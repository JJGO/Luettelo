package helper;

import dominio.List;
import java.util.ArrayList;
import java.util.Arrays;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author Lucia
 */
public class CookieHelper
{
    public static Cookie findCookie(HttpServletRequest request, String search)
    {
        Cookie cookies[] = request.getCookies();
        Cookie cookie = null;

        if(cookies!=null)
        {
            for(Cookie c:cookies)
            {
                if(c.getName().equals(search))
                {
                    cookie = c;    
                }
            }   
        }
        return cookie;
    }

    public static void setListsCookie(HttpServletRequest request, HttpServletResponse response, List list)
    {
        Cookie c = CookieHelper.findCookie(request, "visitedLists");
        String listId = Integer.toString(list.getId());
        
        if (c == null)
        {
            c = new Cookie("visitedLists", listId);     //create and initialize Cookie
            c.setHttpOnly(true);
        }
        else 
        {
            String[] array = c.getValue().split(":");   //get array of Strings
            ArrayList<String> values = new ArrayList<String>(Arrays.asList(array)); //get ArrayList<String> from array
            int max = Integer.parseInt(request.getServletContext().getInitParameter("maxVisited"));
            
            if (values.contains(listId))
            {
                values.remove(listId);                  //so that there are no repeated values in the Cookie
            }
            values.add(listId);                         //add the new list at the end
            
            if(values.size() > max)
            {
                values.remove(0);
            }
            String value = values.toString();
            String cookieValue = value.substring(1, value.length()-1).replace(", ",":");

            c.setValue(cookieValue);                    //add value to the Cookie
        }

        c.setPath("/");
        response.addCookie(c);
    }

    public static ArrayList<String> getSplittedListsCookie(HttpServletRequest request)
    {
        Cookie c = CookieHelper.findCookie(request, "visitedLists");
        if (c != null) 
        {           
            String[] array = c.getValue().split(":");   //get array of Strings
            ArrayList<String> values = new ArrayList<String>(Arrays.asList(array));
            return values;
        }
        return null;
    }
}
