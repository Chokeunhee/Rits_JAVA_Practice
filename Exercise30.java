/*
 * Exercise30
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

public class Exercise30 extends JFrame implements ActionListener {
	BufferedImage bimage1, bimage2;
	String filename = "imageA.jpg";
	int xCenter, yCenter;
	double rotate = 0.0;
	JButton button1, button2;
	boolean flag1 = false;
 
 public Exercise30() {
	 setTitle("Exercise30");
	 setSize(700, 550);
	 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 MyListener myListener = new MyListener(); 
	 addMouseListener(myListener);
  
	 button1 = new JButton("Right Rotation");
	 button1.addActionListener(this);
	 button1.setActionCommand("W");
	 button2 = new JButton("Initialize");
	 button2.addActionListener(this);
	 button2.setActionCommand("G");
	 JPanel p  = new JPanel();
	 p.add(button1);
	 p.add(button2);
	 getContentPane().add(p, BorderLayout.NORTH);
	 setVisible(true);
	 initialization();
	 transform();
	 repaint();
 }
 
 public void paint(Graphics g) {
	 super.paint(g);
	 int size = 5;
	 if(bimage2 != null) {
		 g.drawImage(bimage2, 30, 60, this);
	 }
	 g.setColor(Color.red);
	 if(flag1)
		 g.fillRect(xCenter - size/2 + 30, yCenter - size/2 + 60, size, size);
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
 
 void transform() {
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
		 transform();
		 repaint();
  }
	 if(cmd.contentEquals("G")) {
		 rotate = 0.0;
		 transform();
		 repaint();
  }
 }
 
 public static void main(String[] args) {
	 new Exercise30();
 }

 class MyListener extends MouseAdapter {
	 public void mousePressed(MouseEvent e) {
		 flag1 = true;
		 xCenter = e.getX() - 30;
		 yCenter = e.getY() - 60;
		 transform();
		 repaint();
  }
 }
}