/*
 * Class: action.do.list.UnsubscribeList
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
 * @author
 */

//UnsubscribeList{listId} (AJAX)

public class UnsubscribeList implements Action
{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException
    {
        int listId = Integer.parseInt(request.getParameter("listId"));
        User user  = (User) request.getSession().getAttribute("user");

        ListDAO dao = DAOHelper.getListDAO(request);
        List list = new List(listId);

        if(dao.unsubscribeList(list, user))
        {
            DisplayHelper.setList(request);
            DisplayHelper.setItems(request);
            
            RequestDispatcher rd = request.getRequestDispatcher("/items.jsp");
            rd.forward(request,response);
        }
        else
        {
            response.sendRedirect("/home");
        }
    }
}
