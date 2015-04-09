/*
 * Class: action.do.list.AddList
 * Luettelo
 *
 * 2015-04-04
 */

package action.list;

import action.Action;
import dao.ListDAO;
import dominio.List;
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
 * @author Lucia
 */

//AddList{name, category, description}

public class AddList implements Action
{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException
    {
        String name        = request.getParameter("name");
        String category    = request.getParameter("category");
        String description = request.getParameter("description");
        if(name != null && category != null && description != null)
        {
            User user          = (User) request.getSession().getAttribute("user");
                                 //to know who created the list
            ListDAO dao = DAOHelper.getListDAO(request);
            List list   = new List(name, category, description);
            list = dao.addList(list, user);
            if(list != null)
            {
                response.sendRedirect("Items.show?listId="+list.getId() );
            }
            else
            {
                request.setAttribute("content","error");
                request.setAttribute("title","Error");
                RequestDispatcher rd = request.getRequestDispatcher("/luettelo.jsp");
                rd.forward(request,response);
            }
        }else
        {
            request.setAttribute("content","newlist");
            request.setAttribute("title","New List");
            RequestDispatcher rd = request.getRequestDispatcher("/luettelo.jsp");
            rd.forward(request,response);
        }
    }
}
