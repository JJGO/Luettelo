/*
 * Class: action.UnsubscribeList
 * Luettelo
 *
 * 2015-04-04
 */

package action;

import java.io.IOException;
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
   			out.prinln("{ error : "+error+"}");
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
