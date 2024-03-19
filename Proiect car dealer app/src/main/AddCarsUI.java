package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class AddCarsUI extends JFrame {

	private JPanel contentPane;
	private JTextField vinTextField;
	private JTextField priceTextField;
	private JTextField capacityTextField;
	private JTextField kmTextField;
	private JTextField colorTextField;
	
	String source;
	JLabel imgLabel = new JLabel();
	
	private Map<String, List<String>> makeAndModel = new LinkedHashMap<String, List<String>>();
	
	protected String VIN_number;
	protected String make;
	protected String model;
	protected int year;
	protected int price;
	protected int capacity;
	protected int km;
	protected String fuel;
	protected String color;

	public AddCarsUI() {
		
		buildDataModel();

		setBounds(100, 100, 559, 685);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Vin number
		JLabel lblNewLabel = new JLabel("VIN Number");
		lblNewLabel.setBounds(53, 205, 70, 14);
		contentPane.add(lblNewLabel);
		
		vinTextField = new JTextField();
		vinTextField.setBounds(131, 202, 86, 20);
		contentPane.add(vinTextField);
		vinTextField.setColumns(10);
		
		//Make
		JLabel lblNewLabel_1 = new JLabel("Make");
		lblNewLabel_1.setBounds(350, 31, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JComboBox<String> makeCmb = new JComboBox<String>();
		makeCmb.setBounds(428, 28, 57, 20);
		makeCmb.addItem("Selectati Marca");
        for(String value: makeAndModel.keySet()) {
        	makeCmb.addItem(value);
        }
		contentPane.add(makeCmb);
		
		//Model
		JLabel lblNewLabel_2 = new JLabel("Model");
		lblNewLabel_2.setBounds(350, 73, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		JComboBox<String> modelCmb = new JComboBox<String>();
		modelCmb.setBounds(428, 70, 57, 20);
		makeCmb.addActionListener(new ActionListener() {
        	
        	@SuppressWarnings("unchecked")
            @Override
			public void actionPerformed(ActionEvent e) {
        		JComboBox<String> source = (JComboBox<String>) e.getSource();
				String selectedMake = source.getSelectedItem().toString();
				List<String> models = makeAndModel.get(selectedMake);
				modelCmb.removeAllItems();
				if(models==null) {
					modelCmb.addItem("Selectati Marca");
				}else {
					modelCmb.addItem("Selectati Modelul");
					for(String name:models) {
						modelCmb.addItem(name);
					}
				}
			}
		});
		contentPane.add(modelCmb);
		
		//Year
		JLabel lblNewLabel_3 = new JLabel("Year");
		lblNewLabel_3.setBounds(350, 120, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		JComboBox<String> yearCmb = new JComboBox<String>();
		yearCmb.setBounds(428, 117, 57, 20);
		for(int i=1995 ; i<2021 ; i++) {
			yearCmb.addItem(Integer.toString(i));
		}
		contentPane.add(yearCmb);
		
		//Price
		JLabel lblNewLabel_4 = new JLabel("Price");
		lblNewLabel_4.setBounds(53, 245, 46, 14);
		contentPane.add(lblNewLabel_4);
		
		priceTextField = new JTextField();
		priceTextField.setBounds(131, 242, 86, 20);
		contentPane.add(priceTextField);
		priceTextField.setColumns(10);
		
		//Capacity
		JLabel lblNewLabel_5 = new JLabel("Capacity");
		lblNewLabel_5.setBounds(53, 286, 46, 14);
		contentPane.add(lblNewLabel_5);
		
		capacityTextField = new JTextField();
		capacityTextField.setBounds(131, 283, 86, 20);
		contentPane.add(capacityTextField);
		capacityTextField.setColumns(10);
		
		//KM
		JLabel lblNewLabel_6 = new JLabel("KM");
		lblNewLabel_6.setBounds(292, 197, 46, 14);
		contentPane.add(lblNewLabel_6);
		
		kmTextField = new JTextField();
		kmTextField.setBounds(370, 194, 86, 20);
		contentPane.add(kmTextField);
		kmTextField.setColumns(10);
		
		//Fuel
		JLabel lblNewLabel_7 = new JLabel("Fuel");
		lblNewLabel_7.setBounds(292, 237, 46, 14);
		contentPane.add(lblNewLabel_7);
		
		JComboBox fuelCmb = new JComboBox();
		fuelCmb.setBounds(370, 234, 86, 20);
		fuelCmb.addItem("DIESEL");
        fuelCmb.addItem("BENZINA");
        fuelCmb.addItem("GAZ");
		contentPane.add(fuelCmb);
		
		//Color
		JLabel lblColor = new JLabel("Color");
		lblColor.setBounds(292, 283, 46, 14);
		contentPane.add(lblColor);
		
		colorTextField = new JTextField();
		colorTextField.setBounds(370, 280, 86, 20);
		contentPane.add(colorTextField);
		colorTextField.setColumns(10);
		
		//Image
		imgLabel.setBounds(34, 11, 276, 140);
		imgLabel.setBorder(BorderFactory.createLineBorder(Color.black));
		contentPane.add(imgLabel);
		
		JButton btnBrowse = new JButton("Browse");
		btnBrowse.setBounds(34, 157, 89, 23);
		btnBrowse.addActionListener(new ActionListener(){
	        @Override
	        public void actionPerformed(ActionEvent e){
	            JFileChooser fileChooser = new JFileChooser();
	            fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
	            FileNameExtensionFilter filter = new FileNameExtensionFilter("*.IMAGE", "jpg","gif","png");
	            fileChooser.addChoosableFileFilter(filter);
	            int result = fileChooser.showSaveDialog(null);
	            if(result == JFileChooser.APPROVE_OPTION){
	                File selectedFile = fileChooser.getSelectedFile();
	                String path = selectedFile.getAbsolutePath();
	                imgLabel.setIcon(ResizeImage(path));
	                source = path;
	                 }
	            else if(result == JFileChooser.CANCEL_OPTION){
	                System.out.println("No Data");
	            }
	        }
	       });
		contentPane.add(btnBrowse);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(409, 589, 89, 23);
		btnAdd.addActionListener(new ActionListener(){
		    
		       @Override
		       public void actionPerformed(ActionEvent e){
		    	   
		    	   VIN_number = vinTextField.getText();
		    	   make = makeCmb.getSelectedItem().toString();
		    	   model = modelCmb.getSelectedItem().toString();
		    	   year = Integer.parseInt(yearCmb.getSelectedItem().toString());
		    	   price = Integer.parseInt(priceTextField.getText());
		    	   capacity = Integer.parseInt(capacityTextField.getText());
		    	   km = Integer.parseInt(kmTextField.getText());
		    	   fuel = fuelCmb.getSelectedItem().toString();
		    	   color = colorTextField.getText();
		    	   
		           try{

		               InputStream is = new FileInputStream(new File(source));
		               AddCarsQuery addCar = new AddCarsQuery();
		               addCar.add(VIN_number, make, model, year, price, capacity, km, fuel, color, is);
		               	   				
		               JOptionPane.showMessageDialog(null, "Data Inserted");
		           }catch(Exception ex){
		               ex.printStackTrace();
		           }
		       }
		    });
		contentPane.add(btnAdd);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(34, 589, 89, 23);
		btnCancel.addActionListener(new ActionListener(){
	        @Override
	        public void actionPerformed(ActionEvent e){
	        	closeWindow();
	        }
	       });
		contentPane.add(btnCancel);
		
		JTextArea txtrDescription = new JTextArea();
		txtrDescription.setText("Description");
		txtrDescription.setBounds(53, 340, 432, 192);
		contentPane.add(txtrDescription);
		
		setVisible(true);
	}
	
	public ImageIcon ResizeImage(String imgPath){
        ImageIcon MyImage = new ImageIcon(imgPath);
        Image img = MyImage.getImage();
        Image newImage = img.getScaledInstance(imgLabel.getWidth(), imgLabel.getHeight(),Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImage);
        return image;
    }
	
	private void closeWindow() {
		if (JOptionPane.showConfirmDialog(this, 
                "Are you sure you want to close this window?", "Close Window?", 
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
                dispose();
            }
    	}
	private void buildDataModel() {
        makeAndModel.put("BMW",
                Arrays.asList("Seria 1" , "Seria 2", "Seria 3" , "Seria 4" , "X6"));
        makeAndModel.put("Opel",
                Arrays.asList("Astra H" , "Astra G" , "Astra J" , "Corsa" , "GRANDLAND X"));
        makeAndModel.put("Audi",
                Arrays.asList("A3" , "A4" , "A8" , "Q5" , "Q3"));
        makeAndModel.put("VW",
                Arrays.asList("Passat" , "Polo" , "Golf" , "Tiguan" , "Tuareg"));
        makeAndModel.put("Dacia",
                Arrays.asList("Logan" , "1310" , "Duster" , "Sandero" , "Papuc"));
        //Add other data
    }
}
