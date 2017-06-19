/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@WebServlet(name = "SetCookie", urlPatterns = {"/SetCookie"})
public class SetCookie extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");        
        
        Cookie firstcookie = new Cookie(UUID.randomUUID().toString(),""+randomWithRange(10, 1000000));
        Cookie secondcookie = new Cookie(UUID.randomUUID().toString(),""+randomWithRange(10, 1000000));
        Cookie thirdcookie = new Cookie(UUID.randomUUID().toString(),""+randomWithRange(10, 1000000));
        
        firstcookie.setMaxAge(60);
        secondcookie.setMaxAge(120);
        thirdcookie.setMaxAge(180);
        
        response.addCookie(firstcookie);
        response.addCookie(secondcookie);
        response.addCookie(thirdcookie);
        
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SetCookie</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SetCookie</h1>");
            
            out.println("<b>Cookie Nr. 1</b><br>");
            out.println("Name: " + firstcookie.getName()+"<br>");
            out.println("Value: " + firstcookie.getValue()+"<br>");
            out.println("MaxAge: " + firstcookie.getMaxAge()+"<br>");
            
            out.println("<b>Cookie Nr. 2</b><br>");
            out.println("Name: " + secondcookie.getName()+"<br>");
            out.println("Value: " + secondcookie.getValue()+"<br>");
            out.println("MaxAge: " + secondcookie.getMaxAge()+"<br>");
            
            out.println("<b>Cookie Nr. 3</b><br>");
            out.println("Name: " + thirdcookie.getName()+"<br>");
            out.println("Value: " + thirdcookie.getValue()+"<br>");
            out.println("MaxAge: " + thirdcookie.getMaxAge()+"<br>");
            
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    int randomWithRange(int min, int max) {
        int range = (max - min) + 1;     
        return (int)(Math.random() * range) + min;
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Hey! Im setting Cookie.";
    }

}
