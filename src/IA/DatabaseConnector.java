package IA;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseConnector {
	PreparedStatement insertStatement = null;
	Connection conn = null;
	
	String dbname = "phototable";
	String insertstring = "insert into " + dbname + ".photo (path) values (?)" ;
	boolean connect(){
		try{
		
			conn = DriverManager.getConnection("jdbc:mysql://localhost/" + dbname +"?user=root&password=1234");
			insertStatement = conn.prepareStatement(insertstring);
			return true;
		}
		catch(SQLException ex){
			System.out.println("SQL Exception: " + ex.getMessage());
			System.out.println("SQLState" + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} 
		return false;
	}
	
	boolean addFile(String path){
		try {
			insertStatement.setString(1, path);
			insertStatement.execute();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
