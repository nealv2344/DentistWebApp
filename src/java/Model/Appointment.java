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
public class Appointment {
    String dateTime;
    String patId;
    String dentId;
    String procCode; 
    
    
    public Appointment(){
        this("","","","");
    }

    public Appointment(String dateTime,String patId,String dentId,String procCode){
        this.dateTime = dateTime;
        this.patId = patId;
        this.dentId = dentId;
        this.procCode = procCode;
    }
    
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

    public void selectDB(String patId){
        try{
        Access access = new Access();
        
        String sql = "select * from Appointments where patId='"+patId+"'";
        ResultSet result = access.getStatement().executeQuery(sql);
        
        result.next();
        setDateTime(result.getString(1));
        setProcCode(result.getString(2));        
        setDentId(result.getString(3));
        setPatId(result.getString(4));
        
        
            System.out.println("Appointment selected");
        
        }catch(ClassNotFoundException | SQLException ex){
            System.out.println("Exception caught: "+ ex + System.lineSeparator());
        }
    }
    
    public void insertDB(){
        try {
             Access databaseAccess = new Access();
            
            //setup statment
            String sql = "INSERT INTO Appointments " +
                         "VALUES('" +getDateTime()+ "', '" + getProcCode()+ "', '" + getDentId()+ "', '" + getPatId() + "')";             
            
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
    
    public void display(){
       System.out.println("Date/Time: "+getDateTime()+"\t PatId: "+getPatId()+"\t DentId: "+getDentId()+"\t ProcCode: "+getProcCode());
    }
    
    public static void main (String[]args){
        
//        Appointment a1 = new Appointment("May 12, 2018, 10am", "A912","D201","P321");
//        a1.insertDB();

//        Appointment a2 = new Appointment();
//        a2.selectDB("A912");
//        a2.display();
        
        Appointment a3 = new Appointment();
        a3.selectDB("A900");
        a3.display();
    }
    
    
}

