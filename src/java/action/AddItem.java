/*
 * Class: action.AddItem
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

//AddItem{name, url, listId}

public class AddItem implements Action
{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String name = request.getParameter("name");
        String url = request.getParameter("url");
        int listId = Integer.valueOf(request.getParameter("listId"));

        try
        {
            ItemDAO dao = DAOHelper.getItemDAO(request);
            Item item = new Item(name, url);
            dominio.List list = new dominio.List(listId);
            dao.addItem(item, list);

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
