package IA;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseConnector {
	PreparedStatement insertStatement = null;
	PreparedStatement viewStatement = null;
	Connection conn = null;
	
	String dbname = "phototable";
	String insertstring = "insert into " + dbname + ".photo (path) values (?)" ;
	String viewstring = "select * from " + dbname + ".photo";
	boolean connect(){
		try{
		
			conn = DriverManager.getConnection("jdbc:mysql://localhost/" + dbname +"?user=root&password=1234");
			insertStatement = conn.prepareStatement(insertstring);
			viewStatement = conn.prepareStatement(viewstring);
			return true;
		}
		catch(SQLException ex){
			System.out.println("Connection error");
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
			System.out.println("Add file error");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

ArrayList<String> getPhotos(){
	try {
		ResultSet results =	viewStatement.executeQuery();
		ArrayList<String> paths= new ArrayList<String>(); 
		while(results.next()){
			paths.add(results.getString("path"));
		}
		return paths;
	} catch (SQLException e) {
		System.out.println("Get photo error");
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return new ArrayList<String>();
}
}
