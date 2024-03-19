package tables;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Cars {
	
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
	protected String Nr_tel;
	
	public String getNr_tel() {
		return Nr_tel;
	}




	public Cars(String VIN_number, String make, String model, int year, int price, int capacity, int km, String fuel, String color, byte[] Image) {
		this.VIN_number = VIN_number;
		this.make = make;
		this.model = model;
		this.year = year;
		this.price = price;
		this.capacity = capacity;
		this.km = km;
		this.fuel = fuel;
		this.color = color;
		this.Image = Image;
	}




	public Cars(String VIN_number, String make, String model, int year, int price, int capacity, int km, String fuel, String color, byte[] Image, String Nr_tel) {
		this.VIN_number = VIN_number;
		this.make = make;
		this.model = model;
		this.year = year;
		this.price = price;
		this.capacity = capacity;
		this.km = km;
		this.fuel = fuel;
		this.color = color;
		this.Image = Image;
		this.Nr_tel = Nr_tel;
	}




	public static void getCar(ResultSet rs) throws SQLException {
		while(rs.next()) {
			StringBuffer buffer = new StringBuffer();
			
			buffer.append("Car Make" + rs.getString("Make"));
			buffer.append("Car Model" + rs.getString("Model"));
			System.out.println(buffer.toString());
		}
	}




	public String getVIN_number() {
		return VIN_number;
	}




	public String getMake() {
		return make;
	}




	public String getModel() {
		return model;
	}




	public int getYear() {
		return year;
	}




	public int getPrice() {
		return price;
	}




	public int getCapacity() {
		return capacity;
	}




	public int getKm() {
		return km;
	}




	public String getFuel() {
		return fuel;
	}




	public String getColor() {
		return color;
	}




	public byte[] getImage() {
		return Image;
	}
	
	
	
}
