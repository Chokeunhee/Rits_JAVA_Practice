/*
 * Exercise 16 two button and a textfield without any function
 * By Cho keun hee
 * on November 19th 2019
 */

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Exercise16 extends JFrame {
	JButton button1, button2;
	JTextField text1;
	int count = 0;
	
	public Exercise16() {
		setSize(400,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		button1 = new JButton("red");
		button2 = new JButton("green");
		text1 = new JTextField("This is a textfield",20);
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		p1.add(button1);
		p1.add(button2);
		p2.add(text1);
		getContentPane().add(p1,BorderLayout.NORTH);
		getContentPane().add(p2,BorderLayout.CENTER);
		
		setVisible(true);
	}

	public static void main(String[] args) {
		new Exercise16();
	}

}