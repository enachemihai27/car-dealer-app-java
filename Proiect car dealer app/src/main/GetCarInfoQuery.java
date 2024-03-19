package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import tables.Cars;

public class GetCarInfoQuery {

	protected String VIN_number;
	protected String make;
	protected String model;
	protected int year;
	protected int price;
	protected int capacity;
	protected int km;
	protected String fuel;
	protected String color;
	private byte[] Image;	
	
	String SQL = "SELECT * FROM cars WHERE VIN_number = ?";
	
	public GetCarInfoQuery() {
		
	}
	
	public Cars getSelectedCar(String index) {
		 ResultSet rs;
		 Cars car = null;
		 try(	Connection con = DBConnection.getConnection();
				PreparedStatement stmt = con.prepareStatement(SQL);
			){
		 		   stmt.setString(1, index);
				   rs = stmt.executeQuery();
						
				   while(rs.next()){
					   car = new Cars(
					   rs.getString("VIN_number"),
					   rs.getString("Make"),
					   rs.getString("Model"),
					   rs.getInt("Year"),
					   rs.getInt("Price"),
					   rs.getInt("Capacity"),
					   rs.getInt("KM"),
					   rs.getString("Fuel"),
					   rs.getString("Color"),
					   rs.getBytes("Image"),
					   rs.getString("Nr_tel")
					   );
				   }
					
				} catch(Exception e) {
					e.printStackTrace();
				}

		 return car;
	}
}
