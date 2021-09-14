/*
 * HomeWork3 - Displaying picturefile (adjusting size for dispalying)
 * 			 - Using mouseevent to capture a part of picture and displaying
 * 			 - Click button 1 to zoom in (using variable scale)
 * 			 - Click button 2 to zoom out (using variable scale) 
 * 			 - Click button 3 to rotate left (using variable rotate)
 *  		 - Click button 4 to rotate right (using variable rotate) 
 * By Cho keun hee
 * On Jan 13th 2020
 * 
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class HomeWork3 extends JFrame  implements ActionListener{
	BufferedImage bimage1, bimage2, getImage;
	JButton button1, button2, button3, button4;
	String filename = "imageA.jpg";
	int [][] rect = {{0,0},{0,0}};
	int [] center = {0,0};
	double rotate = 0.0;
	double scale = 1.0;
	double rate;
	
	public HomeWork3() {
		setSize(660, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MyListener myListener = new MyListener();
		addMouseListener(myListener);
		addMouseMotionListener(myListener);
		setVisible(true);
		button1 = new JButton("Zoom in");
		button1.addActionListener(this);
		button2 = new JButton("Zoom out");
		button2.addActionListener(this);
		button3 = new JButton("Left rotation");
		button3.addActionListener(this);
		button4 = new JButton("Right rotation");
		button4.addActionListener(this);
		JPanel panel1 = new JPanel();
		panel1.add(button1);
		panel1.add(button2);
		panel1.add(button3);
		panel1.add(button4);
		getContentPane().add(panel1, BorderLayout.NORTH);
		setVisible(true);
		initialize();
		repaint();
	}
	public void paint(Graphics g) {
		//super.paint(g);
		int hposition = 550;
		g.clearRect(10, 60, 660, 800);
		if (bimage1 != null) {
			g.drawImage(bimage1, 10, 60,
					bimage1.getWidth(), bimage1.getHeight(), this);
		}
		if (bimage2 != null) {
			g.drawImage(bimage2, 10, 60, bimage1.getWidth(), bimage1.getHeight(), this);
		}
		if(getImage!= null) {
			if (getImage.getHeight() > 240) {
				rate =240.0/(double)(getImage.getHeight());
			} else {
				rate = 1.0;
			}
			g.drawImage(getImage, 10, hposition, (int) (getImage.getWidth() *rate),(int) (getImage.getHeight()  *rate),this);
		}
		g.setColor(Color.red);
		g.drawRect(rect[0][0], rect[0][1], rect[1][0]-rect[0][0], rect[1][1]-rect[0][1]);

		if (center[0] != 0 && center[1] != 0) {
			int size = 4;
			g.setColor(Color.green);
			g.fillRect((center[0] + 10) - size/2, (center[1] + 60) - size/2, size, size);
		}
	}

	void initialize() {
		try {
			bimage1 = ImageIO.read(new File (filename));
			bimage2 = bimage1;
		}catch(Exception e) {
			System.out.print("error" + e);  
		}
	}

	public void GetImage() {
		getImage = bimage2.getSubimage(rect[0][0] - 10, rect[0][1] - 60, rect[1][0] - rect[0][0], rect[1][1] - rect[0][1]);
	}

	public void Change() {
		bimage2 = new BufferedImage(bimage1.getWidth(), bimage1.getHeight(), bimage1.getType());
		AffineTransform affine = new AffineTransform();
		affine.translate(center[0],center[1]);
		affine.scale(scale, scale);
		affine.rotate(Math.toRadians(rotate));
		affine.translate(-center[0], -center[1]);


		AffineTransformOp operator = new AffineTransformOp(
				affine, AffineTransformOp.TYPE_BICUBIC);
		operator.filter(bimage1, bimage2);

	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == button1) { 
			scale += 0.1;
			Change();
		}
		else if(e.getSource() == button2) {
			scale -= 0.1;
			Change();
		}
		else if(e.getSource() == button3) {
			rotate -= 1;
			Change();
		}
		else if(e.getSource() == button4) {
			rotate += 1;
			Change();
		}
		GetImage();
		repaint();
	}
	
	class MyListener extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			rect[0][0] = e.getX();
			rect[0][1] = e.getY();

		}

		public void mouseDragged(MouseEvent e) {
			rect[1][0] = e.getX();
			rect[1][1] = e.getY();
			repaint();
		}

		public void mouseReleased(MouseEvent e) {
			GetImage();
			center[0] = (rect[0][0] - 10) + getImage.getWidth()/2;
			center[1] = (rect[0][1] - 60) + getImage.getHeight()/2;   
			repaint();
		}

	}

	public static void main(String[] args) {
		new HomeWork3();
	}
}