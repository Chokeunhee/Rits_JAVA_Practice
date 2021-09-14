/*
 * Exercise29
 * 
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;


public class Exercise29 extends JFrame implements ActionListener{

	BufferedImage bimage1, bimage2;
	String filename = "imageA.jpg";
	int xCenter, yCenter;
	double rotate = 0.0;
	JButton button1;
 
 public Exercise29() {
	 setTitle("Exercise29");
	 setSize(700, 550);
	 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  
	 button1 = new JButton("Right rotation");
	 button1.addActionListener(this);
	 button1.setActionCommand("W");
	 JPanel p  = new JPanel();
	 p.add(button1);
	 getContentPane().add(p, BorderLayout.NORTH);
	 setVisible(true);
	 initialization();
	 change();
	 repaint();
 }
 
 public void paint(Graphics g) {
	 super.paint(g);
	 if(bimage2 != null) {
		 g.drawImage(bimage2, 30, 60, this);
	 }
	 g.setColor(Color.red);
 	}
 
 public void initialization() {
	 try {
		 File file = new File(filename);
		 bimage1 = ImageIO.read(file);
		 xCenter = bimage1.getWidth() / 2;
		 yCenter = bimage1.getHeight() / 2;
	 } catch (IOException e) {
		 System.out.println("error" + e);
	 }
 }
 
 void change() {
	 bimage2 = new BufferedImage(bimage1.getWidth(), bimage1.getHeight(), bimage1.getType());
	 AffineTransform affine = new AffineTransform();
	 affine.translate(xCenter, yCenter);
	 affine.rotate(rotate);
	 affine.translate(-xCenter, -yCenter);
	 AffineTransformOp operator = new AffineTransformOp(affine, AffineTransformOp.TYPE_BICUBIC);
	 operator.filter(bimage1, bimage2);
 }
 
 public void actionPerformed (ActionEvent e) {
	 String cmd = e.getActionCommand();
	 if(cmd.equals("W")) {
		 rotate += Math.toRadians(1.0);
		 change();
		 repaint();
	 }
 }
 
 public static void main(String[] args) {
	 new Exercise29();
 	}
}