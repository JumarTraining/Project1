package com.mississippi.gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import com.charity.database.DBConnect;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;


	
	public class addClientGui extends JDialog  
	{
		boolean taskStatus;
		JPanel main_panel;
		JLabel lblCustomerName, lblCustomerAddress, lblCustomerContactNo;
		JTextField txtCustomerName, txtCustomerAddress, txtCustomerContactNo;
		JButton btnCustomerAdd, btnCustomerExit;
		JTabbedPane tabbedPane;
    	String r;
    
    	public addClientGui()
    	{    	        	
        	
        	setSize(700,360);
        	setLocation(150,200);
        	main_panel = new JPanel();
        	getContentPane().add(main_panel);
        	main_panel.setLayout(null);

      
        	lblCustomerName = new JLabel("Customer Name:");
        	lblCustomerAddress = new JLabel("Customer Address:");
        	lblCustomerContactNo = new JLabel("Customer Contact Number:");
        	txtCustomerName = new JTextField();
        	txtCustomerAddress = new JTextField();
        	txtCustomerContactNo = new JTextField();
        	Dimension dimm = new Dimension(75,45);
			btnCustomerAdd = new JButton("Add", new ImageIcon("images\\add.gif") );
			btnCustomerAdd.setPreferredSize(dimm);
			btnCustomerAdd.setVerticalTextPosition(AbstractButton.BOTTOM);
			btnCustomerAdd.setHorizontalTextPosition(AbstractButton.CENTER);
			btnCustomerAdd.setMnemonic('A');
        	Dimension size = new Dimension(75,45);
        	btnCustomerExit = new JButton("Exit", new ImageIcon("images\\remove.gif") );
        	btnCustomerExit.setPreferredSize(size);
        	btnCustomerExit.setVerticalTextPosition(AbstractButton.BOTTOM);
        	btnCustomerExit.setHorizontalTextPosition(AbstractButton.CENTER);
        	btnCustomerExit.setMnemonic('E');
    		tabbedPane = new JTabbedPane();
       		tabbedPane.setBounds(1,1,500,350);
        	add(tabbedPane);
        	buildTabbedPane();
        	setFontProperties();
		}
	
	private void buildTabbedPane() 
	{
  
		tabbedPane.addTab("Customer", null, new CustomerTabPanel(), "Customer");

	}
	
	private void setFontProperties()
	{
		Font fonts = new Font("Tahoma", Font.BOLD, 16);
		Font font = new Font("Tahoma", Font.PLAIN, 15);
		Font font_buttons = new Font("Tahoma", Font.BOLD, 12);
		lblCustomerName.setFont(font);
		lblCustomerAddress.setFont(font);
		lblCustomerContactNo.setFont(font);
		btnCustomerExit.setFont(font);
		btnCustomerAdd.setFont(font);
	}
	class CustomerTabPanel extends JPanel{
		public CustomerTabPanel()
		{
    		setLayout(null);
    		setBorder(BorderFactory.createEtchedBorder() );
    		lblCustomerName.setBounds(30,10,150,20);
    		add(lblCustomerName);
    		txtCustomerName.setBounds(260,10,200,25);
    		add(txtCustomerName);
    		lblCustomerAddress.setBounds(30,40,200,20);
    		add(lblCustomerAddress);
    		txtCustomerAddress.setBounds(260,40,200,25);
    		add(txtCustomerAddress);
    		lblCustomerContactNo.setBounds(30,70,200,20);
    		add(lblCustomerContactNo);
    		txtCustomerContactNo.setBounds(260,70,200,25);
    		add(txtCustomerContactNo);

    		btnCustomerAdd.setBounds(245,240,80,38);
    		add(btnCustomerAdd);
    		btnCustomerAdd.addActionListener(new ActionListener()
    		{
    			public void actionPerformed(ActionEvent ae)
    			{
    				r = JOptionPane.showInputDialog(null, "Do you want to Add Customer? y/n ");
					if(r != null)
					{
						if(r.equals("y"))
						{
							try{
								addNewCustomer("y");
    						}catch(Exception e){}
    					}
    					else if(r.equals("n"))
    					{
							JOptionPane.showMessageDialog(null,"You don't want to add Customer:");
							addNewCustomer("n");
						}
						else
							JOptionPane.showMessageDialog(null,"Choose a valid option:");
    				}
					else
						JOptionPane.showMessageDialog(null,"Choose a valid option:");
				}
    		});
    		
    		
    		//Exit Customer
    		btnCustomerExit.setBounds(330,240,80,38);
    		add(btnCustomerExit);
    		btnCustomerExit.addActionListener(new ActionListener()
    		{
    			public void actionPerformed(ActionEvent ae)
    			{
    				setVisible(false);
    				dispose();
    			}
    		});
}
	}
	private void addNewCustomer(String cust){
		String customerName = txtCustomerName.getText();
		String customerAddress = txtCustomerAddress.getText();
		String customerContactNumber = txtCustomerContactNo.getText();
		System.out.println(customerName);
		
		String[] item = {customerName, customerAddress, customerContactNumber };
		addmethod("client", item);
		
	}
	private void addmethod(String table, String[] item) {
	
	try{	String input = null;
	switch(table){
	case "client":
		input = " INSERT INTO " + table + " VALUES(null, " +item[0]+", " +item[1]+", " +item[3] +" )";
		
	}
	Connection con = null;
	con = DBConnect.DBConnect();
	Statement st = (Statement) con.createStatement();
	st.executeUpdate(input);}
	catch(SQLException e) {
		e.printStackTrace();
	}
	}
}

