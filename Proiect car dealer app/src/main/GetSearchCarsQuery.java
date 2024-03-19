package main;

import tables.*;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class GetSearchCarsQuery {
	

	protected String make;
	protected String model;
	protected int year;
	protected int minPrice;
	protected int maxPrice;
	protected int capacity;
	protected int minKM;
	protected int maxKM;
	protected String fuel;
	protected String color;
	private byte[] Image;
	
	String SQL = "SELECT * FROM cars WHERE make = ? AND model = IFNULL(?,model) AND year >= ? AND price >= ? AND price <= ? AND capacity <= ? AND km >= ? AND km <= ? AND fuel = IFNULL(?,fuel) AND color = IFNULL(?,color)";
	
	
	
	
	public GetSearchCarsQuery(String make, String model, int year, int minPrice, int maxPrice, int capacity, int minKM,
			int maxKM, String fuel, String color/*, byte[] image*/) {
		this.make = make;
		this.model = model;
		this.year = year;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
		this.capacity = capacity;
		this.minKM = minKM;
		this.maxKM = maxKM;
		this.fuel = fuel;
		this.color = color;
		//Image = image;
	}
	
	 public ArrayList<Cars> BindTable(){
		 ArrayList<Cars> carList = new ArrayList<Cars>();
		 ResultSet rs;
		 try(	Connection con = DBConnection.getConnection();
				PreparedStatement stmt = con.prepareStatement(SQL);
			){

						stmt.setString(1, make);
						stmt.setString(2, model);
						stmt.setInt(3, year);
						stmt.setInt(4, minPrice);
						stmt.setInt(5, maxPrice);
						stmt.setInt(6, capacity);
						stmt.setInt(7, minKM);
						stmt.setInt(8, maxKM);
						stmt.setString(9,fuel);
						stmt.setString(10, color);
						//stmt.execute();
						
						rs = stmt.executeQuery();
						
						   Cars car;
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
						   rs.getBytes("Image")
						   );
						   carList.add(car);
						   }
					
				} catch(Exception e) {
					e.printStackTrace();
				}

		   return carList;
		   }

}
