/*
 * Class: servlets.UserActions
 * Luettelo
 *
 * 2015-04-05
 */

package servlets;

import action.Action;
import dominio.User;
import framework.ActionFactory;
import helper.DisplayHelper;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author
 */
public class UserActions extends HttpServlet {

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
        
        User user   = (User) request.getSession().getAttribute("user");
        if(user != null)
        {
            try
            {
                DisplayHelper.setDefaultLists(request);
                String servletPath = request.getServletPath();
                String actionPath = "action";
                if(servletPath.contains("Comment")) {
                    actionPath += ".comment";
                } else if(servletPath.contains("Item")) {
                    actionPath += ".item";
                } else if(servletPath.contains("List")) {
                    actionPath += ".list";
                }
                Action action = ActionFactory.getAction(servletPath,actionPath);
                action.execute(request, response);
            }
            catch (ClassNotFoundException ex)
            {
               response.sendRedirect("ClassNotFoundException.jsp");
               ex.printStackTrace();
            }
            catch (InstantiationException ex)
            {
               response.sendRedirect("InstantiationException.jsp");
               ex.printStackTrace();
            }
            catch (IllegalAccessException ex)
            {
               response.sendRedirect("IllegalAccessException.jsp");
               ex.printStackTrace();
            }
            catch (SQLException ex)
            {
               response.sendRedirect("SQLException.jsp");
               ex.printStackTrace();
            }
        }
        else
        {
            user = new User("Joseja");
            request.getSession().setAttribute("user",user);
            //response.sendRedirect("index");
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
