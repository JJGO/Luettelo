/*
 * Class: action.do.list.UnsubscribeList
 * Luettelo
 *
 * 2015-04-04
 */

package list;

import dao.ListDAO;
import dominio.List;
import dominio.User;
import helper.DAOHelper;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
            throws ServletException, IOException
    {
        int listId = Integer.parseInt(request.getParameter("listId"));
        User user  = (User) request.getSession().getAttribute("user");

        try
        {
            ListDAO dao = DAOHelper.getListDAO(request);
            List list = new List(listId);
            boolean error = !dao.unsubscribeList(list, user);
            
            PrintWriter out = response.getWriter();
            out.println("{ error : "+error+"}");
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
