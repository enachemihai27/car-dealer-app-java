package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class MenuTest extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuTest frame = new MenuTest();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MenuTest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewCar = new JButton("New Car");
		btnNewCar.setBounds(10, 228, 89, 23);
		contentPane.add(btnNewCar);
		
		JButton btnSearchCars = new JButton("Search Cars");
		btnSearchCars.setBounds(335, 228, 89, 23);
		contentPane.add(btnSearchCars);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(10, 11, 414, 206);
		contentPane.add(lblNewLabel);
		ImageIcon img = new ImageIcon("images/auto.jpg");
		Image image = img.getImage(); // transform it 
		Image newimg = image.getScaledInstance(lblNewLabel.getWidth(), lblNewLabel.getHeight(),  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		img = new ImageIcon(newimg);
		lblNewLabel.setIcon(img);
	}
}
