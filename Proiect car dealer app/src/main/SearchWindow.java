package main;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.HeadlessException;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public class SearchWindow extends JFrame {

	SearchWindow searchWin;
	
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
	
    
	public SearchWindow()  {
		searchWin = this;
		
		setTitle("Cautare Autovehicul");
		setLocationRelativeTo(null);
		setSize(800,1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Se configureaza maparea marcilor cu modelele fiecareia
		buildDataModel();
		
		//Se genereaza panelul pentru afisare
		JPanel contentPanel = createAndAddPanel();
		getContentPane().add(contentPanel);
		
		//Se construiesc cmbboxurile pt marca si model
		buildMakeAndModel(contentPanel);
		
		//Se genereaza anul
		buildYear(contentPanel);
		
		//se genereaza preturile
        buildPrice(contentPanel);
        
        //se genereaza capacitatea motorului
        buildCapacity(contentPanel);
        
        //se genereaza rulajul
        buildKM(contentPanel);
        
        //se genereaza combustibilul
        buildFuel(contentPanel);
		
		setVisible(true);
	}

	private void buildFuel(JPanel contentPanel) {
		
		JLabel fuelLabel = new JLabel("Combustibil:");
		JComboBox<String> fuelCmb = new JComboBox<String>();
        fuelCmb.addItem("DIESEL");
        fuelCmb.addItem("BENZINA");
        fuelCmb.addItem("GAZ");
        
        fuelCmb.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                fuel = fuelCmb.getSelectedItem().toString();
            }
        });
		
		contentPanel.add(fuelLabel);
		contentPanel.add(fuelCmb);
		
	}

	private void buildKM(JPanel contentPanel) {
		
		JLabel minKMLabel = new JLabel("De la:");
		JTextField minKM = new JTextField(8);
		
		JLabel label = new JLabel("Rulaj ");
		contentPanel.add(label);
		contentPanel.add(minKMLabel);
		contentPanel.add(minKM);
		
		JLabel maxKMLabel = new JLabel("Pana la:");
		JTextField maxKM = new JTextField(8);
		contentPanel.add(maxKMLabel);
		contentPanel.add(maxKM);
		
		minKM.getDocument().addDocumentListener(new SimpleDocumentListener() {
			@Override
		    public void update(DocumentEvent e) {

		        if (Integer.parseInt(minKM.getText())<0){
		            JOptionPane.showMessageDialog(null,
		                    "Error: Please enter number bigger than 0", "Error Message",
		                    JOptionPane.ERROR_MESSAGE);
		        }       
		    }
		});
		
		maxKM.getDocument().addDocumentListener(new SimpleDocumentListener() {
			@Override
		    public void update(DocumentEvent e) {

		        if (Integer.parseInt(maxKM.getText())<0){
		            JOptionPane.showMessageDialog(null,
		                    "Error: Please enter number bigger than 0", "Error Message",
		                    JOptionPane.ERROR_MESSAGE);
		        }       
		    }
		});
		
	}

	private void buildCapacity(JPanel contentPanel) {
		JLabel capacityLabel = new JLabel("Capacitate motor:");
		JTextField capacity = new JTextField(8);
		
		contentPanel.add(capacityLabel);
		contentPanel.add(capacity);
		
		capacity.getDocument().addDocumentListener(new SimpleDocumentListener() {
			@Override
		    public void update(DocumentEvent e) {

		        if (Integer.parseInt(capacity.getText())<0){
		            JOptionPane.showMessageDialog(null,
		                    "Error: Please enter number bigger than 0", "Error Message",
		                    JOptionPane.ERROR_MESSAGE);
		        }       
		    }
		});
		
	}

	private void buildPrice(JPanel contentPanel) {
		
		JLabel minPriceLabel = new JLabel("De la:");
		JTextField minPrice = new JTextField(10);
		
		JLabel label = new JLabel("Pret ");
		contentPanel.add(label);
		contentPanel.add(minPriceLabel);
		contentPanel.add(minPrice);
		
		JLabel maxPriceLabel = new JLabel("Pana la:");
		JTextField maxPrice = new JTextField(8);
		contentPanel.add(maxPriceLabel);
		contentPanel.add(maxPrice);
		
		minPrice.getDocument().addDocumentListener(new SimpleDocumentListener() {
			@Override
		    public void update(DocumentEvent e) {

		        if (Integer.parseInt(minPrice.getText())<0){
		            JOptionPane.showMessageDialog(null,
		                    "Error: Please enter number bigger than 0", "Error Message",
		                    JOptionPane.ERROR_MESSAGE);
		        }       
		    }
		});
		
		maxPrice.getDocument().addDocumentListener(new SimpleDocumentListener() {
			@Override
		    public void update(DocumentEvent e) {

		        if (Integer.parseInt(maxPrice.getText())<0){
		            JOptionPane.showMessageDialog(null,
		                    "Error: Please enter number bigger than 0", "Error Message",
		                    JOptionPane.ERROR_MESSAGE);
		        }       
		    }
		});
	
	}

	private void buildYear(JPanel contentPanel) {
		JLabel yearLabel = new JLabel("Anul de la:");
		JComboBox<String> year = new JComboBox<String>();
		for(int i=1995 ; i<2021 ; i++) {
			year.addItem(Integer.toString(i));
		}
		contentPanel.add(yearLabel);
		contentPanel.add(year);
		
	}

	private void buildMakeAndModel(JPanel contentPanel) {
		JLabel makeLabel = new JLabel("Marca:");
		JLabel modelLabel = new JLabel("Modelul:");
		JComboBox<String> makeListCmb = new JComboBox<String>();
        makeListCmb.addItem("Selectati Marca");
        for(String value: makeAndModel.keySet()) {
        	makeListCmb.addItem(value);
        }
        
        
        JComboBox<String> modelListCmb = new JComboBox<String>();
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
        
        contentPanel.add(makeLabel);
        contentPanel.add(makeListCmb);
        
        contentPanel.add(modelLabel);
        contentPanel.add(modelListCmb);
		
	}

	private JPanel createAndAddPanel() {
		JPanel panel = new JPanel(true);
		//panel.setLayout(new GridLayout(1,2));
		add(panel);
		return panel;
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
