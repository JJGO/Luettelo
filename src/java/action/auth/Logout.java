/*
 * Class: action.auth.Logout
 * Luettelo
 *
 * 2015-04-04
 */

package action.auth;

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

//Logout{}

public class Logout implements Action
{
    // TODO - Put coherently the exceptions to error.jsp
    @Override
    public void execute(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException
    {
        request.getSession().setAttribute("user",null);
        
            DisplayHelper.setTrendingLists(request);
            DisplayHelper.setFrontpageLists(request);
            request.setAttribute("content","lists");
            request.setAttribute("title","Luettelo");
            RequestDispatcher rd = request.getRequestDispatcher("/luettelo.jsp");
            rd.forward(request, response);
    }
}
