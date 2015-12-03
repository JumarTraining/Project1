package com.mississippi.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.mississippi.client.Client;

public class ViewClientGUI extends JFrame {
	
	private JLabel clientIDLabel = new JLabel("Client ID");
	private JLabel clientNameLabel = new JLabel("Client Name");	
	private JLabel clientAddressLabel = new JLabel("Client Address");
	private JLabel contactNumberLabel = new JLabel("Contact Number");

	private JTextField clientIDText = new JTextField();
	private JTextField clientNameText = new JTextField();
	private JTextField clientAddressText = new JTextField();
	private JTextField contactNumberText = new JTextField();
	
	public ViewClientGUI(int clientID)
	{
		
		Client viewClient = new Client(clientID);
		
		setLayout(null);
		
		clientIDLabel.setBounds(20,20,110,25);
		add(clientIDLabel);
		clientNameLabel.setBounds(20,60,110,25);
		add(clientNameLabel);
		clientAddressLabel.setBounds(20,100,110,25);
		add(clientAddressLabel);
		contactNumberLabel.setBounds(20,140,110,25);
		add(contactNumberLabel);
		
		clientIDText.setBounds(180,20,100,25);
		clientIDText.setEditable(false);
		clientIDText.setText(String.valueOf(viewClient.getClientID()));
		add(clientIDText);
		clientNameText.setBounds(180,60,200,25);
		clientNameText.setEditable(false);
		clientNameText.setText(viewClient.getClientName());
		add(clientNameText);
		clientAddressText.setBounds(180,100,200,25);
		clientAddressText.setEditable(false);
		clientAddressText.setText(viewClient.getClientAddress());
		add(clientAddressText);
		contactNumberText.setBounds(180,140,100,25);
		contactNumberText.setEditable(false);
		contactNumberText.setText(viewClient.getContactNumber());
		add(contactNumberText);

		setSize(400,220);
		setLocation(100,100);
		setResizable(false);
		setVisible(true);

	}

}
