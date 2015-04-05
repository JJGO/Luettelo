/*
 * Class: action.show.Lists
 * Luettelo
 *
 * 2015-04-05
 */

package action.show;

import action.Action;
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

//Lists.show{type,value}

public class Lists implements Action
{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String type = request.getParameter("type");
        String value = request.getParameter("value");
        try
        {
                ListDAO dao = (ListDAO) DAOHelper.getListDAO(request);
                User user = (User) request.getSession().getAttribute("user");


                if(type == null)
                {
                    request.setAttribute("displayLists",dao.findByCategory("%",user));
                }
                else if (type.equals("category"))
                {
                    request.setAttribute("displayLists",dao.findByCategory(value,user));
                }
                else if (type.equals("search"))
                {
                    request.setAttribute("displayLists",dao.findByKeyword(value,user));
                }
                else if (type.equals("user"))
                {
                    request.setAttribute("displayLists",dao.findByUser(value,user));
                }
                else if (type.equals("subscribed"))
                {
                    request.setAttribute("displayLists",dao.findBySubscribed(user));
                }

                RequestDispatcher rd = request.getRequestDispatcher("/lists.jsp");
                rd.forward(request,response);
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
