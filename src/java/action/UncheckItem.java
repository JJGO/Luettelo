/*
 * Class: action.UncheckItem
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

//UncheckItem{id} //TO DO: Check this is right! (AJAX)

public class UncheckItem implements Action
{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        int itemId = Integer.parseInt(request.getParameter("itemId"));
        User user  = (User) request.getSession().getAttribute("user");

        try
        {
            ItemDAO dao = DAOHelper.getItemDAO(request);
            Item item = new Item(itemId);
            boolean error = !dao.uncheckItem(item, user);
            
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
