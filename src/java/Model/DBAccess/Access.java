/*********************************************** 
 * Instructor: Ron Enz
 * Description: Method used to access database
 * @author Neal Valdez
 * @version 1.0
 *
 * By turning in this code, I Pledge:
 *  1. That I have completed the programming assignment independently.
 *  2. I have not copied the code from a student or any source.
 *  3. I have not given my code to any student.
 *
 ************************************************/
package Model.DBAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
public class Access {
    
    private String DATABASEPATH = "C:\\Users\\Neal Valdez\\Desktop\\java3\\DentistWebApp\\dbStorage\\DentistOfficeACCDB.accdb";
	private Connection CONNECTION;
	private Statement STATEMENT;
        
        /**
        *Access function used to make connection with the database.
        *@throws ClassNotFoundException
        *@throws SQLException
        */
	public Access() throws ClassNotFoundException, SQLException {
		System.out.println("Connecting to database....");
		Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		CONNECTION = DriverManager.getConnection("jdbc:ucanaccess://" + DATABASEPATH);
		System.out.println("Connected");
		STATEMENT = CONNECTION.createStatement();
	}
        /**
        *Used for getting connection to the database
        *
        *@return    Returns the ucanaccess databasepath
        */
        public Connection getConnection(){
            return CONNECTION;
        }
        /**
        *Used for initializing statement that will run sql statements
        *
        *@return    Returns statement for running sql
        */
	public Statement getStatement() {
		return STATEMENT;
	}
        /**
        *Close method that will close out connections to databaseS
        *
        *@throws SQLException
        */
	public void close() throws SQLException{
		CONNECTION.close();
		System.out.println("Connection closed.");
	}
    
}
