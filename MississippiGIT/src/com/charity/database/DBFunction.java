package com.charity.database;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;



import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class DBFunction{
	

	public static void Record(String[] item, String table) throws SQLException {
	  
		Connection con = null;
		try {
			con = DBConnect.DBConnect();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		Statement stmt = (Statement) con.createStatement();
		String input = null;
		
		switch(table){
			case "client":  input = "VALUES (null, '" + item[0] + "', '" + item[1] + "', '" + item[2] + "')";
				break;
			case "employees": input = "VALUES (null, '" + item[0] + "', '" + item[1] + "', '" + item[2] + "', " + item[3] + ")";
				break;
		}
		
		stmt.executeUpdate("INSERT INTO " + table + " " + input);
		
		
		
	}

	public static ResultSet Search(String search, String field, String table) throws SQLException {
		
		Connection con = null;
		try {
			con = DBConnect.DBConnect();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		
		Statement stmt = (Statement) con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * from " + table + " WHERE " + field + "='" + search + "'");
			
		
		
		return rs;
		
	}

	public static void Update(String[] item, String search, String field, String table) throws SQLException {
		Connection con = null;
		try {
			con = DBConnect.DBConnect();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		Statement stmt = (Statement) con.createStatement();
		
		switch(table){
			case "client":  stmt.executeUpdate("UPDATE " + table + 
					" SET name = '" + item [1] +"', "+
					"address = '" + item[2] + "', " +
					"ContactNumber = '"+ item[3] +
					"' WHERE idcustomer = " + item[0]);
				break;
			case "employee":  stmt.executeUpdate("UPDATE " + table + 
					" SET employeename = '" + item [1] +"', "+
					"address = '" + item[2] + "', " +
					"tel = '"+ item[3] + "', " +
					"EmployeeGrades = '" + item[4]+ 
					"' WHERE idcustomer = " + item[0]);
				break;
		}
		
		
		
	}

	public static void Delete(String search, String field, Connection con, String table) throws SQLException {
		Statement stmt = (Statement) con.createStatement();
		stmt.executeUpdate("DELETE FROM " + table + " WHERE " + field + " = '" + search + "'");
	}
	
}