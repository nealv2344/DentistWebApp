/*********************************************** 
 * Instructor: Ron Enz
 * Description: Appointment Business Object
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
import java.sql.ResultSet;
import java.sql.SQLException;


public class Appointment{
    String dateTime;
    String patId;
    String dentId;
    String procCode; 
    
    
    public Appointment(){
        this("","","","");
    }
    /**CONSTRUCTOR
     * @param dateTime
     * @param patId
     * @param dentId
     * @param procCode*/
    public Appointment(String dateTime,String patId,String dentId,String procCode){
        this.dateTime = dateTime;
        this.patId = patId;
        this.dentId = dentId;
        this.procCode = procCode;
    }
    /***GET/SET METHOD
     * @return set/get***/
    public String getDateTime(){
        return dateTime;
    }
    
    public void setDateTime(String dateTime){
        this.dateTime = dateTime;
    }
    
    public String getPatId(){
        return patId;
    }
    
    public void setPatId(String patId){
        this.patId = patId;
    }
    
    public String getDentId(){
        return dentId;
    }
    
    public void setDentId(String dentId){
        this.dentId = dentId;
    }
    
    public String getProcCode(){
        return procCode;
    }
    
    public void setProcCode(String procCode){
        this.procCode = procCode;
    }
    /**
    *Select method used to select an appointment from database
    *
    *@param patId -- Patient Id
    */
    public void selectDB(String patId){
        try{
        Access access = new Access();
        
        String sql = "select * from Appointments where patId='"+patId+"'";
        ResultSet rs = access.getStatement().executeQuery(sql);
        
        rs.next();
        setDateTime(rs.getString(1));
        setPatId(rs.getString(2));           
        setDentId(rs.getString(3));
        setProcCode(rs.getString(4)); 
        
        
            System.out.println("Appointment selected");
        
        }catch(ClassNotFoundException | SQLException ex){
            System.out.println("Exception caught: "+ ex + System.lineSeparator());
        }
    }
    /**
    *Insert method used for inserting new appointment to the database
    */
    public void insertDB(){
        try {
             Access databaseAccess = new Access();
            
            //setup statment
            String sql = "INSERT INTO Appointments " +
                         "VALUES('" +getDateTime()+ "', '" + getPatId()+ "', '" + getDentId()+ "', '" + getProcCode() + "')";             
            
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
    *Delete method used for deleting appointments from database
    *
    */
    public void deleteDB(){
        try{
            Access databaseAccess = new Access();
            
            //setup statment
            String sql = "DELETE FROM Appointments WHERE PatId = " + "'"+getPatId()+"'";          
            System.out.println(sql);
            //execute Deletion                                               
            int num = databaseAccess.getStatement().executeUpdate(sql);
            
            //deal with result
            if (num == 1){
                //debug to console
                System.out.println("Deletion successful!" + System.lineSeparator());
                //reset properties to blank values
                this.setDateTime("");
                this.setPatId("");
                this.setDentId("");
                this.setProcCode("");
            }else {
                //debug to console
                System.out.println("Deletion failed!" + System.lineSeparator());
            }                        
        } 
        catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Exception caught - " + ex + System.lineSeparator());
        }
        
        
    }
    /****DISPLAY METHOD****/
    public void display(){
       System.out.println("Date/Time: "+getDateTime()+"\t PatId: "+getPatId()+"\t DentId: "+getDentId()+"\t ProcCode: "+getProcCode());
    }
    /**
    * Main method used for testing purposes
    **/
    public static void main (String[]args){
        
        Appointment a1 = new Appointment("May 12, 2018, 10am", "A912","D201","P321");
        a1.insertDB();

//        Appointment a2 = new Appointment();
//        a2.selectDB("A912");
//        a2.display();
        
//        Appointment a3 = new Appointment();
//        a3.selectDB("A900");
//        a3.display();
    }
    
    
}

