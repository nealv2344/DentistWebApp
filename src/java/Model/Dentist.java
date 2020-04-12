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
            ResultSet result = databaseAccess.getStatement().executeQuery(sql);            

            result.next();
            
            //set properties
            setId(result.getString(1)); 
            setPw(result.getString(2));
            setFname(result.getString(3)); 
            setLname(result.getString(4));                                               
            setEmail(result.getString(5));
            setOfficeNum(result.getString(6));                 
            
            System.out.println("Customer " + Id + " Successfully selected" + System.lineSeparator());
            
            databaseAccess.close();            
        } 
        catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Exception caught - " + ex + System.lineSeparator());
        }
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
    
    
    
    public void display(){
       System.out.println("Id: "+getId()+"\t Password: "+getPw()+"\t Fname: "+getFname()+"\t Lname: "+getLname()+"\t email: "+getEmail()+"\t OfficeNum: "+getOfficeNum());
    }
    
    
    public static void main(String[]args){
        Dentist d1 = new Dentist();
        d1.selectDB("D206");
        d1.display();
        
        
    }
    
    
    
}
