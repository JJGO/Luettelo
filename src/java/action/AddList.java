/*
 * Class: action.AddList
 * Luettelo
 *
 * 2015-04-04
 */

package action;

import dao.ListDAO;
import dominio.List;
import dominio.User;
import helper.DAOHelper;
import helper.DisplayHelper;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Lucia
 */

//AddList{name, category, description, username}

public class AddList implements Action
{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String name = request.getParameter("name");
        String category = request.getParameter("category");
        String description = request.getParameter("description");
        User user = (User) request.getSession().getAttribute("user"); //to know who created the list

        try
        {
            ListDAO dao = DAOHelper.getListDAO(request);
            List list = new List(name, category, description);
            dao.addList(list, user);

            DisplayHelper.setItems(request);
            DisplayHelper.setList(request);

            RequestDispatcher rd = request.getRequestDispatcher("/items.jsp");
            rd.forward(request,response);
        }
        catch(SQLException e)
        {
            response.sendRedirect("error.jsp");
        }
        catch(ClassNotFoundException e)
        {
            response.sendRedirect("error.jsp");
        }
    }
}
