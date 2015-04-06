/*
 * Class: action.do.item.EditItem
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

//EditItem{itemId, name, url}

public class EditItem implements Action
{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException
    {
        String name = request.getParameter("name");
        String url  = request.getParameter("url");
        int itemId  = Integer.parseInt(request.getParameter("itemId"));
        User user   = (User) request.getSession().getAttribute("user");

        ItemDAO dao = DAOHelper.getItemDAO(request);
        Item item = new Item(name, url, itemId);
        
        boolean error = !dao.editItem(item, user);
        
        PrintWriter out = response.getWriter();
        out.println("{ error : "+error+"}");
    }
}
