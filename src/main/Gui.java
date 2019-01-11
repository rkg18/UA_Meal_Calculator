package main;

import java.awt.*;
import java.awt.event.*; 

public class Gui extends Frame implements ActionListener {
	TextField tf;
	
	Gui()
	{
		
		
		//create components  
		tf=new TextField();  
		tf.setBounds(60,50,170,20);  
		Button b=new Button("click me");  
		b.setBounds(100,120,80,30);  
		  
		// Register Listener Objects
		b.addActionListener(this); //passing current instance  
		addWindowListener(new WindowAdapter(){public void windowClosing(WindowEvent e){dispose();}});  
		
		//add components and set size, layout and visibility  
		add(b);add(tf);  
		setSize(300,300);  
		setLayout(null);  
		setVisible(true);  
	}
	
	public void actionPerformed(ActionEvent e) {
		tf.setText("Welcome!");
	}
		
}