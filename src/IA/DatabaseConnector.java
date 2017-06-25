package IA;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.mysql.jdbc.Statement;




public class DatabaseConnector {
	
	//Scanner
		Scanner userinput = new Scanner(System.in);
	PreparedStatement insertStatement = null;
	PreparedStatement insertUsername = null;
	PreparedStatement viewStatement = null;
	Connection conn = null;
	
	String photodbname = "phototable";
	String userdbname = "userdetails";
	String insertstring = "insert into " + photodbname + ".photo (path) values (?)" ;
	String insertusername = "insert into " + userdbname + ".userdetails (path) values (?)";
	String viewstring = "select * from " + photodbname + ".photo";
	String updateusername = "update " + userdbname + " where 'ID' = " + 1;
	
	//photodb
	boolean connectphotodb(String username, String password){
		try{
		username = "root";
				password = "1234";
			conn = DriverManager.getConnection("jdbc:mysql://localhost/" + photodbname +"?user=" + username + "&password=" + password);
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
	
	//Add directory to phototable
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
	
	//Connector to userdatabase
	boolean connectuserdb(String username, String password){
		try{
			Connection myconn = DriverManager.getConnection("jdbc:mysql://localhost/" + userdbname + "?user=" + username + "&password=" + password);
			insertUsername = conn.prepareStatement(updateusername);
			return true;
		}
		catch(SQLException ex){
			System.out.println("Error! Cannot connect to user database");
			System.out.println("SQL Exception: " + ex.getMessage());
			System.out.println("SQLState" + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		return false;
		}
	}
	
	//Insert username into userdatabase
	boolean changeUsername(String username){
		try{
			insertUsername.setString(2,username);
			insertUsername.execute();
			return true;
		}
		catch(SQLException e){
			System.out.println("Add username error");
		return false;
		}
	}
	
	boolean searchengine(String search){
	try{
		//Search statement
		Statement stmt = (Statement) conn.createStatement();
		
		//**ERROR HERE
		String SQL = "SELECT * FROM phototable WHERE COLUMN LIKE= '%" + search + "%'";
		
		//Does not pass this line
		ResultSet rs = stmt.executeQuery(SQL);
		
		while (rs.next()){
			System.out.println("Directory: " + rs.getString("path"));
		}
	return true;
	}
	catch(Exception e ){
		System.out.println("Search error!");
		return false;
	}
	}

//Extending array connection to database
ArrayList<Photo> getPhotos(){
	try {
		ResultSet results =	viewStatement.executeQuery();
		ArrayList<Photo> photos= new ArrayList<Photo>(); 
		while(results.next()){
			String path = results.getString("path");
			int id = results.getInt("id");
			String name = results.getString("name");
			photos.add(new Photo(id, path, name));
		}
		return photos;
	} catch (SQLException e) {
		System.out.println("Get photo error");
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return new ArrayList<Photo>();
}
}
