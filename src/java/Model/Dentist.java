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
            setFname(result.getString(1));
            setLname(result.getString(2));
            setEmail(result.getString(3));
            setId(result.getString(4));
            setPw(result.getString(6));
            setOfficeNum(result.getString(5));
            
            System.out.println("Customer " + Id + " Successfully selected" + System.lineSeparator());
            
            databaseAccess.close();            
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
        d1.selectDB("D201");
        d1.display();
    }
    
    
    
}
