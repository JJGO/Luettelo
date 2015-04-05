/*
 * Class: action.do.item.CheckItem
 * Luettelo
 *
 * 2015-04-04
 */

package item;

import dao.ItemDAO;
import dominio.Item;
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

//CheckItem{itemId} (AJAX)

public class CheckItem implements Action
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
            boolean error = !dao.checkItem(item, user);
            
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
