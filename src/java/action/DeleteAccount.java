package action;

import dao.UserDAO;
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
 * @author JJ
 */

//DeleteAccount{username, password}

public class DeleteAccount implements Action
{
    // TODO - Put coherently the exceptions to error.jsp
    @Override
    public void execute(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
    {

        String username     = request.getParameter("username");
        String password     = request.getParameter("password");

        try
        {
            User user = new User(username, password);

            UserDAO dao = DAOHelper.getUserDAO(request);
            if( dao.removeUser(user) )
            {
                request.getSession().setAttribute("user",null);
            }
            else
            {
                request.setAttribute("loginError","La contraseña introducida es incorrecta");
            }

            DisplayHelper.setDefaultLists(request);
            DisplayHelper.setAsideLists(request);
            RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
            rd.forward(request, response);
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
