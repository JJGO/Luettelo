package helper;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 *
 * @author JJ
 */
public class DisplayHelper
{
    public static void setDefaultLists(HttpServletRequest request) throws SQLException
    {
        ListDAO dao = DAOHelper.getListDAO();
        User user = request.getSession().getAttribute("user");
        request.setAttribute("defaultLists",dao.findByCategory("%",user));
    }

    public static void setList(HttpServletRequest request) throws SQLException
    {
        ListDAO dao = DAOHelper.getListDAO();
        User user = request.getSession().getAttribute("user");
        List list = new List(request.getParameter("listId"));
        request.setAttribute("displayList",dao.findById());
    }

    public static void setItems(HttpServletRequest request) throws SQLException
    {
        ItemDAO dao = DAOHelper.getItemDAO();
        User user = request.getSession().getAttribute("user");
        List list = new List(request.getParameter("listId"));
        request.setAttribute("displayItems",dao.getItems(list,user));
    }

    public static void setComments(HttpServletRequest request) throws SQLException
    {
        CommentDAO dao = DAOHelper.getCommentDAO();
        List list = new List(request.getParameter("listId"));
        request.setAttribute("displayComments",dao.getComments(list));
    }
}