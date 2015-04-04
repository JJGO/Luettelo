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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Lucia
 */
public class EditComment implements Action
{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        String content = request.getParameter("content");
        String commentId_st = request.getParameter("commentId");
        int commentId = Integer.valueOf(commentId_st);

        try
        {
            CommentDAO dao = DAOHelper.getCommentDAO(request);
            Comment comment = new Comment(content, commentId);
            dao.editComment(comment);

            DisplayHelper.setComments(request); //???

          //to do:
            //in case its an AJAX request:
            //  response.sendRedirect("something"); 
            //
            //in case its not:
            //  RequestDispatcher rd = request.getRequestDispatcher("something");
            //  rd.forward(request,response);

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
