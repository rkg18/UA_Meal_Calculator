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
			
			System.out.println("You are connected...\n");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public ResultSet viewMenu() throws SQLException
	{
		String sqlQuery = "Select * FROM food";
		Statement stm = conn.createStatement();
		ResultSet testSet = stm.executeQuery(sqlQuery);
		return testSet;
	}
	
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
	
	public ResultSet getNutritionInfo(String foodItem) throws SQLException
	{
		String query = "SELECT calories, fat, protein, carbohydrates FROM food WHERE name = \"" + foodItem + "\"";
		Statement stm = conn.createStatement();
		ResultSet testSet = stm.executeQuery(query);
		return testSet;
		
	}

	public void closeConnection() throws SQLException {conn.close();}
	
}
