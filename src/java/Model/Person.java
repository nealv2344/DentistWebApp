/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Neal Valdez
 */
public class Person {
    
    protected String Id;
    protected String password;
    protected String fname;
    protected String lname;
    protected String email;
    public apptList apptList;
    
    
    protected Person(){
        Id = "";
        password="";
        fname="";
        lname="";
        email="";        
    }
    
    protected Person(String Id, String password, String fname, String lname, String email){
        
        this.Id = Id;
        this.password = password;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
    }
    
    //get & set methods
    public String getId(){
        return Id;
    }
    
    public void setId(String Id){
        this.Id = Id;
    }
    
    public String getPw(){
        return password;
    }
    
    public void setPw(String password){
        this.password = password;
    }
                    
    public String getFname(){
        return fname;
    }
    
    public void setFname(String fname){
        this.fname = fname;
    }
    
    public String getLname(){
        return lname;
    }
    
    public void setLname(String lname){
        this.lname = lname;
    }
    
    public String getEmail(){
        return email;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    
    public apptList getAppts(){
        return apptList;
    }
    
    public void setApptList(apptList appts){
        this.apptList = appts;
    }
    
    public static void main(String [] args){
        Person p1 = new Person("Id","password","Fname","Lname","email");
        
        System.out.println(p1.getId());
        System.out.println(p1.getPw());
        System.out.println(p1.getFname());
        System.out.println(p1.getLname());
        System.out.println(p1.getEmail());
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
}
