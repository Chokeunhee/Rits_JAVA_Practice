/*
 * Exercise27 Image Segmentation by Mouse and saving 
 * 			  Elaborated based on Image5 from lecture class (Size change, location change)
 * On Dec 17th 2019
 * By Chokeunhee
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;

public class Exercise27 extends JFrame implements ActionListener{
	BufferedImage bimage, getImage;
	String filename = "image2.jpg";
	int rect[][] = {{0,0},{0,0}};
	JButton button1;
	
	public Exercise27() {
		setTitle("Exercise27");
		setSize(660,800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		MyListener myListener = new MyListener();
		addMouseListener(myListener);
		addMouseMotionListener(myListener);
		
		button1 = new JButton("save image");
		button1.addActionListener(this);
		button1.setActionCommand("btn1");
		 
		JPanel panel1 = new JPanel();
		panel1.add(button1);
		getContentPane().add(panel1, BorderLayout.NORTH);
		
		takeImage();
		setVisible(true);
	}
	
	public void paint(Graphics g) {
		int hposition = 550;
		
		g.clearRect(0, 60, 660, 800);
		if(bimage != null) {
			g.drawImage(bimage,10,60,this);
		}
		if(getImage != null) {
			g.drawImage(getImage, 10, hposition, this);
		}
		g.setColor(Color.red);
		g.drawRect(rect[0][0], rect[0][1], rect[1][0] - rect[0][0], rect[1][1] - rect[0][1]);
	}
	
	public void takeImage() {
		try {
			File file = new File(filename);
			bimage = ImageIO.read(file);
			
			
			
		} catch(IOException e) {
		}
		repaint();
	}
	
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		 if(cmd.contentEquals("btn1")){
			 BufferedImage simage = getImage;
			 File output = new File("save.jpg");
			 try {
				ImageIO.write(simage, "jpg" , output);
			} catch (IOException e1) {
				System.out.println(e1);
			}
		 }
	}
	

	public static void main(String[] args) {
		new Exercise27();
		
	}
	
	class MyListener extends MouseAdapter{
		int cx, cy;
		String str;
		
		public void mousePressed(MouseEvent e) {
			rect[0][0] = e.getX();
			rect[0][1] = e.getY();
		}
		
		public void mouseReleased(MouseEvent e) {
			getImage = bimage.getSubimage(rect[0][0] - 10, rect[0][1] -60, rect[1][0] - rect[0][0], rect[1][1] - rect[0][1]);
			repaint();
		}
		
		public void mouseDragged(MouseEvent e) {
			rect[1][0] = e.getX();
			rect[1][1] = e.getY();
			repaint();
		}
	}

}











