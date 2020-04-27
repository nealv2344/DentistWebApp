/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Neal Valdez
 */
public class apptList{
    
    int count;
    public ArrayList<Appointment> appts = new ArrayList<>();
    
    public apptList(){
        
    }
    
    public int getCount(){
        return count;
    }
    
    public void setCount(int count){
        this.count = count;
    }
    
    public void add(Appointment appt){
        appts.add(appt);
        count++;
    }
    
    public void remove(Appointment appt){
        appts.remove(appt);
        count--;
    }
    
    
    
    
    
    public void displayList(){
        System.out.println("Appts Information"+ System.lineSeparator()+
                "==================");
        if(count == 0){
            System.out.println("No appts");
        }else{
            appts.forEach((a)->{
                System.out.println("Date/Time: " + a.getDateTime()+ System.lineSeparator()); 
                System.out.println("Patient Id: " + a.getPatId()+ System.lineSeparator());
                System.out.println("Dentist Id: " + a.getDentId()+ System.lineSeparator());
                System.out.println("Procedure Code: " + a.getProcCode()+ System.lineSeparator());
                System.out.println("================");
            });
        }        
    }
    
    public static void main (String args[]){
        
        apptList aL = new apptList();
        
        Appointment a = new Appointment("May 2, 2018 10am","A911","D201","P321");
        Appointment b = new Appointment("May 3, 2018 10am","A911","D201","P321");
        Appointment c = new Appointment("May 4, 2018 10am","A911","D201","P321");
        
        aL.add(a);
        aL.add(b);
        aL.add(c);
        
        aL.displayList();
        
    }
    
}
