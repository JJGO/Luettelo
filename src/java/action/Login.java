package action;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author JJ
 */
public class Login implements Action
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

            UserDAO dao = DAOHelper.getUserDAO();
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
