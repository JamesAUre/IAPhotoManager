package IA;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
	Connection conn = null;
	
	boolean connect(){
		try{
		
			conn = DriverManager.getConnection("jdbc:mysql://localhost/sys?user=root&password=1234");
			return true;
		}
		catch(SQLException ex){
			System.out.println("SQL Exception: " + ex.getMessage());
			System.out.println("SQLState" + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} 
		return false;
	}
}
