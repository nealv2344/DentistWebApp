/*********************************************** 
 * Instructor: Ron Enz
 * Description: Dentist Business Object
 * @author Neal Valdez
 * @version 1.0
 *
 * By turning in this code, I Pledge:
 *  1. That I have completed the programming assignment independently.
 *  2. I have not copied the code from a student or any source.
 *  3. I have not given my code to any student.
 *
 ************************************************/
package Model;

import Model.DBAccess.Access;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Dentist extends Person{
    
    String officeNum;
    public apptList aL = new apptList();
    
    public Dentist() {
        this("", "", "", "", "","");
    }
    /**CONSTRUCTOR
     * @param Id*
     * @param password*
     * @param fname*
     * @param lname*
     * @param email*
     * @param officeNum **/
    public Dentist(String Id, String password, String fname, String lname, String email, String officeNum){
        
        super(Id,password,fname,lname,email);
        
        this.officeNum = officeNum;
    }
    /***GET/SET METHOD
     * @return get/set methods***/
    public String getOfficeNum(){
        return officeNum;
    }
    
    public void setOfficeNum(String officeNum){
        this.officeNum = officeNum;
    }
    
    /**
    *Select method used to select a Dentist from database
    *
    *@param Id -- Dentist Id
    */    
    public void selectDB(String Id) {
        try{
            Access databaseAccess = new Access();
            
            //execute statment
            String sql = "select * from Dentists WHERE id = '" + Id + "'";             
            System.out.println(sql);
            ResultSet result = databaseAccess.getStatement().executeQuery(sql);            

            result.next();
            
            //set properties
            setId(result.getString(1)); 
            setPw(result.getString(2));
            setFname(result.getString(3)); 
            setLname(result.getString(4));                                               
            setEmail(result.getString(5));
            setOfficeNum(result.getString(6));                 
            
            System.out.println("Dentist " + Id + " Successfully selected" + System.lineSeparator());
            
            databaseAccess.close();            
        } 
        catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Exception caught - " + ex + System.lineSeparator());
        }
        
    }
    /**
    *Insert method used for inserting new dentist to the database
    */
    public void insertDB(){
        try {
             Access databaseAccess = new Access();
            
            //setup statment
            String sql = "INSERT INTO Dentists " +
                         "VALUES('" +getId()+ "', '" + getPw()+ "', '" + getFname()+ "', '" + getLname()+ "', '" + getEmail()+ "', '" + getOfficeNum()+ "')";             
            
            //execute insertion                         
            int num = databaseAccess.getStatement().executeUpdate(sql);
            
            //deal with result
            if (num == 1){
                
                //debug to console
                System.out.println("Insert successful!" + System.lineSeparator());
                
            }else {
                //debug to console
                System.out.println("Insert failed!" + System.lineSeparator());
            }
            
            databaseAccess.close();

        }

        catch (ClassNotFoundException | SQLException e){
            System.out.println("Exception caught - " + e + System.lineSeparator());
        }
    }
    /**
    *Update method used for updating dentist info from database
    *
    */
    public void updateDB(){
        
        try{
            //loading driver
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            
            //get connection
            Connection con = 
                    DriverManager.getConnection("jdbc:ucanaccess://C:\\Users\\Neal Valdez\\Desktop\\java3\\DentistWebApp\\dbStorage\\DentistOfficeACCDB.accdb");
                
            //execute the statement
            String sql;
            sql = "UPDATE Dentists SET id=?,passwd=?,firstName=?,lastName=?,email=?,office=? WHERE id='" + Id + "'";
            System.out.println(sql);
            //create the Statement
            PreparedStatement stmt = con.prepareStatement(sql);
            
            stmt.setString(1,Id);
            stmt.setString(2,password);
            stmt.setString(3,fname);
            stmt.setString(4,lname);
            stmt.setString(5,email);
            stmt.setString(6,officeNum);
            stmt.executeUpdate();
            
            System.out.println("Dentist "+getId()+" successfully updated");
            con.close();
            
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("Exception caught - " + e + System.lineSeparator());
            e.printStackTrace();
        }
    }
    /**
    *Delete method used for deleting dentists from database
    *
    */
    public void deleteDB() {
        
        try{
            Access databaseAccess = new Access();
            
            //setup statment
            String sql = "DELETE FROM Dentists WHERE id = " + "'"+getId()+"'";          
            System.out.println(sql);
            //execute Deletion                                               
            int num = databaseAccess.getStatement().executeUpdate(sql);
            
            //deal with result
            if (num == 1){
                //debug to console
                System.out.println("Deletion successful!" + System.lineSeparator());
                //reset properties to blank values
                this.setId("");
                this.setPw("");
                this.setFname("");
                this.setLname("");
                this.setEmail("");
                this.setOfficeNum("");
            }else {
                //debug to console
                System.out.println("Deletion failed!" + System.lineSeparator());
            }                        
        } 
        catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Exception caught - " + ex + System.lineSeparator());
            
        }
        
    }
    /**
    * Retrieve appointments by dentist id.
    * Adds appointments into arraylist.
    */
    public void retrieveApptList(){
        
        try{
            Access access = new Access();
            
            String sql = "SELECT * FROM Appointments WHERE dentId= '"+getId()+"'";
            System.out.println(sql);
            ResultSet rs = access.getStatement().executeQuery(sql);
            
            String patId;
            Appointment a;
            
            while(rs.next()){
                patId = rs.getString(2);  
                System.out.println(patId);
                a = new Appointment();
                a.selectDB(patId); 
                aL.add(a);
            }
            System.out.println("Dentist appts for: " + getFname());                        
            
            access.close();
            
        }catch(ClassNotFoundException | SQLException ex){
            System.out.println("Exception Caught - " + ex);
        }
    }
    
    
    /******DISPLAY*******/
    public void display(){
       System.out.println("Id: "+getId()+"\t Password: "+getPw()+"\t Fname: "+getFname()+"\t Lname: "+getLname()+"\t email: "+getEmail()+"\t OfficeNum: "+getOfficeNum());
       aL.displayList();
    }
    
    /**
    *Used for testing purposes
     * @param args
    */
    public static void main(String[]args){
//        Dentist d1 = new Dentist();
//        d1.selectDB("D202");
//        d1.display();
        
//        Dentist d2 = new Dentist();
//        d2.selectDB("D201");
//        d2.setPw("newPass");
//        d2.updateDB();
    }
    
    
    
}
