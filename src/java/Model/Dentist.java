/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.DBAccess.Access;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Neal Valdez
 */
public class Dentist extends Person{
    
    String officeNum;
    apptList aL = new apptList();
    
    public Dentist() {
        this("", "", "", "", "","");
    }
    
    public Dentist(String Id, String password, String fname, String lname, String email, String officeNum){
        
        super(Id,password,fname,lname,email);
        
        this.officeNum = officeNum;
    }
    
    public String getOfficeNum(){
        return officeNum;
    }
    
    public void setOfficeNum(String officeNum){
        this.officeNum = officeNum;
    }
    
    
    
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
        
        retrieveApptList();
    }
    
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
    
    
    
    public void display(){
       System.out.println("Id: "+getId()+"\t Password: "+getPw()+"\t Fname: "+getFname()+"\t Lname: "+getLname()+"\t email: "+getEmail()+"\t OfficeNum: "+getOfficeNum());
       aL.displayList();
    }
    
    
    public static void main(String[]args){
        Dentist d1 = new Dentist();
        d1.selectDB("D201");
        d1.display();
        
        
    }
    
    
    
}
