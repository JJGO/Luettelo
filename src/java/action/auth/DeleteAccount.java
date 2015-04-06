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

//DeleteAccount{password}

public class DeleteAccount implements Action
{
    // TODO - Put coherently the exceptions to error.jsp
    @Override
    public void execute(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException
    {

        String password     = request.getParameter("password");
        User user           = (User) request.getSession().getAttribute("user");

        User user_delete = new User(user.getUsername(), password);

        UserDAO dao = DAOHelper.getUserDAO(request);
        if(dao.removeUser(user_delete))
        {
            request.getSession().setAttribute("user",null);
        }
        else
        {
            request.setAttribute("loginError","La contraseña introducida es incorrecta");
        }

        RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
        rd.forward(request, response);
    }
}
