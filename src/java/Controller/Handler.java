/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.DBAccess.Access;
import Model.Dentist;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Neal Valdez
 */
public class Handler extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */                                
            HttpSession ses1 = null;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String action = request.getParameter("action"); 
        
        try (PrintWriter out = response.getWriter()) {
            
            switch(action){
                case("dLogin"):
                    dentistLogin(request,response);
                    break;
                case("editCreds"):
                    editCreds(request,response);
                    break;
                
            }                      
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
    
    
    public void dentistLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String id = request.getParameter("inputId");
                String pw = request.getParameter("inputPassword");
                boolean result = checkDentist(id,pw);
                
                if(result == true){
                    
                    Dentist d1 = new Dentist();
                    d1.selectDB(id);
                    d1.retrieveApptList();
                    
                    
                    ses1 = request.getSession();
                    
                    ses1.setAttribute("dent1", d1);
                    ses1.setAttribute("dentId", d1.getId());
                    ses1.setAttribute("apptList",d1.aL.appts);
                    System.out.println("Dentist added to session");
                    
                     RequestDispatcher rd = request.getRequestDispatcher("jspFiles/dentistPage.jsp");
                     rd.forward(request, response);
                }else{
                    System.out.println("Please check credentials and try again");
                }
        
        
    }
    
    public void editCreds(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            try{
                    String id = (String) request.getSession().getAttribute("dentId");
                    

                    String fname = request.getParameter("FirstName");
                    String lname = request.getParameter("LastName");
                    String email = request.getParameter("Email");
                    String office = request.getParameter("Office");
                    String pw = request.getParameter("Password");

                    Dentist d1 = new Dentist();
                    d1.selectDB(id);
                    d1.setFname(fname);
                    d1.setLname(lname);
                    d1.setEmail(email);
                    d1.setOfficeNum(office);
                    d1.setPw(pw);
                    d1.updateDB();
                    d1.retrieveApptList();
                    
                    ses1 = request.getSession();
                    ses1.setAttribute("d1",d1);
                    ses1.setAttribute("apptList",d1.aL.appts);
                       
                    RequestDispatcher rd = request.getRequestDispatcher("jspFiles/dentistPage.jsp");
                    rd.forward(request, response);
                }catch(Exception e){
                    e.printStackTrace();
                }
                
            }
        
    protected boolean checkDentist(String id,String pass){
        String validate = null;
        
        try{
            Access access = new Access();
            
            String sql = "SELECT * FROM Dentists WHERE id='"+id+"' and passwd='"+pass+"'";
        
            ResultSet rs = access.getStatement().executeQuery(sql);
            
            if(rs.next()){
                validate=rs.getString(2);
            }
            access.close();
            if(validate.equals(pass)){
                return true;
            }
            else{
                return false;
            }            
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
}