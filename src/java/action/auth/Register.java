/*
 * Class: action.auth.Register
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
import util.BCrypt;
// import java.security.NoSuchAlgorithmException;

/**
 *
 * @author JJ
 */

//Register{username, email, password}

public class Register implements Action
{
    // TODO - Put coherently the exceptions to error.jsp
    @Override
    public void execute(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException
    {

        String username     = request.getParameter("username");
        String email        = request.getParameter("email");
        String password     = request.getParameter("password");
        String rpassword    = request.getParameter("rpassword");

        if(!username.matches("^[a-z0-9_-]{3,15}$"))
        {
            response.sendRedirect("index"); //el usuario se ha saltado la verificacion de cliente
        }
        else if(!email.matches("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$"))
        {
            response.sendRedirect("index"); //el usuario se ha saltado la verificacion de cliente
        }
        else if(!password.matches("^.{8,}$"))
        {
            response.sendRedirect("index"); //el usuario se ha saltado la verificacion de cliente
        }
        else if(password.equals(rpassword))
        {
            response.sendRedirect("index"); //el usuario se ha saltado la verificacion de cliente
        }
        else
        {
            String hash = BCrypt.hashpw(password, BCrypt.gensalt(12));
        User user = new User(username, email, hash);

        UserDAO dao = DAOHelper.getUserDAO(request);
        dao.addUser(user);

        HttpSession session = request.getSession();
        session.setAttribute("user",user);

        DisplayHelper.setDefaultLists(request);
        RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
        rd.forward(request, response);
        }
        
    }
}
