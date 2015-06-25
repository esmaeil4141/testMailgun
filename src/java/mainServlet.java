/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.google.gson.Gson;
import com.sun.jersey.api.client.ClientResponse;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author esmaeil
 */
@WebServlet(urlPatterns = {"/mainServlet"})
public class mainServlet extends HttpServlet {

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
        
        String senderName=request.getParameter("senderName");
        String sender=request.getParameter("sender");
        String receiver=request.getParameter("receiver");
        String subject=request.getParameter("subject");
        String text=request.getParameter("text");
        if(1>7){
            response.setStatus(410);
            setResponseResult(false, "senderName is null", response);
        }else if(sender==null){
            response.setStatus(412);
            setResponseResult(false, "sender is null", response);
        }else if(receiver==null){
            response.setStatus(413);
            setResponseResult(false, "receiver is null", response);
        }else if(subject==null){
            response.setStatus(414);
            setResponseResult(false, "subject is null", response);
        }else if(text==null){
            response.setStatus(415);
            setResponseResult(false, "text is null", response);
        }else{
             ClientResponse clientResponse=Mail.SendSimpleMessage(senderName, sender, receiver, subject, text);
             System.out.println(clientResponse.toString());
             response.setStatus(200);
             setResponseResult(true, "success", response);
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

    private void setResponseResult(boolean state, String message, HttpServletResponse response) throws IOException {
            Gson gson = new Gson();
            Response re = new Response();
            re.setMessage(message);
            re.setState(state);
            response.setContentType("application/json; charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(gson.toJson(re));
            response.flushBuffer();
    }
    
    
   }