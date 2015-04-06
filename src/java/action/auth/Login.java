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
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import util.BCrypt;


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

        User user = new User(username);
        UserDAO dao = DAOHelper.getUserDAO(request);
        String hash = dao.findUser(user).getPassword();
        if( BCrypt.checkpw(password,hash))
        {
            HttpSession session = request.getSession();
            session.setAttribute("user",user);
        }
        else
        {
            request.setAttribute("loginError","Incorrect username or passwords");
        }
    }
}
