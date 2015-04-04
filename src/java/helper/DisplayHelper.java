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
        request.setAttribute("displayLists",dao.findByCategory("%",user));
    }

    public static void setAsideLists(HttpServletRequest request) throws SQLException
    {
        ListDAO dao = DAOHelper.getListDAO();
        User user = request.getSession().getAttribute("user");
        if(user == null)
        {
            request.setAttribute("topLists",dao.findByCategory("%",user));
        }
        else
        {
            request.setAttribute("createdLists",dao.findByCreator(user));
            request.setAttribute("subscribedLists",dao.findBySubscriber(user));
        }
    }

    public static void setItems(HttpServletRequest request)
    {
        ItemDAO dao = DAOHelper.getItemDAO();
        List list = request.getAttribute("list"); //???
        User user = request.getSession().getAttribute("user");
        request.setAttribute("displayItems",dao.getItems(list,user));
    }

    public static void setComments(HttpServletRequest request)
    {
        CommentDAO dao = DAOHelper.getCommentDAO();
        List list = request.getAttribute("list"); //???
        request.setAttribute("displayComments",dao.getComments(list));
    }
}