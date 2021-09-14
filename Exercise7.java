/*
 * Oct 29 2019
 * Exercise7 drawing four colored rectangle
 * by Cho keunhee
 */

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;


public class Exercise7 extends JFrame{
	
	int[][] position = {{20, 50}, {200, 80}, {80, 150}, {220, 170}};
	Color[] color = {Color.red, Color.green, Color.blue, Color.cyan};
	
	public Exercise7( ) {
		setSize(400,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);	
	}
	public void paint(Graphics g) {
		for(int i=0; i<position.length; i++) {
			g.setColor(color[i]);
			g.fillRect(position[i][0],position[i][1],60, 30);
		}		
	}
	
	public static void main(String[] args ) {
		new Exercise7();
	}
	
}
