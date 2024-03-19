package main;

import tables.Cars;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Application {
	
	
	

	public static void main(String[] args) throws SQLException {
		
		Application app = new Application();
		app.mainMenu();

	}

	private void mainMenu() {
		MenuWindow menu = new MenuWindow();
		
	}

}
