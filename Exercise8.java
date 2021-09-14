/*
 * Oct 29 2019
 * Exercise8 drawing four pacman (position, size and color)
 * by Cho keunhee
 */


import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;


public class Exercise8 extends JFrame{
	
	int[][] position = {{20, 50}, {200, 80}, {80, 150}, {220, 170}};
	Color[] color = {Color.red, Color.green, Color.blue, Color.cyan};
	int[] size = {60, 40, 90, 120};
	
	public Exercise8( ) {
		setSize(400,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);	
	}
	
	public void paint(Graphics g) {
		
		for(int i=0; i<position.length; i++) {
			g.setColor(color[i]);
			g.fillArc(position[i][0],position[i][1],size[i],size[i],30,300);
		}		
			
	}
	public static void main(String[] args ) {
		new Exercise8();
	}
}