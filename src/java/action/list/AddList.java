/*
 * Class: action.do.list.AddList
 * Luettelo
 *
 * 2015-04-04
 */

package action.list;

import action.Action;
import dao.ListDAO;
import dominio.List;
import dominio.User;
import helper.DAOHelper;
import helper.DisplayHelper;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Lucia
 */

//AddList{name, category, description}

public class AddList implements Action
{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException
    {
        String name        = request.getParameter("name");
        String category    = request.getParameter("category");
        String description = request.getParameter("description");
        User user          = (User) request.getSession().getAttribute("user");
                             //to know who created the list
        ListDAO dao = DAOHelper.getListDAO(request);
        List list   = new List(name, category, description);
        if(dao.addList(list, user))
        {
            ArrayList<List> col = dao.findByKeyword(list.getName(),user);
            list = col.get(0);
            request.setAttribute("listId",list.getId());
            DisplayHelper.setItems(request);
            DisplayHelper.setList(request);
            RequestDispatcher rd = request.getRequestDispatcher("/items.jsp");
            rd.forward(request,response);
        }
        else
        {
            response.sendRedirect("error.jsp");
        }
    }
}
