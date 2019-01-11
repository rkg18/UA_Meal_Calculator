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
	
	// Database Class
	Driver driver = new Driver();
	
	Gui() throws SQLException
	{
		label.setAlignment(Label.CENTER);
		label.setSize(400,100);
		
		b.setBounds(200,100,50,20);
		b.addActionListener(this);
		
		// Connects to MySQL 'Meal_Calculator' Database
		driver.getConnection();
		
		createMenu();
		createList(driver.countMenuItems());
		
		add(myList);
		add(b);
		add(label);
		
		setSize(400,400);
		setLayout(null);
		setVisible(true);
		setMenuBar(mb);
		
		// Corner 'X' Button
		addWindowListener(new WindowAdapter(){public void windowClosing(WindowEvent e){dispose();}}); 
	}
	
	public void actionPerformed(ActionEvent e) {
		// TODO: Add Performance
	}
	
	public void createList(int numberOfItems) throws SQLException
	{	
		myList = new List(numberOfItems);
		myList.setBounds(100,100,100,100);
		ResultSet menu = driver.viewMenu();
		
		// Adds Menu Items to List Box
		while(menu.next())
		{
			myList.add(menu.getString("name"));
		}
	}
	
	public void createMenu()
	{	
		Menu menu = new Menu("Directory");
		
		MenuItem item1 = new MenuItem("Menu");
		MenuItem item2 = new MenuItem("Calculate Calories");
		
		menu.add(item1); menu.add(item2);
		mb.add(menu);
	}
		
}