package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;

import tables.TheModel;
import tables.Cars;

import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;

public class SearchCarsUI extends JFrame {

	private JPanel contentPane;
	private JTextField minPriceTxt;
	private JTextField maxPriceTxt;
	private JTextField kmMaxTxt;
	private JTextField kmMinTxt;
	private JTextField capacityTxt;
	private JTextField colorTxt;
	private JTable carsTable;
	
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

	JPanel searchPanel = new JPanel();
	
	private Map<String, List<String>> makeAndModel = new LinkedHashMap<String, List<String>>();
	
	private JComboBox<String> makeListCmb;
	private JComboBox<String> modelListCmb;
	private JComboBox<String> yearCmb;
	private JComboBox<String> fuelCmb;
	private JScrollPane scrollPane_1;
	
	private ArrayList<Cars> carList;

	public SearchCarsUI() {
		
		setTitle("Gaseste masina");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 797, 706);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		searchPanel.setBounds(10, 11, 761, 133);
		contentPane.add(searchPanel);
		searchPanel.setLayout(null);
		
		//Se configureaza maparea marcilor cu modelele fiecareia
		buildDataModel();
		
		
		//Se construiesc cmbboxurile pt marca si model
		buildMakeAndModel();
		
		buildYear();
		
		buildPrice();
		
		buildKM();
		
		buildFuel();
		
		buildCapacity();
		
		buildColor();
		
