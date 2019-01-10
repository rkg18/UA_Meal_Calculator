/*
 * Raymond Gines
 * github.com/rkg18/meal_calculator.git
 * Simple Meal Builder GUI Application for University of Akron Restaurants
 */

package main;

import java.sql.SQLException;
import database.Driver;
import java.util.Scanner;

public class Interface {

	public static void main(String[] args) throws SQLException
	{
		Driver myDriver = new Driver();
		
		myDriver.getConnection();
		
		System.out.println("1. View Menu\n"
				+ "2. Add New Menu Item\n"
				+ "3. Edit Menu Item\n"
				+ "4. Delete Menu Item\n"
				+ "\nEnter Choice: ");
		
		Scanner reader = new Scanner(System.in);
		
		int choice = reader.nextInt(); //Gets user input choice
		
		// Performs corresponding menu option
		switch(choice)
		{
			case 1: 
				myDriver.viewMenu();
				break;
			case 2: 
				System.out.println("other...");
				break;
		}
		
		myDriver.closeConnection();
	}
	
}
