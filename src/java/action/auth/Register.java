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
import java.io.IOException;
import java.io.PrintWriter;
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

//Register{username, email, password}

public class Register implements Action
{
    @Override
    public void execute(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException
    {
        String username     = request.getParameter("username");
        String email        = request.getParameter("email");
        String password     = request.getParameter("password");
        String rpassword    = request.getParameter("rpassword");

        if(!username.matches("^[a-z0-9_-]{3,15}$"))
        {
            response.sendRedirect("/home"); //el usuario se ha saltado la verificacion de cliente
        }

        else if(!email.matches("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$"))
        {
            response.sendRedirect("/home"); //el usuario se ha saltado la verificacion de cliente
        }

        else if(!password.matches("^.{8,}$"))
        {
            response.sendRedirect("/home"); //el usuario se ha saltado la verificacion de cliente
        }

        else if(!password.equals(rpassword))
        {
            response.sendRedirect("/home"); //el usuario se ha saltado la verificacion de cliente
        }

        else
        {
            String signupErrorJSON;

            UserDAO dao = DAOHelper.getUserDAO(request);
            String hash = BCrypt.hashpw(password, BCrypt.gensalt(12));
            User user = new User(username, email, hash);
            if(dao.findUser(user) != null)
            {
                signupErrorJSON = "{signupError: true, message: 'Username already exists'}";
            }
            //else if(user.getEmail().equals(storedUser.getEmail()))
            //{
            //    request.setAttribute("loginError","Email already registered");
            //}
            else
            {
                dao.addUser(user);
                HttpSession session = request.getSession();
                session.setAttribute("user",user);
                signupErrorJSON = "{signupError: false}";
            }
            
            PrintWriter out = response.getWriter();
            out.println(signupErrorJSON);
            out.close();
        }
    }
}