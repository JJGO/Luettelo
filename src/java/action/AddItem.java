/*
 * Class: action.AddItem
 * Luettelo
 *
 * 2015-04-04
 */

package action;

import dao.ItemDAO;
import dominio.Item;
import helper.DAOHelper;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Lucia
 */
public class AddItem extends Action
{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        String name = request.getParameter("name");
        String url = request.getParameter("url");
        String listId_st = request.getParameter("listId");
        int listId = Integer.valueOf(listId_st);

        try
        {
            ItemDAO dao = DAOHelper.getItemDAO(request);
            Item item = new Item(name, url);
            dominio.List list = new dominio.List(listId);
            dao.addItem(item, list);

            DisplayHelper.setItems(request); //???

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
