/*
 * Class: action.RemoveItem
 * Luettelo
 *
 * 2015-04-04
 */

package action;

import dao.ItemDAO;
import dominio.Item;
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

//RemoveItem{id}

public class RemoveItem implements Action
{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String itemId_st = request.getParameter("itemId");
        int itemId = Integer.valueOf(itemId_st);

        try
        {
            ItemDAO dao = DAOHelper.getItemDAO(request);
            Item item = new Item(itemId);
            dao.removeItem(item);

            DisplayHelper.setItems(request);

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
