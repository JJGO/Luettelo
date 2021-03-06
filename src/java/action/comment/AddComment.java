/*
 * Class: action.do.comment.AddComment
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

//AddComment{listId, content}

public class AddComment implements Action
{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException
    {
        String  content = request.getParameter("content");
        int listId      = Integer.parseInt(request.getParameter("listId"));
        User user       = (User) request.getSession().getAttribute("user");

        CommentDAO dao    = DAOHelper.getCommentDAO(request);
        Comment comment   = new Comment(content);
        dominio.List list = new dominio.List(listId);

        if(dao.addComment(comment, list, user))
        {
            DisplayHelper.setList(request);
            DisplayHelper.setComments(request);

            RequestDispatcher rd = request.getRequestDispatcher("/comments.jsp");
            rd.forward(request,response);
        }
        else
        {
            response.sendRedirect("/home");
        }

    }
}
