/*
 * Exercise25 Loading and display picture
 * 			  Changing the size of the picture based on the rate (using code)
 * On Dec 17th 2019
 * By Cho keun hee
 */


import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;


public class Exercise25 extends JFrame {
	BufferedImage bimage;
	String filename = "image1.jpg";
	double rate;
	
	public Exercise25() {
		setTitle("Exercise25");
		setSize(660,800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		takeImage();
		
	}
	
	public void paint(Graphics g) {
		if(bimage != null) {
			g.drawImage(bimage,10,60,(int)(bimage.getWidth()*rate),(int)(bimage.getHeight()*rate),this);
			System.out.println("width = " + (int)(bimage.getWidth()*rate) + " height = " + (int)(bimage.getHeight()*rate));
		}
	}
	
	public void takeImage() {
		try {
			File file = new File(filename);
			bimage = ImageIO.read(file);
			rate = 640.0 / bimage.getWidth();
			System.out.println("width = " + bimage.getWidth() + " height = " + bimage.getHeight());
			System.out.println("type : " + bimage.getType() + " field : " + BufferedImage.TYPE_3BYTE_BGR);
		} catch(IOException e) {
			System.out.println("error! " + e);
		}
		repaint();
	}
	

	public static void main(String[] args) {
		new Exercise25();
	}

}
