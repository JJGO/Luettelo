package action;

import helper.DisplayHelper;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author JJ
 */

//Logout{}

public class Logout implements Action
{
    // TODO - Put coherently the exceptions to error.jsp
    @Override
    public void execute(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
    {
        try {
            request.getSession().setAttribute("user",null);
            DisplayHelper.setDefaultLists(request);
            RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
            rd.forward(request, response);
        } catch (SQLException ex) {
            response.sendRedirect("error.jsp");
        } catch (ClassNotFoundException ex) {
            response.sendRedirect("error.jsp");
        }
    }
}
