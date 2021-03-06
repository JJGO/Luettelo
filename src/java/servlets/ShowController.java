/*
 * Class: servlets.ShowController
 * Luettelo
 *
 * 2015-04-05
 */

package servlets;

import action.Action;
import framework.ActionFactory;
import helper.DisplayHelper;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author
 */
public class ShowController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        
        try
        {
           DisplayHelper.setVisitedLists(request, response);
           Action action = ActionFactory.getAction(request.getServletPath(),"action.show");
           action.execute(request, response);
        }
//        catch (ClassNotFoundException ex)
//        {
//           request.setAttribute("content","error");
//           ex.printStackTrace();
//        }
//        catch (InstantiationException ex)
//        {
//           request.setAttribute("content","error");
//           ex.printStackTrace();
//        }
//        catch (IllegalAccessException ex)
//        {
//           request.setAttribute("content","error");
//           ex.printStackTrace();
//        }
//        catch (SQLException ex)
//        {
//           request.setAttribute("content","error");
//           ex.printStackTrace();
//        }
        catch (Exception ex)
        {
           request.setAttribute("content","error");
           ex.printStackTrace();
        }
        finally
        {
            RequestDispatcher rd = request.getRequestDispatcher("/luettelo.jsp");
            rd.forward(request,response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
