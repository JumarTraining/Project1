package com.mississippi.gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import com.charity.database.DBConnect;

//import com.charity.gui.MainWindowCharity.CalendarEntries.AppointmentTabPanel;

	
	public class AddEmployeeGUI extends JDialog  
	{
		
		String[] payGrades = new String[]{"1", "2","3","4"};
		boolean taskStatus;
		JPanel main_panel;
		//Appointment
  		JLabel lblEmployeeName, lblEmployeeAddress, lblTelephoneNumber, lblStaffGrade;
 		JTextField txtEmployeeName, txtEmployeeAddress, txtTelephoneNumber;
 		JComboBox cbxStaffGrade;
		JScrollPane scroll;
		JButton btnEmployeeAdd, btnEmployeeExit;
//		PanelCalendar pnlAppointmentDate;
		JTabbedPane tabbedPane;
    	String r;
    	/**
     	 * Constructs the CalendarEntries main panel
     	 * @param parent the parent JFrame 
     	 * @param title the title of the calendar entries frame
     	 * @param modal the modal
     	 */
    	public AddEmployeeGUI()
    	{    	        	
        	
        	setSize(700,360);
        	setLocation(150,200);
        	main_panel = new JPanel();
        	getContentPane().add(main_panel);
        	main_panel.setLayout(null);
			//Employee
			lblEmployeeName = new JLabel("Employee Name:");
			lblEmployeeAddress = new JLabel("Employee Address:");
			lblTelephoneNumber = new JLabel("Telephone Number:");
			lblStaffGrade = new JLabel("Staff Grade:");
			
			txtEmployeeName = new JTextField();
			txtEmployeeAddress= new JTextField();
			txtTelephoneNumber = new JTextField();
			cbxStaffGrade = new JComboBox(payGrades);

	    	Dimension dimm = new Dimension(75,45);
			btnEmployeeAdd = new JButton("Add", new ImageIcon("images\\add.gif") );
			btnEmployeeAdd.setPreferredSize(dimm);
			btnEmployeeAdd.setVerticalTextPosition(AbstractButton.BOTTOM);
			btnEmployeeAdd.setHorizontalTextPosition(AbstractButton.CENTER);
			btnEmployeeAdd.setMnemonic('A');
    	    btnEmployeeExit = new JButton("Exit", new ImageIcon("images\\remove.gif") );
    	    btnEmployeeExit.setPreferredSize(dimm);
    	    btnEmployeeExit.setVerticalTextPosition(AbstractButton.BOTTOM);
    	    btnEmployeeExit.setHorizontalTextPosition(AbstractButton.CENTER);
    	    btnEmployeeExit.setMnemonic('E');
//    		
    	    tabbedPane = new JTabbedPane();
       		tabbedPane.setBounds(1,1,500,350);
        	add(tabbedPane);
        	buildTabbedPane();
    	    setFontProperties();

}
    	private void buildTabbedPane() 
    	{
        	tabbedPane.addTab("Employee",null, new AppointmentTabPanel(), "Appointments");
    	}
    	
    	private void setFontProperties()
    	{
    		Font fonts = new Font("Tahoma", Font.BOLD, 16);
    		Font font = new Font("Tahoma", Font.PLAIN, 15);
    		Font font_buttons = new Font("Tahoma", Font.BOLD, 12);
    		lblEmployeeName.setFont(font);    	
			lblEmployeeAddress.setFont(font);
			lblTelephoneNumber.setFont(font);
			lblStaffGrade.setFont(font);
			cbxStaffGrade.setFont(font);
			btnEmployeeAdd.setFont(font_buttons);
			btnEmployeeExit.setFont(font_buttons);
			
		}
    	
    	class AppointmentTabPanel extends JPanel
    	{
    		/**
    		 * Constructs the appointment tabbed panel
    		 */
    		public AppointmentTabPanel()
    		{
	    		setLayout(null);
	    		setBorder(BorderFactory.createEtchedBorder() );
	    		// Employee Name
	    		lblEmployeeName.setBounds(30,10,150,20);
	    		add(lblEmployeeName);
	    		txtEmployeeName.setBounds(200,10,200,25);
	    		add(txtEmployeeName);
	    		// Employee Address
	    		lblEmployeeAddress.setBounds(30,40,150,20);
	    		add(lblEmployeeAddress);
	    		txtEmployeeAddress.setBounds(200,40,200,25);
	    		add(txtEmployeeAddress);
//	    		pnlAppointmentDate.setBounds(195,38,150,25);
//	    		add(pnlAppointmentDate);
	    		// Telephone Number
	    		lblTelephoneNumber.setBounds(30,70,150,20);
	    		add(lblTelephoneNumber);
	    		txtTelephoneNumber.setBounds(200,70,200,25);
	    		add(txtTelephoneNumber);
	    		// Pay Grade
	    		lblStaffGrade.setBounds(30, 100, 150, 20);
	    		add(lblStaffGrade);
	    		cbxStaffGrade.setBounds(200, 100, 200, 25);
	    		add(cbxStaffGrade);
	    		
	    		// Buttons
	    		btnEmployeeAdd.setBounds(245,240,80,38);
	    		add(btnEmployeeAdd);
				btnEmployeeAdd.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e) {
						
						
	    				
						
						String employeeName = txtEmployeeName.getText();
						
						String address = txtEmployeeAddress.getText();
						
						String pn = txtTelephoneNumber.getText();
						int phoneNo = Integer.parseInt(pn);
						
						String sg = (String)cbxStaffGrade.getSelectedItem();
						int staffGrade = Integer.parseInt(sg);
						
						Connection conn = null;
						try {
							conn = DBConnect.DBConnect();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						
						try{
							String insertItemSQL = ("insert into employees (EmployeeId, EmployeeName, Address, Tel, EmployeeGrades_GradeId) values(?,?,?,?,?)");
						
						
						PreparedStatement preparedStmt = (PreparedStatement) conn.prepareStatement(insertItemSQL);
						
						preparedStmt.setInt(1, 0);
						preparedStmt.setString(2, employeeName);
						preparedStmt.setString(3, address);
						preparedStmt.setInt(4, phoneNo);
						preparedStmt.setInt(5, staffGrade);
						
						preparedStmt.execute();
						
						conn.close();
						} catch(SQLException ex) {
							ex.printStackTrace();
						}
						dispose();

					}
					
				});
	    		
	    		
	    		
	 			btnEmployeeExit.setBounds(330,240,80,38);
	    		add(btnEmployeeExit);
	    		btnEmployeeExit.addActionListener(new ActionListener()
	    		{
	    			public void actionPerformed(ActionEvent ae)
	    			{
	    				
	    				dispose();
	    			}
	    		});	
	    		
			}
		}
	}

