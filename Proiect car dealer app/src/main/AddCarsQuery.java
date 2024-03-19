package main;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class AddCarsQuery {
	
	public AddCarsQuery() {	
	}
	
	public void add(String VIN_number, String make, String model, int year, int price, int capacity, int km, String fuel, String color, InputStream image) {
		
		String SQL = "INSERT INTO cars (VIN_number,Make,Model,Year,Price,Capacity,KM,Fuel,Color,Image) VALUES (?,?,?,?,?,?,?,?,?,?)";
		
		try(Connection con = DBConnection.getConnection();
			PreparedStatement stmt = con.prepareStatement(SQL);
		   ){

	            stmt.setString(1, VIN_number);
				stmt.setString(2, make);
				stmt.setString(3, model);
				stmt.setInt(4, year);
				stmt.setInt(5, price);
				stmt.setInt(6, capacity);
				stmt.setInt(7, km);
				stmt.setString(8, fuel);
				stmt.setString(9, color);
				stmt.setBlob(10,image);
				stmt.execute();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
