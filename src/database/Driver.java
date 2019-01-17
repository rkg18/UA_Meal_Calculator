package database;

import java.sql.*;

public class Driver {
	private Connection conn;
	
	public Driver() 
	{
		conn = null;
	}
	
	// Instantiates a connection to the food database
	public void getConnection() throws SQLException
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String username = "root";
			String password = "4815f5b89597908ef76db8feca36416c";
			String url = "jdbc:mysql://127.0.0.1:3306/meal_calculator";
			
			conn = DriverManager.getConnection(url, username, password);
			
			System.out.println("You are connected...\n");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	// Returns a result set containing all the food options in the database
	public ResultSet viewMenu() throws SQLException
	{
		String sqlQuery = "Select * FROM food";
		Statement stm = conn.createStatement();
		ResultSet testSet = stm.executeQuery(sqlQuery);
		return testSet;
	}
	
	// Returns a count of the number of available food items inside the menu database
	public int countMenuItems() throws SQLException
	{
		String query = "SELECT Count(*) AS rowcount FROM food";
		Statement stm = conn.createStatement();
		ResultSet result = stm.executeQuery(query);
		
		result.next();
		int count = result.getInt("rowcount");
		result.close();
		
		return count;
	}
	
	// Returns the Row of an object dependent on the food item name passed
	public ResultSet getNutritionInfo(String foodItem) throws SQLException
	{
		String query = "SELECT calories, fat, protein, carbohydrates FROM food WHERE name = \"" + foodItem + "\"";
		Statement stm = conn.createStatement();
		ResultSet testSet = stm.executeQuery(query);
		return testSet;
	}

	public void closeConnection() throws SQLException {conn.close();}
	
}
