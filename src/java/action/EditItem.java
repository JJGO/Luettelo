/*
 * Class: action.EditItem
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

//EditItem{id, name, url}

public class EditItem implements Action
{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String name = request.getParameter("name");
        String url = request.getParameter("url");
        int itemId = Integer.valueOf(request.getParameter("itemId"));

        try
        {
            ItemDAO dao = DAOHelper.getItemDAO(request);
            Item item = new Item(name, url, itemId);
            dao.editItem(item);

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
