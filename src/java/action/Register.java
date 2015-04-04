package action;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
// import java.security.NoSuchAlgorithmException;


/**
 *
 * @author JJ
 */
public class Register extends Action
{
    // TODO - Put coherently the exceptions to error.jsp
    public void execute(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
    {

        String username     = request.getParameter("username");
        String email        = request.getParameter("email");
        String password     = request.getParameter("password");

        try
        {
            String hash = BCrypt.hashpw(password, BCrypt.gensalt(12));
            User user = new User(username, email, hash);

            UserDAO dao = DAOHelper.getUserDAO()
            dao.addUser(user);

            HttpSession session = request.getSession();
            session.setAttribute("user",user);

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
