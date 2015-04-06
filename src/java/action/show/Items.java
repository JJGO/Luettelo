/*
 * Class: action.show.Items
 * Luettelo
 *
 * 2015-04-05
 */

package action.show;

import action.Action;
import helper.DisplayHelper;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
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
        request.setAttribute("title",request.getAttribute("displayList").getName());
        RequestDispatcher rd = request.getRequestDispatcher("/luettelo.jsp");
        rd.forward(request,response);
    }
}
