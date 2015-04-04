/*
 * Class: action.EditComment
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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Lucia
 */

//EditComment{id, content}

public class EditComment implements Action
{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String content = request.getParameter("content");
        int commentId = Integer.valueOf(request.getParameter("commentId"));

        try
        {
            CommentDAO dao = DAOHelper.getCommentDAO(request);
            Comment comment = new Comment(content, commentId);
            dao.editComment(comment);

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