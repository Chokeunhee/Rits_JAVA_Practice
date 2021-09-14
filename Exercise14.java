/*
 * Exercise 14 lefr click one for blue circle right click for red square
 * by Cho keun hee
 * on November 12 2019
 */



import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;


public class Exercise14 extends JFrame{
	int size = 50;
	
	public Exercise14() {
		setSize(500,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		MyListener myListener = new MyListener();
		addMouseListener(myListener);
		
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, 500, 500);
	}
	
	public static void main(String[] args) {
		new Exercise14();
	}
	
	class MyListener extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			System.out.println(e.getModifiersEx());
			if((e.getModifiersEx() & MouseEvent.BUTTON3_DOWN_MASK)
					== MouseEvent.BUTTON3_DOWN_MASK) { 
				Graphics g = getGraphics();
				g.setColor(Color.red);
				g.fillRect(e.getX() -  size / 2, e.getY() - size / 2 , size, size);
				g.dispose();
			} else {
				Graphics g = getGraphics();
				g.setColor(Color.blue);
				g.fillOval(e.getX() -  size / 2, e.getY() - size / 2 , size, size);
				g.dispose();
			}
		}
	}
}