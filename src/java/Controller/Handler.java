/*********************************************** 
 * Instructor: Ron Enz
 * Description: Handles requests from Dentists
 * @author Neal Valdez
 * @version 1.0
 *
 * By turning in this code, I Pledge:
 *  1. That I have completed the programming assignment independently.
 *  2. I have not copied the code from a student or any source.
 *  3. I have not given my code to any student.
 *
 ************************************************/
package Controller;

import Model.DBAccess.Access;
import Model.Dentist;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Handler extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     */          
        //initializes session
         //HttpSession ses1 = null;
    
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
    
    /**
    * Function recieves two input values from dentist login form.
    * Creates new Dentist object and retrieves appt list.
    * Object added into Session.  
    */
    public void dentistLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
                String id = request.getParameter("inputId");
                String pw = request.getParameter("inputPassword");
                boolean validate = checkDentist(id,pw);
                
                if(validate == true){
                    Dentist d1 = new Dentist();
                    d1.selectDB(id);
                    d1.retrieveApptList();
                    
                    
                    HttpSession ses1 = request.getSession();
                    
                    ses1.setAttribute("d1", d1);
                    ses1.setAttribute("dentId", d1.getId());
                    ses1.setAttribute("apptList",d1.aL.appts);
                    System.out.println("Dentist added to session");
                    
                     RequestDispatcher rd = request.getRequestDispatcher("jspFiles/dentistPage.jsp");
                     rd.forward(request, response);
                }else{
                    System.out.println("Please check credentials and try again");
                }
                
        
    }
    /**
    * Function to edit Dentist Credentials
    * gets id from session to instantiate a new object
    * and update the attributes.
    *
    */
    public void editCreds(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            try{
                HttpSession ses2 = request.getSession();  
                
                String id = (String) ses2.getAttribute("dentId");
                Dentist d1 = (Dentist) ses2.getAttribute("d1");

                String fname = request.getParameter("FirstName");
                String lname = request.getParameter("LastName");
                String email = request.getParameter("Email");
                String office = request.getParameter("Office");
                String pw = request.getParameter("Password");
                
                d1.selectDB(id);
                d1.setFname(fname);
                d1.setLname(lname);
                d1.setEmail(email);
                d1.setOfficeNum(office);
                d1.setPw(pw);
                d1.updateDB();
                d1.retrieveApptList();

                //ses2 = request.getSession();
                ses2.setAttribute("d1",d1);
                ses2.setAttribute("apptList",d1.aL.appts);

                RequestDispatcher rd = request.getRequestDispatcher("jspFiles/dentistPage.jsp");
                rd.forward(request, response);
                }catch(IOException | ServletException e){
                    e.printStackTrace();
                }
                
            }
/**    
* Function that checks the database for a match on id and password
*    
* @param  id -- id input value from login form
* @param  pass -- password input value from login form    
* @return      True if login credentials match, else false
*/
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
    
    private boolean validateAppt(String id){
        
        String validate="";
        
        try{
            
            Access access = new Access();
            
            String sql = "SELECT * FROM Appointments WHERE dentId='"+id+"'";
            
            ResultSet rs = access.getStatement().executeQuery(sql);
            
            if(rs.next()){
                validate=rs.getString(3);
            }
            
            access.close();
            System.out.println(validate);
            return validate.equals(id);
            
        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
            return false;
        }
        
    }
}