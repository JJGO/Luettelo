/*
 * Class: action.show.Lists
 * Luettelo
 *
 * 2015-04-05
 */

package action.show;

import action.Action;
import dao.ListDAO;
import dominio.User;
import helper.DAOHelper;
import helper.DisplayHelper;
import java.io.IOException;
import java.sql.SQLException;
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
            throws ServletException, IOException, SQLException, ClassNotFoundException
    {
        String type = request.getParameter("type");
        String value = request.getParameter("value");
        ListDAO dao = (ListDAO) DAOHelper.getListDAO(request);
        User user = (User) request.getSession().getAttribute("user");

        if(type == null)
        {
            request.setAttribute("displayLists",dao.findByCategory("%",user));
            request.setAttribute("title","Luettelo");
        }
        else if (type.equals("category"))
        {
            request.setAttribute("displayLists",dao.findByCategory(value,user));
            request.setAttribute("title",value);
            request.setAttribute("queryMessage","Category: "+value);
        }
        else if (type.equals("search"))
        {
            request.setAttribute("displayLists",dao.findByKeyword(value,user));
            request.setAttribute("title","Search: "+value);
            request.setAttribute("queryMessage","Search: "+value);
        }
        else if (type.equals("user"))
        {
            request.setAttribute("displayLists",dao.findByUser(new User(value),user));
            request.setAttribute("title",value);
            request.setAttribute("queryMessage","User:  "+value);
        }
        else if (type.equals("subscribed"))
        {
            request.setAttribute("displayLists",dao.findBySubscribed(user));
            request.setAttribute("title","Subscriptions");
            request.setAttribute("queryMessage","Subscribed Lists");
        }
        else if( type.equals("visited"))
        {
            DisplayHelper.setVisitedLists(request, response);
            request.setAttribute("displayLists",request.getAttribute("visitedLists"));
            request.setAttribute("title","Recently Visited");
            request.setAttribute("queryMessage","Recently Visited Lists");
        }

        request.setAttribute("content","lists");
    }
}
