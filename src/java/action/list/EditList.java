/*
 * Class: action.do.list.EditList
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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author JJ
 */

//EditList{listId, name, category, description}

public class EditList implements Action
{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException
    {
    	int listId 	   = Integer.parseInt(request.getParameter("listId"));
        String name        = request.getParameter("name");
        String category    = request.getParameter("category");
        String description = request.getParameter("description");
        User user          = (User) request.getSession().getAttribute("user");
                              //to know who created the list
        ListDAO dao = DAOHelper.getListDAO(request);
        List list   = new List(listId, name, category, description);
        
        if(dao.editList(list, user))
        {
            DisplayHelper.setList(request);
            DisplayHelper.setItems(request);
            
            RequestDispatcher rd = request.getRequestDispatcher("/displayList.jsp");
            rd.forward(request,response);
        }
        else
        {
            response.sendRedirect("index");
        }
    }
}
