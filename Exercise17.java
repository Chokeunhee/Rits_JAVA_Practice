/*
 * Exercise 17 2 button and a textfield which reacts(show different text) with when clicked
 * by Cho keun hee
 * On November 19th 2019
 */

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Exercise17 extends JFrame {
	JButton button1, button2;
	JTextField text1;
	
	public Exercise17() {
		setSize(400,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		button1 = new JButton("red");
		button1.addMouseListener(new Button_red());
		button2 = new JButton("green");
		button2.addMouseListener(new Button_green());
		text1 = new JTextField("This is a textfield",20);
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		panel1.add(button1);
		panel1.add(button2);
		panel2.add(text1);
		getContentPane().add(panel1,BorderLayout.NORTH);
		getContentPane().add(panel2,BorderLayout.CENTER);
		
		
		setVisible(true);
	}

	public static void main(String[] args) {
		new Exercise17();
	}
	
	class Button_red extends MouseAdapter{
		public void mousePressed(MouseEvent e) {
			text1.setText("Red button was pressed.");
			}
	}
	class Button_green extends MouseAdapter{
		public void mousePressed(MouseEvent e) {
			text1.setText("green button was pressed.");
		}
	}	
}
