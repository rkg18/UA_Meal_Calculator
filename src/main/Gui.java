package main;

import java.awt.*;

public class Gui extends Frame {
	Gui()
	{
		Button testButton = new Button("Click me!");
		
		testButton.setBounds(50,50,50,50);
		
		add(testButton);
		
		setSize(500,300);
		
		setTitle("University of Akron Meal Calculator");
		
		setLayout(new FlowLayout());
		
		setVisible(true);
	}
}
