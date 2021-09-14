/*
 * Exercise 20 changing color of circle using ActionListener
 * by Cho keun hee
 * on Dec 3rd 2019
 * 
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Exercise20 extends JFrame implements ActionListener, MouseListener {
	JButton button1;
	
	Color[] colorSet = {Color.red, Color.yellow, Color.green, Color.blue};
	String[] colorLabel = {"Red", "Yellow", "Green", "Blue"};
	
	int label = 0;
	
	boolean flag = false;
	boolean flag2 = false;
	
	Color color;
	
	int xCenter, yCenter;
	int size = 100;
 
 public Exercise20() {
	 setSize(500,500);
	 setDefaultCloseOperation(EXIT_ON_CLOSE);
	 addMouseListener(this);
  
	 button1 = new JButton(colorLabel[0]);
	 button1.addActionListener(this);
	 button1.setActionCommand("btn1");
	 
	 JPanel panel1 = new JPanel();
	 panel1.add(button1);
	 getContentPane().add(panel1, BorderLayout.NORTH);
  
	 setVisible(true);
 }
 
 public void paint(Graphics g) {
	 super.paint(g);
	 g.clearRect(0, 60, 500, 500);
	 if(flag) {
		 Font font1 = new Font("Dialoge", Font.PLAIN, 20);
		 g.setFont(font1);
		 g.setColor(Color.red);
		 g.drawString("Enter", 440, 50);
	 }
	 if(flag2) {
		 color = colorSet[label];
		 g.setColor(color);
		 g.fillOval(xCenter - size/2, yCenter - size/2, size, size);
	 }
 }

 public void mouseClicked(MouseEvent e) {
	 flag2 = true;
	 color = colorSet[label];
	 repaint();
 }
 public void mouseEntered(MouseEvent e) {
	 flag = true;
	 repaint();
 }
 public void mouseExited(MouseEvent e) {
	 flag = false;
	 repaint();
 }
 public void mousePressed(MouseEvent e) {
	 flag2 = true;
  	xCenter = e.getX();
  	yCenter = e.getY();
  	repaint();
 }
 public void mouseReleased(MouseEvent e) {
	 flag2 = true;
	 repaint();
 }
 
 public void actionPerformed(ActionEvent e) {
	 String cmd = e.getActionCommand();
	 if(cmd.contentEquals("btn1")){
		 if(label++ < 3) {
			 button1.setText(colorLabel[label]);
			 repaint();
		 }else {
			 button1.setText(colorLabel[0]);
			 label = 0;
			 repaint();
		 }
	 }
 }
public static void main(String[] args) {
	new Exercise20();
  }
}