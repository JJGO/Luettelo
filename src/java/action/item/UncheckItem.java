/*
 * Class: action.do.item.UncheckItem
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

//UncheckItem{itemId} (AJAX)

public class UncheckItem implements Action
{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException
    {
        int itemId = Integer.parseInt(request.getParameter("itemId"));
        User user  = (User) request.getSession().getAttribute("user");

        ItemDAO dao = DAOHelper.getItemDAO(request);
        Item item = new Item(itemId);
        boolean error = !dao.uncheckItem(item, user);
        
        PrintWriter out = response.getWriter();
        out.println("{ error : "+error+"}");
    }
}
