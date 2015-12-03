package com.mississippi.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.mississippi.client.Client;
import com.mississippi.gui.ViewClientGUI;

public class SelectClientGUI extends JFrame {

	private JLabel clientIDLabel = new JLabel("Please enter Client ID");

	private JTextField clientIDText = new JTextField();
	
	private JButton viewBtn = new JButton("View");
	
	SelectClientGUI currentGUI = this;
	
	public SelectClientGUI()
	{
		
		setLayout(null);
		
		clientIDLabel.setBounds(20,20,150,25);
		add(clientIDLabel);

		clientIDText.setBounds(180,20,100,25);
		add(clientIDText);
		
		viewBtn.setBounds(100,60,90,25);
		add(viewBtn);
		viewBtn.addActionListener
		(new ActionListener()	
			{
				public void actionPerformed(ActionEvent e)
				{
					String clientIDString = clientIDText.getText();
					if(clientIDString.trim().equals(""))
					{
						JOptionPane.showMessageDialog(currentGUI,"Please enter ID","ID not entered",0);
						
					}
					else if (Pattern.compile("[^0-9]").matcher(clientIDString).find())
					{
						JOptionPane.showMessageDialog(currentGUI,"Client ID must be entirely numeric","ID invalid",0);					
					}
					else if (!(Client.idExists(Integer.parseInt(clientIDString))))
					{
						JOptionPane.showMessageDialog(currentGUI,"Client ID not found","ID not found",0);	
					}
					else
					{
						new ViewClientGUI(Integer.parseInt(clientIDString));
					}
					
				}
				
			}
		);
		setSize(300,120);
		setLocation(100,100);
		setResizable(false);
		setVisible(true);
	}
}
