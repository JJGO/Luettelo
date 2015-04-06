/*
 * Class: action.do.comment.RemoveComment
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

//RemoveComment{commentId, username}

public class RemoveComment implements Action
{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException
    {
        int commentId = Integer.parseInt(request.getParameter("commentId"));
        User user = (User) request.getSession().getAttribute("user");

        CommentDAO dao = DAOHelper.getCommentDAO(request);
        Comment comment = new Comment(commentId);

        boolean error = !dao.removeComment(comment, user);
        
        PrintWriter out = response.getWriter();
        out.println("{ error : "+error+"}");
    }
}
