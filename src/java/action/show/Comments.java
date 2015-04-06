/*
 * Class: action.show.Comments
 * Luettelo
 *
 * 2015-04-05
 */

package action.show;

import action.Action;
import dominio.List;
import helper.DisplayHelper;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author JJ
 */

//Comments.show{listId}

public class Comments implements Action
{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException
    {
        DisplayHelper.setList(request);
        DisplayHelper.setComments(request);

        request.setAttribute("content","comments");
        List list = (List) request.getAttribute("displayList");
        request.setAttribute("title",list.getName()+" : Comments");
    }
}
