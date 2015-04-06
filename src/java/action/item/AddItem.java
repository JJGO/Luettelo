/*
 * Class: action.do.item.AddItem
 * Luettelo
 *
 * 2015-04-04
 */

package action.item;

import action.Action;
import dao.ItemDAO;
import dominio.Item;
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
 * @author Lucia
 */

//AddItem{name, url, listId}

public class AddItem implements Action
{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException
    {
        String name = request.getParameter("name");
        String url  = request.getParameter("url");
        int listId  = Integer.parseInt(request.getParameter("listId"));
        User user   = (User) request.getSession().getAttribute("user");

        ItemDAO dao       = DAOHelper.getItemDAO(request);
        Item item         = new Item(name, url);
        dominio.List list = new dominio.List(listId);
        if(dao.addItem(item, list, user))
        {
            DisplayHelper.setList(request);
            DisplayHelper.setItems(request);

            RequestDispatcher rd = request.getRequestDispatcher("/items.jsp");
            rd.forward(request,response);
        }
        else
        {
            response.sendRedirect("index.jsp");
        }
    }
}