		searchCar();
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 655, 761, -497);
		carsTable = new JTable();
		initTableComponents(scrollPane_1, carsTable);
		contentPane.add(scrollPane_1);
		
		setVisible(true);
	}
	
	private void searchCar() {
		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(336, 99, 89, 23);
		searchPanel.add(btnSearch);
		
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(makeListCmb.getSelectedItem().toString().equals("Selectati Marca"))
					JOptionPane.showMessageDialog(null,
		                    "Error: Please enter select a car make", "Error Message",
		                    JOptionPane.ERROR_MESSAGE);
				else {
					make = makeListCmb.getSelectedItem().toString();
					
					if(modelListCmb.getSelectedItem().toString().equals("Selectati Modelul")) {
						model = null;
					}else {
						model = modelListCmb.getSelectedItem().toString();
					}
					year = Integer.parseInt(yearCmb.getSelectedItem().toString());
					
					if(fuelCmb.getSelectedItem().toString().equals("ANY")) {
						fuel = null;
					}else {
						fuel = fuelCmb.getSelectedItem().toString();
					}
					
					if(minPriceTxt.getText().equals("")) {
						minPrice = 0;
					}else{
						minPrice = Integer.parseInt(minPriceTxt.getText());
					}
					
					if(maxPriceTxt.getText().equals("")) {
						maxPrice = 1000000;
					}else {
						maxPrice = Integer.parseInt(maxPriceTxt.getText());
					}
					
					if(capacityTxt.getText().equals("")) {
						capacity = 100000;
					}else {
						capacity = Integer.parseInt(capacityTxt.getText());
					}
					
					if(kmMinTxt.getText().equals("")) {
						minKM = 0;
					}else {
						minKM = Integer.parseInt(kmMinTxt.getText());
					}
					
					if(kmMaxTxt.getText().equals("")) {
						maxKM = 1000000;
					}else {
					maxKM = Integer.parseInt(kmMaxTxt.getText());;
					}
					
					if(colorTxt.getText().equals("")) {
						color = null;
					}else {
						color = colorTxt.getText();
					}
					System.out.println(make + model + year + minPrice + maxPrice + capacity + minKM + maxKM + fuel + color);
					populateCarsTable();
				}
			}
		});
		
	}

	private void buildColor() {
		JLabel colorLabel = new JLabel("Color");
		colorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		colorLabel.setBounds(226, 46, 46, 14);
		searchPanel.add(colorLabel);
		
		colorTxt = new JTextField();
		colorTxt.setBounds(283, 44, 86, 20);
		searchPanel.add(colorTxt);
		colorTxt.setColumns(10);
		
	}

	private void buildCapacity() {
		JLabel capacityLabel = new JLabel("Capacity");
		capacityLabel.setHorizontalAlignment(SwingConstants.CENTER);
		capacityLabel.setBounds(226, 10, 46, 14);
		searchPanel.add(capacityLabel);
		
		capacityTxt = new JTextField();
		capacityTxt.setBounds(282, 8, 86, 20);
		searchPanel.add(capacityTxt);
		capacityTxt.setColumns(10);
		
		capacityTxt.getDocument().addDocumentListener(new SimpleDocumentListener() {
			@Override
		    public void update(DocumentEvent e) {

		        if (Integer.parseInt(capacityTxt.getText())<0){
		            JOptionPane.showMessageDialog(null,
		                    "Error: Please enter number bigger than 0", "Error Message",
		                    JOptionPane.ERROR_MESSAGE);
		        }       
		    }
		});
	}

	private void buildFuel() {
		JLabel fuelLabel = new JLabel("Fuel");
		fuelLabel.setHorizontalAlignment(SwingConstants.CENTER);
		fuelLabel.setBounds(114, 46, 46, 14);
		searchPanel.add(fuelLabel);
		
		fuelCmb = new JComboBox<String>();
		fuelCmb.setBounds(178, 44, 48, 20);
		searchPanel.add(fuelCmb);
		
		 fuelCmb.addItem("DIESEL");
	     fuelCmb.addItem("BENZINA");
	     fuelCmb.addItem("GAZ");
	     fuelCmb.addItem("ANY");
	        
	}

	private void buildKM() {
		JLabel kmLabel = new JLabel("KM");
		kmLabel.setHorizontalAlignment(SwingConstants.CENTER);
		kmLabel.setBounds(379, 44, 46, 14);
		searchPanel.add(kmLabel);
		
		kmMaxTxt = new JTextField();
		kmMaxTxt.setColumns(10);
		kmMaxTxt.setBounds(640, 41, 86, 20);
		searchPanel.add(kmMaxTxt);
		
		kmMinTxt = new JTextField();
		kmMinTxt.setColumns(10);
		kmMinTxt.setBounds(488, 41, 86, 20);
		searchPanel.add(kmMinTxt);
		
		JLabel kmMinLabel = new JLabel("Min");
		kmMinLabel.setHorizontalAlignment(SwingConstants.CENTER);
		kmMinLabel.setBounds(435, 44, 46, 14);
		searchPanel.add(kmMinLabel);
		
		JLabel kmMaxLabel = new JLabel("Max");
		kmMaxLabel.setHorizontalAlignment(SwingConstants.CENTER);
		kmMaxLabel.setBounds(584, 44, 46, 14);
		searchPanel.add(kmMaxLabel);
		
		/*kmMinTxt.getDocument().addDocumentListener(new SimpleDocumentListener() {
			@Override
		    public void update(DocumentEvent e) {

		        if (Integer.parseInt(kmMinTxt.getText())<0){
		            JOptionPane.showMessageDialog(null,
		                    "Error: Please enter number bigger than 0", "Error Message",
		                    JOptionPane.ERROR_MESSAGE);
		        }       
		    }
		});
		
		kmMaxTxt.getDocument().addDocumentListener(new SimpleDocumentListener() {
			@Override
		    public void update(DocumentEvent e) {

		        if (Integer.parseInt(kmMaxTxt.getText())<0){
		            JOptionPane.showMessageDialog(null,
		                    "Error: Please enter number bigger than 0", "Error Message",
		                    JOptionPane.ERROR_MESSAGE);
		        }       
		    }
		});*/
		
	}

	private void buildPrice() {
		
		JLabel priceLabel = new JLabel("Price");
		priceLabel.setHorizontalAlignment(SwingConstants.CENTER);
		priceLabel.setBounds(379, 16, 46, 14);
		searchPanel.add(priceLabel);
		
		JLabel minPriceLabel = new JLabel("Min:");
		minPriceLabel.setHorizontalAlignment(SwingConstants.CENTER);
		minPriceLabel.setBounds(435, 16, 46, 14);
		
		minPriceTxt = new JTextField(10);
		minPriceTxt.setBounds(488, 13, 86, 20);
		minPriceTxt.setColumns(10);
		
		searchPanel.add(minPriceLabel);
		searchPanel.add(minPriceTxt);
		
		JLabel maxPriceLabel = new JLabel("Max:");
		maxPriceLabel.setHorizontalAlignment(SwingConstants.CENTER);
		maxPriceLabel.setBounds(584, 16, 46, 14);
		
		maxPriceTxt = new JTextField(8);
		maxPriceTxt.setBounds(640, 13, 86, 20);
		maxPriceTxt.setColumns(10);
		
		searchPanel.add(maxPriceLabel);
		searchPanel.add(maxPriceTxt);
		
		/*minPriceTxt.getDocument().addDocumentListener(new SimpleDocumentListener() {
			@Override
		    public void update(DocumentEvent e) {

		        if (Integer.parseInt(minPriceTxt.getText())<0){
		            JOptionPane.showMessageDialog(null,
		                    "Error: Please enter number bigger than 0", "Error Message",
		                    JOptionPane.ERROR_MESSAGE);
		        }       
		    }
		});
		
		maxPriceTxt.getDocument().addDocumentListener(new SimpleDocumentListener() {
			@Override
		    public void update(DocumentEvent e) {

		        if (Integer.parseInt(maxPriceTxt.getText())<0){
		            JOptionPane.showMessageDialog(null,
		                    "Error: Please enter number bigger than 0", "Error Message",
		                    JOptionPane.ERROR_MESSAGE);
		        }       
		    }
		});*/
		
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
	
	private void buildMakeAndModel() {
		JLabel makeLabel = new JLabel("Make");
		makeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		makeLabel.setBounds(10, 11, 46, 14);
		
		JLabel modelLabel = new JLabel("Model");
		modelLabel.setHorizontalAlignment(SwingConstants.CENTER);
		modelLabel.setBounds(10, 47, 46, 14);
		makeListCmb = new JComboBox<String>();
		makeListCmb.setBounds(66, 8, 46, 20);
        makeListCmb.addItem("Selectati Marca");
        for(String value: makeAndModel.keySet()) {
        	makeListCmb.addItem(value);
        }
        
        
        modelListCmb = new JComboBox<String>();
        modelListCmb.setBounds(66, 44, 46, 20);
        makeListCmb.addActionListener(new ActionListener() {
        	
        	@SuppressWarnings("unchecked")
            @Override
			public void actionPerformed(ActionEvent e) {
        		JComboBox<String> source = (JComboBox<String>) e.getSource();
				String selectedMake = source.getSelectedItem().toString();
				List<String> models = makeAndModel.get(selectedMake);
				modelListCmb.removeAllItems();
				if(models==null) {
					modelListCmb.addItem("Selectati Marca");
				}else {
					modelListCmb.addItem("Selectati Modelul");
					for(String name:models) {
						modelListCmb.addItem(name);
					}
				}
			}
		});
        
        searchPanel.add(makeLabel);
        searchPanel.add(makeListCmb);
        
        searchPanel.add(modelLabel);
        searchPanel.add(modelListCmb);
		
	}
	
	private void buildYear() {
		JLabel yearLabel = new JLabel("Year from:");
		yearLabel.setHorizontalAlignment(SwingConstants.CENTER);
		yearLabel.setBounds(122, 11, 58, 14);
		yearCmb = new JComboBox<String>();
		yearCmb.setBounds(178, 8, 48, 20);
		for(int i=1995 ; i<2021 ; i++) {
			yearCmb.addItem(Integer.toString(i));
		}
		searchPanel.add(yearLabel);
		searchPanel.add(yearCmb);
		
	}

	private void initTableComponents(JScrollPane scrollPane, JTable carsTable2) {
		
		 carsTable2.setModel(new javax.swing.table.DefaultTableModel(
		            new Object [][] {
		                {null, null, null, null},
		                {null, null, null, null},
		                {null, null, null, null},
		                {null, null, null, null}
		            },
		            new String [] {
		                "Title 1", "Title 2", "Title 3", "Title 4"
		            }
		        ));
		 
	        carsTable2.addMouseListener(new java.awt.event.MouseAdapter() {
	            public void mouseClicked(java.awt.event.MouseEvent evt) {
	            	
	                if(evt.getClickCount() == 2) {
	                	System.out.println("Car selected" + carsTable2.getSelectedRow());
	                	GetCarInfoQuery carInfo = new GetCarInfoQuery();
	                	Cars selectedCar = carInfo.getSelectedCar(carList.get(carsTable2.getSelectedRow()).getVIN_number());
	                	CarInfoUI obj = new CarInfoUI(selectedCar);
	                }
	            }
	        });
	        scrollPane.setViewportView(carsTable2);
	        
	        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
	        layout.setHorizontalGroup(
	        	layout.createParallelGroup(Alignment.TRAILING)
	        		.addGroup(Alignment.LEADING, layout.createSequentialGroup()
	        			.addContainerGap()
	        			.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 751, Short.MAX_VALUE)
	        			.addContainerGap())
	        );
	        layout.setVerticalGroup(
	        	layout.createParallelGroup(Alignment.LEADING)
	        		.addGroup(layout.createSequentialGroup()
	        			.addGap(197)
	        			.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
	        			.addContainerGap())
	        );
	        getContentPane().setLayout(layout);

	       // pack();
		
	}

	private void populateCarsTable() {
		GetSearchCarsQuery getCarsQuery = new GetSearchCarsQuery(make,model,year,minPrice,maxPrice,capacity,minKM,maxKM,fuel,color);
        carList = getCarsQuery.BindTable();
        String[] columnName = {"Make","Model","Year","Price","Image"};
        Object[][] rows = new Object[carList.size()][5];
        for(int i = 0; i < carList.size(); i++){
            rows[i][0] = carList.get(i).getMake();
            rows[i][1] = carList.get(i).getModel();
            rows[i][2] = carList.get(i).getYear();
            rows[i][3] = carList.get(i).getPrice();
            
            if(carList.get(i).getImage() != null){
                
             ImageIcon image = new ImageIcon(new ImageIcon(carList.get(i).getImage()).getImage()
             .getScaledInstance(150, 120, java.awt.Image.SCALE_SMOOTH) );   
                
            rows[i][4] = image;
            }
            else{
                rows[i][4] = null;
            }

        }
        
        TheModel model = new TheModel(rows, columnName);
        carsTable.setModel(model);
        carsTable.setRowHeight(120);
        carsTable.getColumnModel().getColumn(4).setPreferredWidth(150);	
	}
}
