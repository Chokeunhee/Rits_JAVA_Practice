/*
 * contents of the program 
 * (1) making window size 400*300, setting the background as dark_gray
 * (2) putting info on name card ; name studentID school, email, telephone, nationality
 * (3) - 1. made a symbol of E-mail using drawRoundRect() and drawPolyline()
 * 	   - 2. made a symbol of flag of Korea using fillOval() and fillArc()
 *     - 3. made lines using drawLine() to divide section
 * By CHO KEUN HEE / 26001904131
 * November 4th 2019
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import javax.swing.JFrame;

public class NameCard extends JFrame {
		
	public NameCard() {
		setSize(400,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setBackground( Color.DARK_GRAY );
	}
	
	public void paint(Graphics g) {
		
		Font font1 = new Font("Arial", Font.PLAIN, 12);
		Font font2 = new Font("Arial", Font.PLAIN, 100);
		Font font3 = new Font("Arial", Font.PLAIN, 10);
		FontMetrics metrics = g.getFontMetrics(font1);
		FontMetrics metrics2 = g.getFontMetrics(font2);
		
		g.setFont(font2);
		g.setColor(Color.red);
		g.drawString("R",(400 - metrics2.stringWidth("R")) / 2 , 150);
		
		g.setFont(font1);
		g.setColor(Color.white);
		g.drawString("Cho Keun Hee", (400 - metrics.stringWidth("Cho Keun Hee")) / 2, 170);		
		g.drawString("26001904131", (400 - metrics.stringWidth("26001904131")) / 2, 184);				
		g.drawString("College of Information Science and Engineering", (400 - metrics.stringWidth("College of Information Science and Engineering")) / 2, 198);				
		g.drawString("Ritsumeikan University",(400 - metrics.stringWidth("Ritsumeikan University")) / 2 , 212);
		
		g.setFont(font3);
		g.drawString("tonycho@gmail.com ", 40, 280);
		g.drawString("080-8097-4599 ", 175, 280);
		g.drawString("Republic of Korea ", 310, 280);
		
		int xArea = 50, yArea = 50;
		int xLine = xArea/2, yLine = yArea/2;
		int xPoint = 280, yPoint = 265;
		g.setColor(Color.blue);
		g.fillOval(xPoint, yPoint, xLine, yLine);
		g.setColor(Color.red);
		g.fillArc(xPoint, yPoint, xLine, yLine, 148, -180);
		g.setColor(Color.red);
		g.fillOval(xPoint+(xLine/24), yPoint+(xLine/8), xLine/2, yLine/2);
		g.setColor(Color.blue);
		g.fillOval(xPoint+(xLine/2)-(xLine/24), yPoint+(yLine/2)-(xLine/8), xLine/2, yLine/2);
		
		g.setColor(Color.white);
		g.drawRoundRect(8, 268, 24, 16 , 3, 3);
		int xPoints[] = {8,20,32};
		int yPoints[] = {268,275,268};
		g.drawPolyline(xPoints, yPoints, 3);
		g.setFont(font1);
		g.drawString("Tel.", 150, 280);
		
		g.drawLine(140, 265, 140, 290);
		g.drawLine(265, 265, 265, 290);
		
				
	}
		  
	
	public static void main(String[] args) {
		  new NameCard();
		 }
}
