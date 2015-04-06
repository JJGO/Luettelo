/*
 * Class: action.do.comment.EditComment
 * Luettelo
 *
 * 2015-04-04
 */

package action.comment;

import action.Action;
import dao.CommentDAO;
import dominio.Comment;
import dominio.User;
import helper.DAOHelper;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Lucia
 */

//EditComment{commentId, content}

public class EditComment implements Action
{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException
    {
        String content  = request.getParameter("content");
        int commentId   = Integer.parseInt(request.getParameter("commentId"));
        User user       = (User) request.getSession().getAttribute("user");

        CommentDAO dao = DAOHelper.getCommentDAO(request);
        Comment comment = new Comment(content, commentId);
    
        if(dao.editComment(comment, user))
        {
            DisplayHelper.setList(request);
            DisplayHelper.setComments(request);

            RequestDispatcher rd = request.getRequestDispatcher("/comments.jsp");
            rd.forward(request,response);
        }
        else
        {
            response.sendRedirect("index");
        }
    }
}
