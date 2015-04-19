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
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        
        String loginErrorJSON = "{loginError: true, message: 'Incorrect username or password'}";

        user = dao.findUser(user);
           
        if(user != null) 
        {
            //User exists in DataBase
            String hash = user.getPassword(); //get password
            
            if(BCrypt.checkpw(password,hash))
            {
                request.getSession().setAttribute("user",user);
                loginErrorJSON = "{loginError: false}";
            }
        }

        PrintWriter out = response.getWriter();
        out.println(loginErrorJSON);
        out.close();
    }
}
