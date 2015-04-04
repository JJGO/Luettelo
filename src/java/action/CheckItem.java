/*
 * Class: action.CheckItem
 * Luettelo
 *
 * 2015-04-04
 */

package action;


/**
 *
 * @author
 */

//CheckItem{id} (AJAX)

public class CheckItem implements Action
{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        int itemId = Integer.parseInt(request.getParameter("itemId"));
        User user  = (User) request.getSession().getAttribute("user");

        try
        {
            ItemDAO dao = DAOHelper.getItemDAO(request);
            Item item = new Item(itemId);
            if( dao.checkItem(item, user) )
            {
            	//// TODO >>> JSON
            }
            else
            {
            	//// TODO >>> JSON
            }
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
