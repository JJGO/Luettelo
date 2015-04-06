package helper;

import dao.CommentDAO;
import dao.ItemDAO;
import dao.ListDAO;
import dominio.List;
import dominio.User;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;


/**
 *
 * @author JJ
 */
public class DisplayHelper
{

    public static void setDefaultLists(HttpServletRequest request) throws SQLException, ClassNotFoundException
    {
        ListDAO dao = (ListDAO) DAOHelper.getListDAO(request);
        User user = (User) request.getSession().getAttribute("user");
        request.setAttribute("defaultLists",dao.findByRating(15));
    }

    public static void setList(HttpServletRequest request) throws SQLException, ClassNotFoundException
    {
        ListDAO dao = DAOHelper.getListDAO(request);
        User user = (User) request.getSession().getAttribute("user"); //Needed to get the user ratings of the items in the list!
        Integer listId = (Integer) request.getAttribute("listId");
        List list = new List(listId);
        request.setAttribute("displayList",dao.findById(list, user));
    }

    public static void setItems(HttpServletRequest request) throws SQLException, ClassNotFoundException
    {
        ItemDAO dao = DAOHelper.getItemDAO(request);
        User user = (User) request.getSession().getAttribute("user");
        Integer listId = (Integer) request.getAttribute("listId");
        List list = new List(listId);
        request.setAttribute("displayItems",dao.getItems(list,user));
    }

    public static void setComments(HttpServletRequest request) throws SQLException, ClassNotFoundException
    {
        CommentDAO dao = DAOHelper.getCommentDAO(request);
        Integer listId = (Integer) request.getAttribute("listId");
        List list = new List(listId);
        request.setAttribute("displayComments",dao.getComments(list));
    }
}