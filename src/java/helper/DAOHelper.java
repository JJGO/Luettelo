package helper;


/**
 *
 * @author JJ
 */
public class DAOHelper
{
    // BIG TO DO - Make the DAO getters using Reflection
    public static UserDAO getUserDAO(HttpServletRequest request) throws SQLException, ClassNotFoundException
    {
        ServletContext application = request.getServletContext();
        UserDAO dao  = (UserDAO) application.getAttribute("UserDAO");
        if(dao == null)
        {
            dao = new UserDAO();
            aplicacion.setAttribute("UserDAO",dao);
        }
        return dao;
    }

    public static ListDAO getListDAO(HttpServletRequest request) throws SQLException, ClassNotFoundException
    {
        ServletContext application = request.getServletContext();
        ListDAO dao  = (ListDAO) application.getAttribute("ListDAO");
        if(dao == null)
        {
            dao = new ListDAO();
            aplicacion.setAttribute("ListDAO",dao);
        }
        return dao;
    }

    public static ItemDAO getItemDAO(HttpServletRequest request) throws SQLException, ClassNotFoundException
    {
        ServletContext application = request.getServletContext();
        ItemDAO dao  = (ItemDAO) application.getAttribute("ItemDAO");
        if(dao == null)
        {
            dao = new ItemDAO();
            aplicacion.setAttribute("ItemDAO",dao);
        }
        return dao;
    }

    public static CommentDAO getCommentDAO(HttpServletRequest request) throws SQLException, ClassNotFoundException
    {
        ServletContext application = request.getServletContext();
        CommentDAO dao  = (CommentDAO) application.getAttribute("CommentDAO");
        if(dao == null)
        {
            dao = new CommentDAO();
            aplicacion.setAttribute("CommentDAO",dao);
        }
        return dao;
    }

}