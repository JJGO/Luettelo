/*
 * Class: action.do.item.RateItem
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

//RateItem{itemId, rating}  (AJAX)

public class RateItem implements Action
{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException
    {
        int itemId     = Integer.parseInt(request.getParameter("itemId"));
        Integer rating = Integer.valueOf(request.getParameter("rating"));
        User user      = (User) request.getSession().getAttribute("user");

        ItemDAO dao = DAOHelper.getItemDAO(request);
        Item item = new Item(itemId,rating);
        
        if(dao.rateItem(item, user))
        {
            DisplayHelper.setList(request);
            DisplayHelper.setItems(request);
            
            RequestDispatcher rd = request.getRequestDispatcher("/items.jsp");
            rd.forward(request,response);
        }
        else
        {
            response.sendRedirect("/home");
        }
    }
}
