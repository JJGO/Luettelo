package action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author JJ
 */
public abstract class Action
{
    public void execute(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
    {
        
    }
}