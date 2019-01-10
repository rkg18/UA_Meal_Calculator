/*
 * Raymond Gines
 * github.com/rkg18/meal_calculator.git
 * Simple Meal Builder GUI Application for University of Akron Restaurants
 */

package main;

import java.sql.SQLException;

import database.Driver;

public class Interface {

	public static void main(String[] args) throws SQLException
	{
		Driver myDriver = new Driver();
		
		myDriver.getConnection();
		
		myDriver.viewMenu();
	}
	
}
