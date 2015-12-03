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

public class AddCampaignGUI extends JFrame {
	
	private JLabel campaignNameLabel = new JLabel("Campaign Name");
	private JLabel plannedStartDateLabel = new JLabel("Planned Start Date");
	private JLabel plannedEndDateLabel = new JLabel("Planned End Date");
	private JLabel budgetLabel = new JLabel("Budget");
	private JLabel clientIDLabel = new JLabel("Client ID");

	private JTextField campaignNameText = new JTextField();
	private JTextField plannedStartDateText = new JTextField();
	private JTextField plannedEndDateText = new JTextField();
	private JTextField budgetText = new JTextField();
	private JTextField clientIDText = new JTextField();
	
	private JButton addCampaignBtn = new JButton("Add");
	
	AddCampaignGUI currentGUI = this;
	
	public AddCampaignGUI()
	{
		setLayout(null);
	
		campaignNameLabel.setBounds(20,20,150,25);
		add(campaignNameLabel);
		plannedStartDateLabel.setBounds(20,60,150,25);
		add(plannedStartDateLabel);
		plannedEndDateLabel.setBounds(20,100,150,25);
		add(plannedEndDateLabel);
		budgetLabel.setBounds(20,140,150,25);
		add(budgetLabel);
		clientIDLabel.setBounds(20,180,150,25);
		add(clientIDLabel);

		campaignNameText.setBounds(180,20,150,25);
		add(campaignNameText);
		plannedStartDateText.setBounds(180,60,100,25);
		add(plannedStartDateText);
		plannedEndDateText.setBounds(180,100,100,25);
		add(plannedEndDateText);
		budgetText.setBounds(180,140,100,25);
		add(budgetText);
		clientIDText.setBounds(180,180,100,25);
		add(clientIDText);
	
		addCampaignBtn.setBounds(100,220,90,25);
		add(addCampaignBtn);
		addCampaignBtn.addActionListener
		(new ActionListener()	
			{
				public void actionPerformed(ActionEvent e)
				{
					String campaignNameString = campaignNameText.getText();
					String plannedStartDateString = plannedStartDateText.getText();
					String plannedEndDateString = plannedEndDateText.getText();				
					String budgetString = budgetText.getText();					
					String clientIDString = clientIDText.getText();
					if (campaignNameString.trim().equals(""))
					{
						JOptionPane.showMessageDialog(currentGUI,"Please enter Campaign Name","Campaign not entered",0);
										}
					else if (plannedStartDateString.trim().equals(""))
					{
						JOptionPane.showMessageDialog(currentGUI,"Please enter Start Date","Start Date not entered",0);						
					}
					else if (Pattern.compile("\d\d\d\d-\d\d").matcher(plannedStartDateString).find())
					{
						
					}
					else if(clientIDString.trim().equals(""))
					{
						JOptionPane.showMessageDialog(currentGUI,"Please enter Client ID","Client not entered",0);
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
//						new ViewClientGUI(Integer.parseInt(clientIDString));
					}
				
				}
			
			}
		);
		setSize(300,260);
		setLocation(100,100);
		setResizable(false);
		setVisible(true);
	}	
	
}
