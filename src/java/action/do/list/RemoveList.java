/*
 * Class: action.do.list.RemoveList
 * Luettelo
 *
 * 2015-04-04
 */

package list;

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
 * @author
 */

//RemoveList{listId}

public class RemoveList implements Action
{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        int listId = Integer.parseInt(request.getParameter("listId"));
        User user  = (User) request.getSession().getAttribute("user");

        try
        {
            ListDAO dao = DAOHelper.getListDAO(request);
            List list = new List(listId);
            if(dao.removeList(list, user))
            {
                DisplayHelper.setItems(request);
                DisplayHelper.setList(request);

                RequestDispatcher rd = request.getRequestDispatcher("/lists.jsp");
                rd.forward(request,response);
            }
            else
            {
                response.sendRedirect("index.jsp");
            }
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
