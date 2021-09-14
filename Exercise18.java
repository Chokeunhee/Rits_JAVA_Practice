/*
 * Exercise18 two button if pressed show at different place therefore setting section for clear
 * By Cho keun hee
 * on November 19th 2019
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Exercise18 extends JFrame {
	JButton button1, button2;
	JTextField text1;
	
	public Exercise18() {
		setSize(400,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		button1 = new JButton("red");
		button1.addMouseListener(new Button_red());
		button2 = new JButton("green");
		button2.addMouseListener(new Button_green());
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
		new Exercise18();
	}
	
	class Button_red extends MouseAdapter{
		String str;
		
		public void mousePressed(MouseEvent e) {
			str = text1.getText();
			Graphics g = getGraphics();
			g.clearRect(0, 120, 400, 31);
			Font font1 = new Font("Rockwell", Font.PLAIN, 30);
			g.setFont(font1);
			g.setColor(Color.red);
			g.drawString(str, 50, 150);
		}
	}
	
	class Button_green extends MouseAdapter{
		String str;
		
		public void mousePressed(MouseEvent e) {
			str = text1.getText();
			Graphics g = getGraphics();
			g.clearRect(0,170,400,31);
			Font font2 = new Font("Georgia", Font.PLAIN, 30);
			g.setFont(font2);
			g.setColor(Color.green);
			g.drawString(str, 50, 200);
			}
	}
}
		
