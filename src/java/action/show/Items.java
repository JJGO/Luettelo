/*
 * Class: action.show.Items
 * Luettelo
 *
 * 2015-04-05
 */

package action.show;

import action.Action;
import dominio.List;
import helper.CookieHelper;
import helper.DisplayHelper;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author JJ
 */

//Items.show{listId}

public class Items implements Action
{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException
    {
    	DisplayHelper.setList(request);
        DisplayHelper.setItems(request);


        request.setAttribute("content","items");
        List list = (List) request.getAttribute("displayList");
        request.setAttribute("title",list.getName());

        CookieHelper.setListsCookie(request, response, list);
    }
}
