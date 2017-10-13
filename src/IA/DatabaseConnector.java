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
PreparedStatement deleteStatement = null;
PreparedStatement viewStatement = null;
Connection conn = null;
String photodbname = "phototable";
String insertstring = "insert into " + photodbname + ".photo (path) values (?)" ;
String viewstring = "select * from " + photodbname + ".photo";
String insertuserdetails = "insert into " + photodbname + ".userdetails (password) values (?)";
String viewuserdetails = "select * from " + photodbname + ".userdetails";
String deletestring = "delete from" + photodbname + ".photo (path) values (?)";
//photodb
boolean connectphotodb(String username, String password){
try{
username = "root";
password = "1234";
conn = DriverManager.getConnection("jdbc:mysql://localhost/" + photodbname +"?user=" + username + "&password=" + password);
insertStatement = conn.prepareStatement(insertstring);
viewStatement = conn.prepareStatement(viewstring);
insertUsername = conn.prepareStatement(insertuserdetails);
deleteStatement = conn.prepareStatement(deletestring);
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
boolean connectusertable(String username, String password){
try{
username = "root";
password = "1234";
conn = DriverManager.getConnection("jdbc:mysql://localhost/" + photodbname +"?user=" + username + "&password=" + password);
insertStatement = conn.prepareStatement(insertuserdetails);
viewStatement = conn.prepareStatement(viewuserdetails);
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
boolean deleteFile(String path){
try{
deleteStatement.setObject(1, path);
deleteStatement.execute();
return true;
}
catch(SQLException e){
System.out.println("Delete file error");
e.printStackTrace();
}
return false;
}
boolean addUsername(String username){
try {
insertUsername.setString(1, username);
insertUsername.execute();
return true;
} catch (SQLException e) {
System.out.println("Add username error");
// TODO Auto-generated catch block
e.printStackTrace();
}
return false;
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
ResultSet results = viewStatement.executeQuery();
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
