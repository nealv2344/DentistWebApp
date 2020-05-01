/*********************************************** 
 * Instructor: Ron Enz
 * Description: Patient Business Object
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


public class Patient extends Person{
    String address;
    String insCo;
    
    public Patient(){
    this("","","","","","","");
}
    /**CONSTRUCTOR
     * @param Id
     * @param password
     * @param fname
     * @param lname
     * @param address
     * @param email
     * @param insCo*/
    public Patient(String Id,String password, String fname, String lname, String address, String email, String insCo){
        super(Id,password,fname,lname,email);
        
        this.address = address;
        this.insCo = insCo;
        
    }
    /***GET/SET METHOD
     * @return S***/
    public String getAddress(){
        return address;
    }
    
    public void setAddress(String address){
        this.address = address;
    }
    
    public String getInsCo(){
        return insCo;
    }
    
    public void setInsCo(String insCo){
        this.insCo = insCo;
    }
    /**
    *Select method used to select a Patient from database
    *
    *@param Id -- Patient Id
    */
    public void selectDB(String Id){
        try{
            Access databaseAccess = new Access();
            
            //execute statment
            String sql = "select * from Patients WHERE patId = '" + Id + "'";             
            ResultSet result = databaseAccess.getStatement().executeQuery(sql);            

            result.next();
            
            //set properties
            setId(result.getString(1)); 
            setPw(result.getString(2));
            setFname(result.getString(3)); 
            setLname(result.getString(4));                                               
            setAddress(result.getString(5));
            setEmail(result.getString(6));
            setInsCo(result.getString(7));
            
            System.out.println("Patient " + Id + " Successfully selected" + System.lineSeparator());
            
            databaseAccess.close();            
        } 
        catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Exception caught - " + ex + System.lineSeparator());
        }
    }
    /**
    *Insert method used for inserting new patient to the database
    */
    public void insertDB(){
        try {
             Access databaseAccess = new Access();
            
            //setup statment
            String sql = "INSERT INTO Patients " +
                         "VALUES('" +getId()+ "', '" + getPw()+ "', '" + getFname()+ "', '" + getLname()+ "', '" + getAddress()+ "', '" + getEmail()+ "', '" + getInsCo()+ "')";             
            
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
    *Update method used for updating patient info from database.
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
            sql = "UPDATE Patients SET patId=?,passwd=?,firstName=?,lastName=?,addr=?,email=?,insCo=? WHERE patId='" + Id + "'";
            System.out.println(sql);
            //create the Statement
            PreparedStatement stmt = con.prepareStatement(sql);
            
            stmt.setString(1,Id);
            stmt.setString(2,password);
            stmt.setString(3,fname);
            stmt.setString(4,lname);
            stmt.setString(5,address);
            stmt.setString(6,email);
            stmt.setString(7,insCo);
            stmt.executeUpdate();
            
            System.out.println("Patient "+getId()+" successfully updated");
            con.close();
            
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("Exception caught - " + e + System.lineSeparator());
            e.printStackTrace();
        }
    }
    /**
    *Delete method used for deleting Patients from database
    */
    public void deleteDB(){
        try{
            Access databaseAccess = new Access();
            
            //setup statment
            String sql = "DELETE FROM Patients WHERE patId = " + "'"+getId()+"'";          
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
                this.setAddress("");
                this.setEmail("");
                this.setInsCo("");
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
    *Method to retrieve the amount of rows in patient table.
    *Used for autogenerating patient id
    */
    public static int getRowCount(){
        int count=0;
        
        try{
            Access databaseAccess = new Access();
            
            //execute statment
            String sql = "select * from Patients";             
            ResultSet result = databaseAccess.getStatement().executeQuery(sql);            
            while(result.next()){
                count++;
            }
            
            return count;
        }catch(ClassNotFoundException | SQLException e){
            return 0;
        }
        
    }
    /******DISPLAY*******/    
    public void display(){
       System.out.println("Id: "+getId()+"\t Password: "+getPw()+"\t Fname: "+
                            getFname()+"\t Lname: "+getLname()+"\t Address: "+getAddress()+
                            "\t email: "+getEmail()+"\t InsCo: "+getInsCo());
    }
    
    
    /**
    *Used for testing purposes
    */
    public static void main(String[]args){
//        Patient p1 = new Patient();
//        p1.selectDB("A900");
//        p1.display();
//        
//        Patient p2 = new Patient();
//        p2.selectDB("A912");
//        p2.deleteDB();
        String id = "A9";
        int count = getRowCount();
        String num = Integer.toString(count);
        String newId="A9"+num;
        System.out.println(newId);
    }
    
    
    
    
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    



