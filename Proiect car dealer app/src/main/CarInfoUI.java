package main;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import tables.Cars;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;

public class CarInfoUI extends JFrame {

	private JPanel contentPane;
	private JTextField makeTxt;
	private JTextField modelTxt;
	private JTextField yearTxt;
	private JLabel label_3;
	private JTextField vinText;
	private JTextField priceTxt;
	private JLabel label_4;
	private JLabel label_5;
	private JTextField capacityTxt;
	private JLabel label_6;
	private JTextField colorTxt;
	private JLabel label_7;
	private JLabel label_8;
	private JTextField kmTxt;
	private JTextField fuelTxt;
	
	Cars selectedCar;
	private JLabel imgLabel;

	public CarInfoUI(Cars car) {
		
		this.selectedCar = car;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 682);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		imgLabel = new JLabel();
		imgLabel.setBounds(10, 11, 514, 299);
		imgLabel.setBorder(BorderFactory.createLineBorder(Color.black));
		imgLabel.setIcon(ResizeImage(car.getImage()));
		contentPane.add(imgLabel);
		
		JLabel label = new JLabel("Make");
		label.setBounds(10, 324, 46, 14);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("Model");
		label_1.setBounds(10, 366, 46, 14);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("Year");
		label_2.setBounds(10, 403, 46, 14);
		contentPane.add(label_2);
		
		makeTxt = new JTextField();
		makeTxt.setBounds(51, 321, 86, 20);
		makeTxt.setText(car.getMake());
		contentPane.add(makeTxt);
		makeTxt.setColumns(10);
		
		modelTxt = new JTextField();
		modelTxt.setColumns(10);
		modelTxt.setBounds(51, 363, 86, 20);
		modelTxt.setText(car.getModel());
		contentPane.add(modelTxt);
		
		yearTxt = new JTextField();
		yearTxt.setColumns(10);
		yearTxt.setBounds(51, 400, 86, 20);
		yearTxt.setText(Integer.toString(car.getYear()));
		contentPane.add(yearTxt);
		
		label_3 = new JLabel("VIN Number");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setBounds(180, 324, 70, 14);
		contentPane.add(label_3);
		
		vinText = new JTextField();
		vinText.setColumns(10);
		vinText.setBounds(258, 321, 115, 20);
		vinText.setText(car.getVIN_number());
		contentPane.add(vinText);
		
		priceTxt = new JTextField();
		priceTxt.setColumns(10);
		priceTxt.setBounds(258, 361, 86, 20);
		priceTxt.setText(Integer.toString(car.getPrice()));
		contentPane.add(priceTxt);
		
		label_4 = new JLabel("Price");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setBounds(180, 364, 46, 14);
		contentPane.add(label_4);
		
		label_5 = new JLabel("Capacity");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setBounds(180, 405, 46, 14);
		contentPane.add(label_5);
		
		capacityTxt = new JTextField();
		capacityTxt.setColumns(10);
		capacityTxt.setBounds(258, 402, 86, 20);
		capacityTxt.setText(Integer.toString(car.getCapacity()));
		contentPane.add(capacityTxt);
		
		label_6 = new JLabel("Color");
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setBounds(383, 410, 46, 14);
		contentPane.add(label_6);
		
		colorTxt = new JTextField();
		colorTxt.setColumns(10);
		colorTxt.setBounds(438, 407, 86, 20);
		colorTxt.setText(car.getColor());
		contentPane.add(colorTxt);
		
		label_7 = new JLabel("Fuel");
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setBounds(383, 364, 46, 14);
		contentPane.add(label_7);
		
		label_8 = new JLabel("KM");
		label_8.setHorizontalAlignment(SwingConstants.CENTER);
		label_8.setBounds(383, 324, 46, 14);
		contentPane.add(label_8);
		
		kmTxt = new JTextField();
		kmTxt.setColumns(10);
		kmTxt.setBounds(438, 321, 86, 20);
		kmTxt.setText(Integer.toString(car.getKm()));
		contentPane.add(kmTxt);
		
		fuelTxt = new JTextField();
		fuelTxt.setColumns(10);
		fuelTxt.setBounds(438, 361, 86, 20);
		fuelTxt.setText(car.getFuel());
		contentPane.add(fuelTxt);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(10, 610, 89, 23);
		btnCancel.addActionListener(new ActionListener(){
	        @Override
	        public void actionPerformed(ActionEvent e){
	        	closeWindow();
	        }
	       });
		contentPane.add(btnCancel);
		
		JButton btnCall = new JButton("Call");
		btnCall.setBounds(409, 610, 115, 23);
		btnCall.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnCall.setText(car.getNr_tel());
			}
		});
		contentPane.add(btnCall);
		
		JTextPane txtpnAskAQuestion = new JTextPane();
		txtpnAskAQuestion.setText("Ask a question...");
		txtpnAskAQuestion.setBounds(27, 466, 477, 130);
		contentPane.add(txtpnAskAQuestion);
		
		setVisible(true);
	}
	
	public Icon ResizeImage(byte[] bs){
		ImageIcon icon = new ImageIcon(bs);
		Image newImage = icon.getImage(); 
		newImage = newImage.getScaledInstance(imgLabel.getWidth(), imgLabel.getHeight(),Image.SCALE_SMOOTH);
		icon = new ImageIcon(newImage);
		return icon;
    }
	
	private void closeWindow() {
		if (JOptionPane.showConfirmDialog(this, 
                "Are you sure you want to close this window?", "Close Window?", 
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
                dispose();
            }
    	}
}
