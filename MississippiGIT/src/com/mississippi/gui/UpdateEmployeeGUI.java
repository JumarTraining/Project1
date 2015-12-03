package com.mississippi.gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import com.charity.database.DBFunction;

//import com.charity.gui.MainWindowCharity.CalendarEntries.AppointmentTabPanel;

	
	public class UpdateEmployeeGUI extends JDialog  
	{
		
		String[] payGrades = new String[]{"1","2","3","4"};
		boolean taskStatus;
		JPanel main_panel;
		//Appointment
  		JLabel lblEmployeeName, lblEmployeeAddress, lblTelephoneNumber, lblStaffGrade, lblEmployeeId;
 		JTextField txtEmployeeName, txtEmployeeAddress, txtTelephoneNumber, txtEmployeeId;
 		JComboBox cbxStaffGrade;
		JScrollPane scroll;
		JButton btnEmployeeUpdate, btnEmployeeExit;
//		PanelCalendar pnlAppointmentDate;
		JTabbedPane tabbedPane;
    	String r;
    	/**
     	 * Constructs the CalendarEntries main panel
     	 * @param parent the parent JFrame 
     	 * @param title the title of the calendar entries frame
     	 * @param modal the modal
     	 */
    	public UpdateEmployeeGUI()
    	{    	        	
        	
        	setSize(700,360);
        	setLocation(150,200);
        	main_panel = new JPanel();
        	getContentPane().add(main_panel);
        	main_panel.setLayout(null);
			//Employee
        	lblEmployeeId = new JLabel("Employee ID:");
			lblEmployeeName = new JLabel("Employee Name:");
			lblEmployeeAddress = new JLabel("Employee Address:");
			lblTelephoneNumber = new JLabel("Telephone Number:");
			lblStaffGrade = new JLabel("Staff Grade:");
			
			txtEmployeeId = new JTextField();
			txtEmployeeName = new JTextField();
			txtEmployeeAddress= new JTextField();
			txtTelephoneNumber = new JTextField();
			cbxStaffGrade = new JComboBox(payGrades);

	    	Dimension dimm = new Dimension(75,45);
			btnEmployeeUpdate = new JButton("Update", new ImageIcon("images\\add.gif") );
			btnEmployeeUpdate.setPreferredSize(dimm);
			btnEmployeeUpdate.setVerticalTextPosition(AbstractButton.BOTTOM);
			btnEmployeeUpdate.setHorizontalTextPosition(AbstractButton.CENTER);
			btnEmployeeUpdate.setMnemonic('U');
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
    		lblEmployeeId.setFont(font);
    		lblEmployeeName.setFont(font);    	
			lblEmployeeAddress.setFont(font);
			lblTelephoneNumber.setFont(font);
			lblStaffGrade.setFont(font);
			cbxStaffGrade.setFont(font);
			btnEmployeeUpdate.setFont(font_buttons);
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
	    		// Employee ID
	    		lblEmployeeId.setBounds(30,10,150,20);
	    		add(lblEmployeeId);
	    		txtEmployeeId.setBounds(200,10,200,25);
	    		add(txtEmployeeId);
	    		// Employee Name
	    		lblEmployeeName.setBounds(30,40,150,20);
	    		add(lblEmployeeName);
	    		txtEmployeeName.setBounds(200,40,200,25);
	    		add(txtEmployeeName);
	    		txtEmployeeName.setEditable(false);
	    		// Employee Address
	    		lblEmployeeAddress.setBounds(30,70,150,20);
	    		add(lblEmployeeAddress);
	    		txtEmployeeAddress.setBounds(200,70,200,25);
	    		add(txtEmployeeAddress);
	    		txtEmployeeAddress.setEditable(false);
//	    		pnlAppointmentDate.setBounds(195,38,150,25);
//	    		add(pnlAppointmentDate);
	    		// Telephone Number
	    		lblTelephoneNumber.setBounds(30,100,150,20);
	    		add(lblTelephoneNumber);
	    		txtTelephoneNumber.setBounds(200,100,200,25);
	    		add(txtTelephoneNumber);
	    		txtTelephoneNumber.setEditable(false);
	    		// Pay Grade
	    		lblStaffGrade.setBounds(30, 130, 150, 20);
	    		add(lblStaffGrade);
	    		cbxStaffGrade.setBounds(200, 130, 200, 25);
	    		add(cbxStaffGrade);
	    		cbxStaffGrade.setEditable(false);
	    		
	    		// Buttons
	    		btnEmployeeUpdate.setBounds(245,240,80,38);
	    		add(btnEmployeeUpdate);
				btnEmployeeUpdate.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e) {
						
						
						String employeeId = null;
						String employeeName;
						String employeeAdd;
						String employeeTel;
						String employeeGrade;
						
						
						
						employeeName = txtEmployeeName.getText();
						employeeAdd = txtEmployeeAddress.getText();
						employeeTel = txtTelephoneNumber.getText();
						employeeGrade = (String) cbxStaffGrade.getSelectedItem();
						
						if(employeeName.equals("")){
							employeeId = txtEmployeeId.getText();
							
							Connection con = null;
							try {
								con = DBConnect.DBConnect();
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
							ResultSet results = null;
							try {
								results = DBFunction.Search(employeeId, "EmployeeId", "employees");
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
							try {
								while (results.next()) {
									employeeName = results
											.getString("employeeName");
									employeeAdd = results.getString("Address");
									employeeTel = results.getString("tel");
									employeeGrade = results
											.getString("EmployeeGrades_GradeId");
								}
							} catch (SQLException e2) {
								e2.printStackTrace();
							}
							
							int n = JOptionPane.showConfirmDialog(null, "Employee ID: "+ employeeId +
									"\n Employee Name: " + employeeName +
									"\n\nDo you wish to update this customer?",
									"Update Customer", JOptionPane.YES_NO_OPTION);
							if(n == 0){
								txtEmployeeId.setEditable(false);
								txtEmployeeName.setEditable(true);
								txtEmployeeAddress.setEditable(true);
								txtTelephoneNumber.setEditable(true);
								cbxStaffGrade.setEditable(true);
								
								txtEmployeeName.setText(employeeName);
								txtEmployeeAddress.setText(employeeAdd);
								txtTelephoneNumber.setText(employeeTel);
								cbxStaffGrade.setSelectedItem(employeeGrade);
							}
						}else{
							employeeId = txtEmployeeId.getText();
							String[] item = {employeeId, employeeName, employeeAdd, employeeTel, employeeGrade};
							
							Connection con = null;
							try {
								con = DBConnect.DBConnect();
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
							try {
								DBFunction.Update(item, employeeId, "EmployeeId", "employees");
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
							JOptionPane.showMessageDialog(null, "Employee information updated", "Employee Updated", JOptionPane.PLAIN_MESSAGE);
							dispose();
						}
						

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

