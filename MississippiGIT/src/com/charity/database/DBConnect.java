package com.charity.database;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.jdbc.Connection;


public class DBConnect {
	public static Connection DBConnect() throws SQLException {// 

		String url = "jdbc:mysql://localhost:3306/mississippi";
		
		Properties prop = new Properties();
		
		prop.setProperty("user", "root");
		
		prop.setProperty("password", "password");

		Driver d = new com.mysql.jdbc.Driver();
		
		Connection con = (Connection) d.connect(url,prop);
		
		
		if(con == null){
			System.out.println("Connection failed.");
			
			return null;
		}
		
		return con;
	}
}
