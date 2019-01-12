package main;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.Driver;

public class Gui extends Frame implements ActionListener {
	Label label = new Label();
	Button b = new Button("Click");
	MenuBar mb = new MenuBar();
	List myList;
	Button addItem = new Button("Add Item");
	
	// Database Class
	Driver driver = new Driver();
	
	Gui() throws SQLException
	{
		label.setAlignment(Label.CENTER);
		label.setSize(250,100);
		
		b.setBounds(175,75,60,20);
		b.addActionListener(this);
		
		addItem.setBounds(200,100,60,20);
		addItem.addActionListener(this);
		
		// Connects to MySQL 'Meal_Calculator' Database
		driver.getConnection();
		
		createFileMenu();
		createList(driver.countMenuItems());
		
		add(myList);
		add(b);
		add(label);
		add(addItem);
		
		setSize(300,250);
		setLayout(null);
		setVisible(true);
		setMenuBar(mb);
		
		// Corner 'X' Button
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
				
				label.setText(calories + " " + fat + " " + carbohydrates + " " + protein);
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}	
		}
		else if(e.getSource() == addItem)
		{
			label.setText("hello world!");
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