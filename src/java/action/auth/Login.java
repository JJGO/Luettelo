/*
 * Class: action.auth.Login
 * Luettelo
 *
 * 2015-04-04
 */

package action.auth;

import action.Action;
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
import javax.servlet.http.HttpSession;


/**
 *
 * @author JJ
 */

//Login{username, password}

public class Login implements Action
{
    // TODO - Put coherently the exceptions to error.jsp
    @Override
    public void execute(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException
    {
        String username     = request.getParameter("username");
        String password     = request.getParameter("password");

        User user = new User(username, password);
        UserDAO dao = DAOHelper.getUserDAO(request);
        if( dao.findUser(user) )

        {
            HttpSession session = request.getSession();
            session.setAttribute("user",user);
        }
        else
        {
            request.setAttribute("loginError","El usuario/contraseña son incorrectos");
        }

        DisplayHelper.setDefaultLists(request);
        RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
        rd.forward(request, response);
    }
}
