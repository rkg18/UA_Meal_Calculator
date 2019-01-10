package database;

import java.sql.*;

public class Driver {
	private Connection conn;
	
	public Driver() 
	{
		conn = null;
	}
		
	public void getConnection() throws SQLException
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String username = "root";
			String password = "4815f5b89597908ef76db8feca36416c";
			String url = "jdbc:mysql://127.0.0.1:3306/meal_calculator";
			
			conn = DriverManager.getConnection(url, username, password);
			
			System.out.println("You are connected...");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void viewMenu() throws SQLException
	{
		String sqlQuery = "Select * FROM food";
		
		Statement stm = conn.createStatement();
		
		ResultSet testSet = stm.executeQuery(sqlQuery);
		
		while (testSet.next())
		{
			int id = testSet.getInt("idfood");
			String name = testSet.getString("name");
			int calories = testSet.getInt("calories");
			
			System.out.format("%s is the ID %s is the food %s is the calories\n", id, name, calories);
		}
	}
	
}
