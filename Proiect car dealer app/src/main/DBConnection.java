package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	private static final String USERNAME = "user";
	private static final String PASSWORD = "admin";
	private static final String CONNECTION = "jdbc:mysql://localhost/siteauto";
	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(CONNECTION,USERNAME,PASSWORD);
	}
}
