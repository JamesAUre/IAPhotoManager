package IA;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by HP on 5/9/2017.
 */
public class Connector {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306";
        String username = "Java";
        String password = "1234";

        System.out.println("Connecting database...");

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Database connected!");
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }
}
