/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Appointment;
import Model.DBAccess.Access;
import Model.Patient;
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
public class patHandler extends HttpServlet {

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
        
        String action = request.getParameter("action");
        
        try (PrintWriter out = response.getWriter()) {
            
            switch(action){
                case("customerLogin"):
                    patLogin(request,response);
                    break;
                case("customerSignup"):
                    
                    break;
                case("cancelAppt"):
                    cancelAppt(request,response);
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

    
    public void patLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String id = request.getParameter("id");
        String pw = request.getParameter("password");
        boolean validate = checkPatient(id,pw);
        if(validate == true){
            String redirect;            
            boolean validateAppt = validateAppt(id);
            
            if(validateAppt == true){
                Patient p = new Patient();
                p.selectDB(id);
                
                Appointment a = new Appointment();
                a.selectDB(p.getId());
                
                HttpSession ses1 = request.getSession();
                
                ses1.setAttribute("p1", p);
                ses1.setAttribute("a1", a);
                System.out.println("Patient & pat appointment added to session");
                
                redirect = "jspFiles/patPage.jsp";
            }else{
                Patient p = new Patient();
                p.selectDB(id);
                
                HttpSession ses1 = request.getSession();                
                ses1.setAttribute("p1", p);
                
                redirect = "jspFiles/patNoAppt.jsp";
            }
            
            RequestDispatcher rd = request.getRequestDispatcher(redirect);
            rd.forward(request, response);
        }else{
            System.out.println("Please Check credentials");
        }
        
        
    }
    
    public void cancelAppt(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
                HttpSession ses1 = request.getSession();                
                Patient p1 = (Patient)ses1.getAttribute("p1");
                                
                Appointment a1 = new Appointment();
                a1.selectDB(p1.getId());
                a1.deleteDB();
                
                ses1.setAttribute("p1", p1);
                
                RequestDispatcher rd = request.getRequestDispatcher("jspFiles/patNoAppt.jsp");
                rd.forward(request, response);
    }

    private boolean checkPatient(String email, String pw) {
        String validate = null;
        
        try{
            Access access = new Access();
            
            String sql = "SELECT * FROM Patients WHERE patId='"+email+"' and passwd='"+pw+"'";
        
            ResultSet rs = access.getStatement().executeQuery(sql);
            
            if(rs.next()){
                validate=rs.getString(2);
            }
            access.close();
            if(validate.equals(pw)){
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
    
    private boolean validateAppt(String id){
        
        String validate = null;
        
        try{
            Access access = new Access();
            
            String sql = "SELECT * FROM Appointments WHERE patId='"+id+"'";
        
            ResultSet rs = access.getStatement().executeQuery(sql);
            
            if(rs.next()){
                validate=rs.getString(2);
            }
            access.close();
            if(validate.equals(id)){
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
