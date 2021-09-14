/*
 * Exercise 15 draw pacman using mousepressed and mousedragged to drag pacman bigger
 * by Cho keun hee
 * on November 12 2019
 */



import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;


public class Exercise15 extends JFrame {
	int size, xCenter, yCenter;
	
	public Exercise15() {
		setSize(500,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		MyListener myListener = new MyListener();
		addMouseListener(myListener);
		addMouseMotionListener(myListener);
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, 500, 500);
		g.setColor(Color.yellow);
		g.fillArc(xCenter - size / 2, yCenter - size / 2, size, size, 30, 300);
	}
	
	public static void main(String[] args) {
		new Exercise15();
	}
	
	class MyListener extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			xCenter = e.getX();
			yCenter = e.getY();
		}
		
		public void mouseDragged(MouseEvent e) {
			size = (int)Math.hypot(xCenter - e.getX(), yCenter - e.getY())*2;
			repaint();
		}
	}
}