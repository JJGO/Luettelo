/*
 * Class: action.CreateComment
 * Luettelo
 *
 * 2015-04-04
 */

package action;

import dao.CommentDAO;
import dominio.Comment;
import helper.DAOHelper;
import helper.DisplayHelper;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Lucia
 */
public class CreateComment implements Action
{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String content = request.getParameter("content");
        String username = request.getParameter("username");
        String listId_st = request.getParameter("listId");
        int listId = Integer.valueOf(listId_st);

        try
        {
            CommentDAO dao = DAOHelper.getCommentDAO(request);
            Comment comment = new Comment(content, username);
            dominio.List list = new dominio.List(listId);
            dao.addComment(comment, list);

            DisplayHelper.setComments(request);

            RequestDispatcher rd = request.getRequestDispatcher("/comments.jsp");
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
