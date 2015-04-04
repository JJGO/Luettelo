package action;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author JJ
 */
public class Register extends Action
{
    public void execute(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
    {
        // int id = Integer.parseInt(request.getParameter("id"));
     //    ProductoDAO dao = (ProductoDAO) request.getServletContext().getAttribute("productoDAO");

        // try{
        //  Producto producto = dao.findById(id);

        //  Carrito cart = MVC.getCarrito(request);

        //  cart.add(producto);

        //  request.getSession().setAttribute( "total", cart.total() );

        //  RequestDispatcher rd = request.getRequestDispatcher("/carrito.jsp");
        //     rd.forward(request, response);
     //    }
     //    catch(SQLException e)
     //    {
     //        response.sendRedirect("index.jsp");
     //    }
    }
}
