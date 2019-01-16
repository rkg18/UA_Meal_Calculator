package main;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.Driver;

public class Gui extends Frame implements ActionListener {
	Label lblCalories = new Label();
	Label lblFat = new Label();
	Label lblProtein = new Label();
	Label lblCarbohydrates = new Label();
	
	Button b = new Button("See Nutrition Info");
	Button addItem = new Button("Add");
	Button removeItem = new Button("Remove");
	MenuBar mb = new MenuBar();
	List myList;
	List myMenu;
	
	ArrayList<String> itemNames = new ArrayList<String>(0);
	ArrayList<Integer> itemCalories= new ArrayList<Integer>(0);
	ArrayList<Integer> itemProtein = new ArrayList<Integer>(0);
	ArrayList<Integer> itemCarbohydrates = new ArrayList<Integer>(0);
	ArrayList<Integer> itemFat = new ArrayList<Integer>(0);
	
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
		
		removeItem.setBounds(350,175,100,20);
		removeItem.addActionListener(this);
		
		// Connects to MySQL 'Meal_Calculator' Database
		driver.getConnection();
		
		// File and Menu Creation
		createFileMenu();
		createList(driver.countMenuItems());
		createMyMenu();
		
		// Adds Objects to Frame
		add(myList); add(myMenu); // Lists
		add(b); add(removeItem); add(addItem); // Buttons
		add(lblCalories); add(lblFat); add(lblProtein); add(lblCarbohydrates); // Labels
		
		// Sets the Size and Items on the Frame
		setSize(500,300);
		setLayout(null);
		setVisible(true);
		setMenuBar(mb);
		
		// Implements Corner 'X' Button
		addWindowListener(new WindowAdapter(){public void windowClosing(WindowEvent e){dispose();}}); 
	}
	
	public void actionPerformed(ActionEvent e){
		String name = myList.getItem(myList.getSelectedIndex());
		ResultSet info;
		
		int calories=0;
		int fat=0;
		int carbohydrates=0;
		int protein=0;
		
		try {
			info = driver.getNutritionInfo(name);
			info.next();
			
			calories = info.getInt("calories");
			fat = info.getInt("fat");
			carbohydrates= info.getInt("carbohydrates");
			protein = info.getInt("protein");
		} catch (SQLException e1) {e1.printStackTrace();}
		
		if(e.getSource() == b)
		{
			
			lblCalories.setText("Calories: " + calories);
			lblFat.setText("Fat: " + fat + "g");
			lblProtein.setText("Protein: " + protein + "g");
			lblCarbohydrates.setText("Carbohydrates: " + carbohydrates + "g");
		}
		else if(e.getSource() == addItem)
		{	
			myMenu.add(name);
			itemNames.add(name);
			itemCalories.add(calories);
			itemProtein.add(protein);
			itemFat.add(fat);
			itemCarbohydrates.add(carbohydrates);
		}
		else if(e.getSource() == removeItem)
		{
			String oldItem = myMenu.getItem(myMenu.getSelectedIndex());
			myMenu.remove(oldItem);
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
	
	public void createMyMenu()
	{
		myMenu = new List();
		myMenu.setBounds(350,75,100,70);
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