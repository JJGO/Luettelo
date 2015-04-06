/*
 * Class: action.auth.Logout
 * Luettelo
 *
 * 2015-04-04
 */

package action.auth;

import action.Action;
import java.io.IOException;
import java.sql.SQLException;
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
    }
}
