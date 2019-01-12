package main;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.Driver;

public class Gui extends Frame implements ActionListener {
	Label lblCalories = new Label();
	Label lblFat = new Label();
	Label lblProtein = new Label();
	Label lblCarbohydrates = new Label();
	
	Button b = new Button("See Nutrition Info");
	MenuBar mb = new MenuBar();
	List myList;
	Button addItem = new Button("Add Item");
	
	// Database Class
	Driver driver = new Driver();
	
	Gui() throws SQLException
	{
		// Set Size and Position of the Nutrition Labels
		lblCalories.setBounds(200, 75, 150, 25);
		lblFat.setBounds(200, 100, 150, 25);
		lblProtein.setBounds(200, 125, 150, 25);
		lblCarbohydrates.setBounds(200, 150, 150, 25);
		
		// Button Objects
		b.setBounds(50,150,100,20);
		b.addActionListener(this);
		
		addItem.setBounds(50,175,100,20);
		addItem.addActionListener(this);
		
		// Connects to MySQL 'Meal_Calculator' Database
		driver.getConnection();
		
		// File and Menu Creation
		createFileMenu();
		createList(driver.countMenuItems());
		
		// Adds Objects to Frame
		add(myList);
		add(b);
		add(lblCalories); add(lblFat); add(lblProtein); add(lblCarbohydrates);
		add(addItem);
		
		// Sets the Size and Items on the Frame
		setSize(500,300);
		setLayout(null);
		setVisible(true);
		setMenuBar(mb);
		
		// Implements Corner 'X' Button
		addWindowListener(new WindowAdapter(){public void windowClosing(WindowEvent e){dispose();}}); 
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == b)
		{
			String name = myList.getItem(myList.getSelectedIndex());
			
			ResultSet info;
			
			try {
				info = driver.getNutritionInfo(name);
			
				info.next();
				
				int calories = info.getInt("calories");
				int fat = info.getInt("fat");
				int carbohydrates= info.getInt("carbohydrates");
				int protein = info.getInt("protein");
				
				lblCalories.setText("Calories: " + calories);
				lblFat.setText("Fat: " + fat + "g");
				lblProtein.setText("Protein: " + protein + "g");
				lblCarbohydrates.setText("Carbohydrates: " + carbohydrates + "g");
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}	
		}
		else if(e.getSource() == addItem)
		{
			lblCalories.setText("hello world!");
		}
		
	}
	
	public void createList(int numberOfItems) throws SQLException
	{	
		myList = new List(numberOfItems);
		myList.setBounds(50,75,100,70);
		ResultSet menuItems = driver.viewMenu();
		
		// Adds Menu Items to List Box
		while(menuItems.next())
		{
			myList.add(menuItems.getString("name"));
		}
	}
	
	public void createFileMenu()
	{	
		Menu menu = new Menu("Directory");
		MenuItem item1 = new MenuItem("Menu");
		MenuItem item2 = new MenuItem("Calculate Calories");
		menu.add(item1); menu.add(item2);
		mb.add(menu);
	}
		
}