/*
 * Class: action.RateItem
 * Luettelo
 *
 * 2015-04-04
 */

package action;


/**
 *
 * @author
 */

//RateItem{id, value} //TO DO: Check this is right! (AJAX)

public class RateItem implements Action
{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        int itemId 		= Integer.parseInt(request.getParameter("itemId"));
        Integer rating 	= Integer.valueOf(request.getParameter("rating"));
        User user       = (User) request.getSession().getAttribute("user");

        try
        {
            ItemDAO dao = DAOHelper.getItemDAO(request);
            Item item = new Item(itemId,rating);
            if( dao.rateItem(item, user) )
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
