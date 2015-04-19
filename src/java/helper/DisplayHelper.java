package helper;

import dao.CommentDAO;
import dao.ItemDAO;
import dao.ListDAO;
import dominio.List;
import dominio.User;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;


/**
 *
 * @author JJ
 */
public class DisplayHelper
{

    public static void setTrendingLists(HttpServletRequest request) throws SQLException, ClassNotFoundException
    {
        ListDAO dao = (ListDAO) DAOHelper.getListDAO(request);
        User user = (User) request.getSession().getAttribute("user");
        request.setAttribute("trendingLists",dao.findByRating(15,user));
    }

    public static void setVisitedLists(HttpServletRequest request) throws SQLException, ClassNotFoundException
    {
        ListDAO dao = DAOHelper.getListDAO(request);
        ArrayList<String> listIds = CookieHelper.getSplittedListsCookie(request);
        ArrayList<List> visitedLists = new ArrayList<List>();

        if(listIds != null)
        {
            for(String id:listIds)
            {
                List list = new List(Integer.parseInt(id));
                visitedLists.add(dao.findById(list, null));
            }
        }
     
        request.setAttribute("visitedLists",visitedLists);
    }
    
    public static void setList(HttpServletRequest request) throws SQLException, ClassNotFoundException
    {
        ListDAO dao = DAOHelper.getListDAO(request);
        User user = (User) request.getSession().getAttribute("user"); //Needed to get the user ratings of the items in the list!
        int listId =  Integer.parseInt(request.getParameter("listId"));
        List list = new List(listId);
        request.setAttribute("displayList",dao.findById(list, user));
    }

    public static void setItems(HttpServletRequest request) throws SQLException, ClassNotFoundException
    {
        ItemDAO dao = DAOHelper.getItemDAO(request);
        User user = (User) request.getSession().getAttribute("user");
        int listId =  Integer.parseInt(request.getParameter("listId"));
        List list = new List(listId);
        request.setAttribute("displayItems",dao.getItems(list,user));
    }

    public static void setComments(HttpServletRequest request) throws SQLException, ClassNotFoundException
    {
        CommentDAO dao = DAOHelper.getCommentDAO(request);
        int listId =  Integer.parseInt(request.getParameter("listId"));
        List list = new List(listId);
        request.setAttribute("displayComments",dao.getComments(list));
    }

    public static void setFrontpageLists(HttpServletRequest request) throws SQLException, ClassNotFoundException
    {
        ListDAO dao = (ListDAO) DAOHelper.getListDAO(request);
        User user = (User) request.getSession().getAttribute("user");
        request.setAttribute("displayLists",dao.findByRating(50,user));
    }
}