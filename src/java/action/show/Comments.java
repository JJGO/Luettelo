/*
 * Class: action.show.Comments
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

//Comments.show{listId}

public class Comments implements Action
{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        try
        {
                DisplayHelper.setList(request);
                DisplayHelper.setComments(request);

                RequestDispatcher rd = request.getRequestDispatcher("/comments.jsp");
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
