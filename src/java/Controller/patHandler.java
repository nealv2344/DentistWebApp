/*********************************************** 
 * Instructor: Ron Enz
 * Description: Handles requests from Patients
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

import Model.Appointment;
import Model.DBAccess.Access;
import Model.Patient;
import static Model.Patient.getRowCount;
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
                case("patSignup"):
                    patSignup(request,response);
                    break;
                case("cancelAppt"):
                    cancelAppt(request,response);
                    break;
                case("addAppt"):
                    addAppt(request,response);
                    break;
                case("editPatInfo"):
                    editPatInfo(request,response);
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
    * Function recieves two input values from patient login form.
    * Validates user input using checkPatient() method.
    * Checks database for Appointments. Runs a conditional statement
    * to redirect page depending on if user has upcoming appt.
    * Object added into Session.  
    */
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
                ses1.setAttribute("pId", p.getId());
                ses1.setAttribute("a1", a);
                System.out.println("Patient & pat appointment added to session");
                
                redirect = "jspFiles/patPage.jsp";
            }else{
                Patient p = new Patient();
                p.selectDB(id);
                
                HttpSession ses1 = request.getSession();                
                ses1.setAttribute("p1", p);
                ses1.setAttribute("pId", p.getId());
                
                redirect = "jspFiles/patNoAppt.jsp";
            }
            
            RequestDispatcher rd = request.getRequestDispatcher(redirect);
            rd.forward(request, response);
        }else{
            System.out.println("Please Check credentials");
        }
        
        
    }
    /**
    * Autogenerates patId.
    * Recieves credentials from signup form.
    * Uses insertDB() method to add new patient to the database.
    * Adds patient into new session.
    */
    public void patSignup(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //generating new id
        String A9 = request.getParameter("id");
        int count = getRowCount();
        String countConvert = Integer.toString(count);
        String id = A9+countConvert;
        
        String fname = request.getParameter("fName");
        String lname = request.getParameter("lName");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String insCo = request.getParameter("insurance");
        
        Patient p1 = new Patient(id,password,fname,lname,address,email,insCo);
        p1.insertDB();
        p1.display();
        
        HttpSession ses1 = request.getSession();
        
        ses1.setAttribute("p1",p1);
        
        RequestDispatcher rd = request.getRequestDispatcher("jspFiles/patNoAppt.jsp");
        rd.forward(request, response);
        
    }
    /**
    * Gets Patient out of session.
    * Instantiates new Appointment object.
    * Deletes corresponding appointment bases on patient id.
    * Adds patient back into session.
    */
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
    /**
    * Gets Patient out of Session 
    * Recieves data input from AddAppointment form.
    * Adds new appointment to database based on patient id.
    */
    public void addAppt(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException  {
        
        HttpSession ses1 = request.getSession();                
        Patient p1 = (Patient)ses1.getAttribute("p1");
        
        String date = request.getParameter("Date");
        String time = request.getParameter("Time");
        String dentId = request.getParameter("Dentist");
        String procCode = request.getParameter("Procedure");      
        String dateTime = date+" "+time;
        
        Appointment a1 = new Appointment(dateTime,p1.getId(),dentId,procCode);
        a1.insertDB();
        a1.display();
        
        ses1.setAttribute("a1",a1);
        ses1.setAttribute("p1",p1);
        
        RequestDispatcher rd = request.getRequestDispatcher("jspFiles/patPage.jsp");
        rd.forward(request, response);
        
    } 
    /**
    * Function to edit Patient Info.
    * Gets id from session to instantiate a new object
    * and update the attributes.
    *
    */
    public void editPatInfo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        
        try{
            HttpSession ses1 = request.getSession();
            String id = (String) ses1.getAttribute("pId");
            Patient p1 = (Patient) ses1.getAttribute("p1");

            String fname = request.getParameter("FirstName");
            String lname = request.getParameter("LastName");
            String email = request.getParameter("Email");
            String address = request.getParameter("Address");
            String pw = request.getParameter("Password");
            String insCo = request.getParameter("InsCo");
            
            p1.selectDB(id);
            p1.setFname(fname);
            p1.setLname(lname);
            p1.setEmail(email);
            p1.setAddress(address);
            p1.setPw(pw);
            p1.setInsCo(insCo);
            p1.updateDB();


            String redirect;
            boolean validateAppt = validateAppt(id);
            if(validateAppt == true){
                Appointment a1 = (Appointment) ses1.getAttribute("a1");
                a1.selectDB(id);
                ses1.setAttribute("p1", p1);
                ses1.setAttribute("a1", a1);
                RequestDispatcher rd = request.getRequestDispatcher("jspFiles/patPage.jsp");
                rd.forward(request, response);

            }else{
                ses1.setAttribute("p1",p1);
                RequestDispatcher rd = request.getRequestDispatcher("jspFiles/patNoAppt.jsp");
                rd.forward(request, response);
            }

            
        }catch(IOException | ServletException e){
            e.printStackTrace();
        }
        
    }  
    /**  
    * Function that checks the database for a match on id and password
    *    
    * @param  id -- id input value from login form
    * @param  pw -- password input value from login form    
    * @return      True if login credentials match, else false
    */
    private boolean checkPatient(String id, String pw) {
        String validate = null;
        
        try{
            Access access = new Access();
            
            String sql = "SELECT * FROM Patients WHERE patId='"+id+"' and passwd='"+pw+"'";
        
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
    /**
    *Function that checks for appointments using patient id
    *
    *@param id -- Patient id
    *@return    True if Patient has an existing appointment.
    */
    private boolean validateAppt(String id){
        
        String validate="";
        
        try{
            Access access = new Access();
            
            String sql = "SELECT * FROM Appointments WHERE patId='"+id+"'";
        
            ResultSet rs = access.getStatement().executeQuery(sql);
            
            if(rs.next()){
                validate=rs.getString(2);
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
