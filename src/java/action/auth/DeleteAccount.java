/*
 * Class: action.auth.DeleteAccount
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
import util.BCrypt;


/**
 *
 * @author JJ
 */

//DeleteAccount{password}

public class DeleteAccount implements Action
{
    // TODO - Put coherently the exceptions to error.jsp
    @Override
    public void execute(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException
    {

        String username     = request.getParameter("username");
        String password     = request.getParameter("password");

        User user = new User(username);
        UserDAO dao = DAOHelper.getUserDAO(request);
        User storedUser = dao.findUser(user);
        String hash = storedUser.getPassword();
        if( BCrypt.checkpw(password,hash))
        {
            dao.removeUser(storedUser);
            request.getSession().setAttribute("user",null);
        }
        else
        {
            request.setAttribute("loginError","La contraseña introducida es incorrecta");
        }
        DisplayHelper.setDefaultLists(request);
        request.setAttribute("content","lists");
        RequestDispatcher rd = request.getRequestDispatcher("/luettelo.jsp");
        rd.forward(request, response);
    }
}
