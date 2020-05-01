/*********************************************** 
 * Instructor: Ron Enz
 * Description: Appointment list utility object
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

import java.util.ArrayList;

/*******apptList*******/
public class apptList{
    
    int count;
    public ArrayList<Appointment> appts = new ArrayList<>();
    
    public apptList(){
        
    }
    /***GET/SET METHOD
     * @return S***/
    public int getCount(){
        return count;
    }
    
    public void setCount(int count){
        this.count = count;
    }
    
    /**
    *Method used to add appointment to 
    *appointment list.
    *
    *@param appt -- Appointment object
    */
    public void add(Appointment appt){
        appts.add(appt);
        count++;
    }
    /**
    *Method used to remove appointment from 
    *appointment list.
    *
    *@param appt -- Appointment object
    */
    public void remove(Appointment appt){
        appts.remove(appt);
        count--;
    }
    
    
    
    
    /******DISPLAY*******/  
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
    
    /**
    *Used for testing purposes
    */
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
