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
            throws ServletException, IOException
    {
        try
        {
                DisplayHelper.setList(request);
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
