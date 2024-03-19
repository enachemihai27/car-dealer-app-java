package main;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MenuWindow extends JFrame{
	
	public MenuWindow() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		JPanel contentPane = new JPanel();
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
		
		btnSearchCars.addActionListener(new ActionListener() {
			
            @Override
            public void actionPerformed(ActionEvent e) {
            	//SearchWindow search = new SearchWindow();
            	SearchCarsUI test = new SearchCarsUI();
            }
        });
		
		btnNewCar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                AddCarsUI test1 = new AddCarsUI();
            }
        });
		
		setVisible(true);
	}

}
