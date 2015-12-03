package com.mississippi.client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.charity.database.DBConnect;


public class Client {

	private int clientID = 0;
	private String clientName;
	private String clientAddress;
	private String contactNumber;
	
	public Client (int clientID)
	{
		

		try
		{
			Connection dbCon = DBConnect.DBConnect();
			if(dbCon==null)
			{
				System.out.println("Connection failed");
				return;
			}
			PreparedStatement readClient = dbCon.prepareStatement("Select ClientId, Name, Address, ContactNumber from Client where ClientId = ?");
			
			readClient.setInt(1, clientID);
			ResultSet rs = readClient.executeQuery();
			
			if(rs.next())
			{
				this.clientID = clientID;
				clientName = rs.getString("Name");
				clientAddress = rs.getString("Address");
				contactNumber = rs.getString("ContactNumber");
			}

		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}
	
	public int getClientID ()
	{
		return clientID;
	}
	
	public String getClientName ()
	{
		return clientName;
	}
	
	public String getClientAddress ()
	{
		return clientAddress;
	}
	
	public String getContactNumber ()
	{
		return contactNumber;
	}
	
	static public boolean idExists (int clientID)
	{
		try
		{
			Connection dbCon = DBConnect.DBConnect();
			if(dbCon==null)
			{
				System.out.println("Connection failed");
				return false;
			}
			PreparedStatement readClient = dbCon.prepareStatement("Select ClientId from Client where ClientId = ?");
			
			readClient.setInt(1, clientID);
			ResultSet rs = readClient.executeQuery();
			
			return rs.next();
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
}
