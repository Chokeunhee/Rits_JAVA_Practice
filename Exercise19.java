/*
 * Exercise 19 using MouseListener(press to make red circle / Enter to show string enter / Exit to not show string Enter)
 * By Chokeunhee
 * made on Dec 3rd 2019
 */


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;


public class Exercise19 extends JFrame implements MouseListener {
	boolean flag1 = false;
	boolean flag2 = false;
	Color color;
	int xCenter, yCenter;
	int size = 100;
	
	public Exercise19() {
		setTitle("Painter");
		setSize(500,500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		addMouseListener(this);
		setVisible(true);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		g.clearRect(0, 0, 500, 500);
		if(flag1) {
			Font font1 = new Font("Dialoge", Font.PLAIN, 20);
			g.setFont(font1);
			g.setColor(Color.red);
			g.drawString("Enter", 440, 50);
		}
		if(flag2) {
			g.setColor(color);
			g.fillOval(xCenter - size/2, yCenter - size/2, size, size);
		}
	}
	
	public void mouseClicked(MouseEvent e) {
	}
	
	public void mouseEntered(MouseEvent e) {
		flag1 = true;
		repaint();
	}
	
	public void mouseExited(MouseEvent e) {
		flag1 = false;
		repaint();
	}
	
	public void mousePressed(MouseEvent e) {
		flag2 = true;
		xCenter = e.getX();
		yCenter = e.getY();
		color = Color.red;
		repaint();
	}
	
	public void mouseReleased(MouseEvent e) {
	}

	public static void main(String[] args) {
		new Exercise19();

	}

}
