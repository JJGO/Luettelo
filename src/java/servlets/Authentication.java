/*
 * Class: servlets.Authentication
 * Luettelo
 *
 * 2015-04-05
 */

package servlets;

import action.Action;
import framework.ActionFactory;
import helper.DisplayHelper;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author
 */
public class Authentication extends HttpServlet {

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
           Action action = ActionFactory.getAction(request.getServletPath(),"action.auth");
           action.execute(request, response);
           DisplayHelper.setTrendingLists(request);
           DisplayHelper.setFrontpageLists(request);
           request.setAttribute("content","lists");
           request.setAttribute("title","Luettelo");
        }
//        catch (ClassNotFoundException ex)
//        {
//           response.sendRedirect("error.jsp");
//           ex.printStackTrace();
//        }
//        catch (InstantiationException ex)
//        {
//           response.sendRedirect("error.jsp");
//           ex.printStackTrace();
//        }
//        catch (IllegalAccessException ex)
//        {
//           response.sendRedirect("error.jsp");
//           ex.printStackTrace();
//        }
//        catch (SQLException ex)
//        {
//           response.sendRedirect("error.jsp");
//           ex.printStackTrace();
//        }
        catch(Exception ex)
        {
            request.setAttribute("content","error");
            ex.printStackTrace();
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
