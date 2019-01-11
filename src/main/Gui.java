package main;

import java.awt.*;
import java.awt.event.*;  

public class Gui extends Frame implements ActionListener {
	Label label = new Label();
	Button b = new Button("Click");
	Choice c = new Choice();
	
	Gui()
	{
		label.setAlignment(Label.CENTER);
		label.setSize(400,100);
		
		b.setBounds(200,100,50,20);
		b.addActionListener(this);
		
		c.setBounds(100,100,75,75);
		c.add("Pizza");
		c.add("Burger");
		c.add("Steak");
		c.add("Chicken");
		
		add(c);
		add(b);
		add(label);
		
		setSize(400,400);
		setLayout(null);
		setVisible(true);
		
		addWindowListener(new WindowAdapter(){public void windowClosing(WindowEvent e){dispose();}}); 
	}
	
	public void actionPerformed(ActionEvent e) {
		String data = c.getItem(c.getSelectedIndex());
		label.setText(data);
	}
		
}