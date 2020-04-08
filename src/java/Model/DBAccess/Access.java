/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DBAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Neal Valdez
 */
public class Access {
    
    private String DATABASEPATH = "\"C:\\Users\\Neal Valdez\\Desktop\\java3\\DentistWebApp\\dbStorage\\DentistOfficeACCDB.accdb\"";
	private Connection CONNECTION;
	private Statement STATEMENT;
        
    
	public Access() throws ClassNotFoundException, SQLException {
		System.out.println("Connecting to database....");
		Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		CONNECTION = DriverManager.getConnection("jdbc:ucanaccess://" + DATABASEPATH);
		System.out.println("Connected");
		STATEMENT = CONNECTION.createStatement();
	}
        
        public Connection getConnection(){
            return CONNECTION;
        }

	public Statement getStatement() {
		return STATEMENT;
	}

	public void close() throws SQLException{
		CONNECTION.close();
		System.out.println("Connection closed.");
	}
    
}
