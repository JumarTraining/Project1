package com.mississippi.gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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

import com.charity.database.DBConnect;
import com.charity.database.DBFunction;

public class StaffGrades extends JDialog  
	{
		//boolean taskStatus;
		JPanel main_panel;
		//Appointment
  		JLabel lblGradeSalary, lblGradeNumber;
 		JTextField txtGradeSalary, txtGradeNumber;
		JScrollPane scroll;
		JButton btnGradeAdd, btnGradeExit, btnViewGrade, btnViewGradeExit;
		JTextArea txtaDescription, txtbDescription, txtaMessage, txtbMessage;
		JTabbedPane tabbedPane;
		String r;
		private String gradeSalary;
		private String gradeNumber;
		/*
//		PanelCalendar pnlAppointmentDate;
		// Task
 		JLabel lblTaskName, lblTaskDescription, lblTaskDeadLine, lblTaskStatus;
		JTextField txtTaskName, txtTaskDescription, txtTaskDeadLine, txtTaskStatus;
		JTextArea txtaTaskDescription, txtaTaskMessage;
		JComboBox cbxTaskStatus;
		JButton btnTaskAdd,   btnTaskExit;
		JScrollPane taskScroll;
	//	PanelCalendar pnlTaskDate;
		// Reminder 
		JLabel lblReminderName, lblReminderDate, lblReminderTime, lblReminderDescription;
 		JTextField txtReminderName, txtReminderTime;
		JTextArea txtaReminderDescription, txtaReminderMessage;
		JScrollPane reminderScroll;
		JButton btnReminderAdd,btnReminderExit;
//		PanelCalendar pnlReminderDate;
		JTabbedPane tabbedPane;
    	String r;
*/


public StaffGrades(JFrame parent, String title, boolean modal)
    	{    	        	
        	super(parent, title, modal);
        	setSize(700,360);
        	setLocation(150,200);
        	main_panel = new JPanel();
        	getContentPane().add(main_panel);
        	main_panel.setLayout(null);
			//Add Grade
			lblGradeSalary = new JLabel("Salary Amount:");
			lblGradeNumber = new JLabel("Grade Number:");
			txtGradeSalary = new JTextField();
			txtGradeNumber = new JTextField();
			txtaDescription = new JTextArea(3,10);
			txtaDescription.setLineWrap(true);
			txtbDescription = new JTextArea(3,10);
			txtbDescription.setLineWrap(true);
			int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
			int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
			scroll = new JScrollPane(txtaDescription,v,h);
			scroll = new JScrollPane(txtbDescription,v,h);
			txtaMessage = new JTextArea("This pane allows you to add new staff grade ",3,3);
			txtbMessage = new JTextArea("This pane allows you to view staff grades ",3,3);
	    	Dimension dimm = new Dimension(75,45);
			btnGradeAdd = new JButton("Add", new ImageIcon("images\\add.gif") );
			btnGradeAdd.setPreferredSize(dimm);
			btnGradeAdd.setVerticalTextPosition(AbstractButton.BOTTOM);
			btnGradeAdd.setHorizontalTextPosition(AbstractButton.CENTER);
			btnGradeAdd.setMnemonic('A');
			
			btnViewGrade = new JButton("View", new ImageIcon("images\\add.gif") );
			btnViewGrade.setPreferredSize(dimm);
			btnViewGrade.setVerticalTextPosition(AbstractButton.BOTTOM);
			btnViewGrade.setHorizontalTextPosition(AbstractButton.CENTER);
			btnViewGrade.setMnemonic('V');
			
    	    btnGradeExit = new JButton("Exit", new ImageIcon("images\\remove.gif") );
    	    btnGradeExit.setPreferredSize(dimm);
    	    btnGradeExit.setVerticalTextPosition(AbstractButton.BOTTOM);
    	    btnGradeExit.setHorizontalTextPosition(AbstractButton.CENTER);
    	    btnGradeExit.setMnemonic('E');
    		tabbedPane = new JTabbedPane();
       		tabbedPane.setBounds(1,1,500,350);
        	add(tabbedPane);
        	buildTabbedPane();
        	setFontProperties();
    	    
    	}

			//pnlViewGrade = new PanelCalendar();

    	    /*
    	    	    		pnlAppointmentDate = new PanelCalendar();
    		// Task
			lblTaskName = new JLabel("Task Name:", SwingConstants.RIGHT);				
			lblTaskDescription = new JLabel("Description:", SwingConstants.RIGHT);
			lblTaskDeadLine = new JLabel("Deadline (Date):", SwingConstants.RIGHT);
			lblTaskStatus = new JLabel("Status:", SwingConstants.RIGHT);		
			txtTaskName = new JTextField();
			txtTaskDescription = new JTextField();
			txtTaskDeadLine = new JTextField();
			txtTaskStatus = new JTextField();;
			txtaTaskDescription = new JTextArea(3,10);
			txtaTaskDescription.setLineWrap(true);
			int hr = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
			int vr = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
			taskScroll = new JScrollPane(txtaTaskDescription,vr,hr);
			String status[] = {"Completed","Not Completed"};		
			cbxTaskStatus = new JComboBox(status);	
			txtaTaskMessage = new JTextArea("This tabbed pane allows you to add the tasks "+
	    										"for your upcoming activities.",3,3);			
			btnTaskAdd = new JButton("Add", new ImageIcon("images\\add.gif") );
			btnTaskAdd.setPreferredSize(dimm);
			btnTaskAdd.setVerticalTextPosition(AbstractButton.BOTTOM);
    		btnTaskAdd.setHorizontalTextPosition(AbstractButton.CENTER);
    		btnTaskAdd.setMnemonic('A');
    		btnTaskExit = new JButton("Exit", new ImageIcon("images\\remove.gif") );
			btnTaskExit.setPreferredSize(dimm);
			btnTaskExit.setVerticalTextPosition(AbstractButton.BOTTOM);
    		btnTaskExit.setHorizontalTextPosition(AbstractButton.CENTER);
    		btnTaskExit.setMnemonic('E');   	
//    		pnlTaskDate = new PanelCalendar();
    		// Reminder Events
			lblReminderName = new JLabel("Event Name:", SwingConstants.RIGHT);
			lblReminderDate = new JLabel("Event Date:", SwingConstants.RIGHT);
			lblReminderTime = new JLabel("Time to Occur:", SwingConstants.RIGHT);				
			lblReminderDescription = new JLabel("Description:", SwingConstants.RIGHT);
			txtReminderName = new JTextField();
			txtReminderTime = new JTextField();			
			txtaReminderDescription = new JTextArea(3,10);
			txtaReminderDescription.setLineWrap(true);
			int hor = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
			int vor = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
			reminderScroll = new JScrollPane(txtaReminderDescription,vor,hor);
			txtaReminderMessage = new JTextArea("This tabbed pane allows you to add a reminder event "+
	    										"for your upcoming activities.",3,3);
			btnReminderAdd = new JButton("Add", new ImageIcon("images\\add.gif") );
			btnReminderAdd.setPreferredSize(dimm);
			btnReminderAdd.setVerticalTextPosition(AbstractButton.BOTTOM);
    		btnReminderAdd.setHorizontalTextPosition(AbstractButton.CENTER);
    		btnReminderAdd.setMnemonic('A');
    	    btnReminderExit = new JButton("Exit", new ImageIcon("images\\remove.gif") );
			btnReminderExit.setPreferredSize(dimm);
			btnReminderExit.setVerticalTextPosition(AbstractButton.BOTTOM);
    		btnReminderExit.setHorizontalTextPosition(AbstractButton.CENTER);
    		btnReminderExit.setMnemonic('E');
//    		pnlReminderDate = new PanelCalendar();
    		tabbedPane = new JTabbedPane();
       		tabbedPane.setBounds(1,1,500,350);
        	add(tabbedPane);
        	buildTabbedPane();
        	setFontProperties();
		}
		/**
		 * Method that builds the GUI tabbed pane for appointment, task and reminder event.
		 */
    	private void buildTabbedPane() 
    	{
        	tabbedPane.addTab("Add Grade",null, new AddGradePanel(), "Add Grade");
        	//tabbedPane.addTab("View Grade",null, new ViewGradePanel(), "View Grade");
        	
		}
		/**
		 * Method that sets the propeties of the fonts
		 */
    	private void setFontProperties()
    	{
    		Font fonts = new Font("Tahoma", Font.BOLD, 16);
    		Font font = new Font("Tahoma", Font.PLAIN, 15);
    		Font font_buttons = new Font("Tahoma", Font.BOLD, 12);
    		lblGradeSalary.setFont(font);
    		lblGradeNumber.setFont(font);
    		btnViewGrade.setFont(font_buttons);
			btnGradeAdd.setFont(font_buttons);
			btnGradeExit.setFont(font_buttons);
			txtaMessage.setFont(font_buttons);
			txtbMessage.setFont(font_buttons);
		}
    	
    	class AddGradePanel extends JPanel
    	{
    		/**
    		 * Constructs the appointment tabbed panel
    		 * @return 
    		 */
    		public AddGradePanel()
    		{
	    		setLayout(null);
	    		setBorder(BorderFactory.createEtchedBorder() );
	    		// Appointment Name
	    		lblGradeSalary.setBounds(30,10,150,20);
	    		add(lblGradeSalary);
	    		txtGradeSalary.setBounds(200,10,200,25);
	    		add(txtGradeSalary);
	    		
	    		// Buttons
	    		btnGradeAdd.setBounds(245,240,80,38);
	    		add(btnGradeAdd);
	    		btnGradeAdd.addActionListener(new ActionListener()
				{
	    			public void actionPerformed(ActionEvent ae)
	    			{
	    				r = JOptionPane.showInputDialog(null, "Are you sure you want to add this grade? y/n ");
						if(r != null)
						{
							if(r.equals("y"))
							{
								try{
									addNewGrade("y");
	    						
	    						}catch(Exception e){}
	    					}
	    					else if(r.equals("n"))
	    					{
								JOptionPane.showMessageDialog(null,"You don't want to set reminder for your appointment:");
								addNewGrade("n");
							}
							else
								JOptionPane.showMessageDialog(null,"Choose a valid option:");
	    				
						}
						else
							JOptionPane.showMessageDialog(null,"Choose a valid option:");	
					}
	    		});
	 			btnGradeExit.setBounds(330,240,80,38);
	    		add(btnGradeExit);
	    		btnGradeExit.addActionListener(new ActionListener()
	    		{
	    			public void actionPerformed(ActionEvent ae)
	    			{
	    				setVisible(false);
	    				dispose();
	    			}
	    		});	
	    		txtaMessage.setEditable(false);
	    		txtaMessage.setLineWrap(true);
	    		txtaMessage.setBackground(getBackground());
	    		txtaMessage.setWrapStyleWord(true);
	    		JScrollPane appoint_inside = new JScrollPane(txtaMessage);
	    		appoint_inside.setBounds(480,20,150,80);
	    		add(appoint_inside);    
			}
		}
    	private  void addNewGrade(String reminderOption) 
    	{
    		gradeSalary = txtGradeSalary.getText();
    		String[] item = {gradeSalary};
//			String appDateStr = pnlAppointmentDate.input.getText();
//			year = Integer.parseInt(appDateStr.substring(7,11));
	//		String month = appDateStr.substring(3,6);
			//String [] monthCon = {"Jan", "Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
			//for(int i = 0;i<monthCon.length;i++)
			//{
/*				if(month.equals(monthCon[i]))
				{
					mon = i+1;
					break;
				}*/
			//}
//			day = Integer.parseInt(appDateStr.substring(0,2));
//			String appStartTime = txtStartTime.getText();
//			hour = Integer.parseInt(appStartTime.substring(0,2));
//			minute = Integer.parseInt(appStartTime.substring(3,5));
//			String appointmentDurationStr = txtDuration.getText();
//			int appDuration = Integer.parseInt(appointmentDurationStr);
//			String appointmentLocationStr = txtLocation.getText();
//			String appointmentDescriptionStr = txtaDescription.getText();
if(reminderOption.equals("y"))
			{
			Connection con = null;
//	try {
	//	con = DBConnect.DBConnect();
//	} catch (SQLException e1) {
	//	e1.printStackTrace();
	//}
	
	try {
		DBFunction.Record(item, "employeegrades");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	//iStatement stmt = (Statement) con.createStatement();
	//ResultSet rs = stmt.executeUpdate("INSERT INTO StaffGrades" + "VALUES (null,"+"\""+staffGrades+"\""+","+"\""+gender+"\""+","+"\""+address+"\""+","+"\""+postcode+"\""+","+phone+""+",\""+email+"\")");

		
	//String results = "";
	//try {
		//while (rs.next()){
			//results = results + "Employee ID: " + rs.getString("employeeid")+"\n"
				//	+ "Name: "+ rs.getString("employeename")+"\n"
				//	+ "Address: "+ rs.getString("address")+"\n"
				//	+ "Phone Number: "+ rs.getString("tel")+"\n"
				//	+ "Pay Grade: "+ rs.getString("Employeegrades_gradeid")+"\n\n";
		//}
	//} catch (SQLException e1) {
	//	e1.printStackTrace();
	//}
	
	//JOptionPane.showMessageDialog(null, results, "Employee information", JOptionPane.PLAIN_MESSAGE);
	//setReminder("Appointment",year, mon, day, hour, minute, appointmentName);
			}
////      		Appointment app = new Appointment(1,appointmentName, 
//      		new Date(year-1900, mon-1, day), appStartTime, appDuration,
 //     			appointmentLocationStr,appointmentDescriptionStr);
//        	ce.addCalendarEntryComponent(app);
        	String s = "Grade "+gradeSalary+" is created. "+"\nDo you want create another Grade?";
        	int i = JOptionPane.showConfirmDialog(null, s, "Staff Grade",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
			if(i == JOptionPane.YES_OPTION)
			{
				txtGradeSalary.setText("");
				txtaDescription.setText("");
			}
		}
	//}
    	class ViewGradePanel extends JPanel
    	{
    		/**
    		 * Constructs the appointment tabbed panel
    		 * @return 
    		 */
    		public ViewGradePanel()
    		{
	    		setLayout(null);
	    		setBorder(BorderFactory.createEtchedBorder() );
	    		// Appointment Name
	    		lblGradeNumber.setBounds(30,10,150,20);
	    		add(lblGradeNumber);
	    		txtGradeNumber.setBounds(200,10,200,25);
	    		add(txtGradeNumber);
	    		
	    		// Buttons
	    		btnViewGrade.setBounds(245,240,80,38);
	    		add(btnViewGrade);
	    		btnViewGrade.addActionListener(new ActionListener()
				{
	    			public void actionPerformed(ActionEvent ae)
	    			{
	    				r = JOptionPane.showInputDialog(null, "Are you sure you want to view this grade? y/n ");
						if(r != null)
						{
							if(r.equals("y"))
							{
								try{
									viewGrade("y");
	    						
	    						}catch(Exception e){}
	    					}
	    					else if(r.equals("n"))
	    					{
								JOptionPane.showMessageDialog(null,"You don't want to set reminder for your appointment:");
								addNewGrade("n");
							}
							else
								JOptionPane.showMessageDialog(null,"Choose a valid option:");
	    				
						}
						else
							JOptionPane.showMessageDialog(null,"Choose a valid option:");	
					}
	    		});
	 			btnViewGradeExit.setBounds(330,240,80,38);
	    		add(btnViewGradeExit);
	    		btnViewGradeExit.addActionListener(new ActionListener()
	    		{
	    			public void actionPerformed(ActionEvent ae)
	    			{
	    				setVisible(false);
	    				dispose();
	    			}
	    		});	
	    		txtbMessage.setEditable(false);
	    		txtbMessage.setLineWrap(true);
	    		txtbMessage.setBackground(getBackground());
	    		txtbMessage.setWrapStyleWord(true);
	    		JScrollPane appoint_inside = new JScrollPane(txtbMessage);
	    		appoint_inside.setBounds(480,20,150,80);
	    		add(appoint_inside);    
			}
		}
    	private  void viewGrade(String reminderOption) 
    	{
    		gradeNumber = txtGradeNumber.getText();
    		//String search = gradeNumber;
    		String table = "employeegrades";
    		String field = "GradeId";
    		
    		//String[] item = {gradeNumber, "GradeId", "employeegrades"};
//			String appDateStr = pnlAppointmentDate.input.getText();
//			year = Integer.parseInt(appDateStr.substring(7,11));
	//		String month = appDateStr.substring(3,6);
			//String [] monthCon = {"Jan", "Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
			//for(int i = 0;i<monthCon.length;i++)
			//{
/*				if(month.equals(monthCon[i]))
				{
					mon = i+1;
					break;
				}*/
			//}
//			day = Integer.parseInt(appDateStr.substring(0,2));
//			String appStartTime = txtStartTime.getText();
//			hour = Integer.parseInt(appStartTime.substring(0,2));
//			minute = Integer.parseInt(appStartTime.substring(3,5));
//			String appointmentDurationStr = txtDuration.getText();
//			int appDuration = Integer.parseInt(appointmentDurationStr);
//			String appointmentLocationStr = txtLocation.getText();
//			String appointmentDescriptionStr = txtaDescription.getText();
if(reminderOption.equals("y"))
			{
			Connection con = null;
//	try {
	//	con = DBConnect.DBConnect();
//	} catch (SQLException e1) {
	//	e1.printStackTrace();
	//}
	
	try {
		DBFunction.Search(gradeNumber, field, table);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	//iStatement stmt = (Statement) con.createStatement();
	//ResultSet rs = stmt.executeUpdate("INSERT INTO StaffGrades" + "VALUES (null,"+"\""+staffGrades+"\""+","+"\""+gender+"\""+","+"\""+address+"\""+","+"\""+postcode+"\""+","+phone+""+",\""+email+"\")");

		
	//String results = "";
	//try {
		//while (rs.next()){
			//results = results + "Employee ID: " + rs.getString("employeeid")+"\n"
				//	+ "Name: "+ rs.getString("employeename")+"\n"
				//	+ "Address: "+ rs.getString("address")+"\n"
				//	+ "Phone Number: "+ rs.getString("tel")+"\n"
				//	+ "Pay Grade: "+ rs.getString("Employeegrades_gradeid")+"\n\n";
		//}
	//} catch (SQLException e1) {
	//	e1.printStackTrace();
	//}
	
	//JOptionPane.showMessageDialog(null, results, "Employee information", JOptionPane.PLAIN_MESSAGE);
	//setReminder("Appointment",year, mon, day, hour, minute, appointmentName);
			}
////      		Appointment app = new Appointment(1,appointmentName, 
//      		new Date(year-1900, mon-1, day), appStartTime, appDuration,
 //     			appointmentLocationStr,appointmentDescriptionStr);
//        	ce.addCalendarEntryComponent(app);
        	//String s = "Grade "+gradeSalary+" is created. "+"\nDo you want create another Grade?";
        	//int i = JOptionPane.showConfirmDialog(null, s, "Staff Grade",
				//JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
			//if(i == JOptionPane.YES_OPTION)
			//{
			//	txtGradeNumber.setText("");
			//	txtbDescription.setText("");
			//}
		}
	}