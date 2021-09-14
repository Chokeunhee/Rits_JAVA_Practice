/*
 * Exercise28 making bimage2 with combination of tranlate, rotate and scale from imageA.jpg
 * on Dec 24
 * by Cho keun hee
 * 
 */


import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Exercise28 extends JFrame {
	BufferedImage bimage1, bimage2;
	String filename = "imageA.jpg";
	int xCenter, yCenter;
	double scale = 0.7;
	double rotate = Math.toRadians(-7);
	
	public Exercise28() {
		setSize(700,800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		initialization();
		transform();
		
		repaint();
	}
	
	public void paint(Graphics g) {
		int hposition = 550;
		
		
		if (bimage1 != null) {
			g.drawImage(bimage1, 30, 60, bimage1.getWidth(), bimage1.getHeight(), this);
		}
		if (bimage2 != null) {
			g.drawImage(bimage2, 30, hposition, bimage2.getWidth()/2, bimage2.getHeight()/2, this);
		}
	}
	
	void initialization() {
		try {
			bimage1 = ImageIO.read(new File(filename));
		} catch (Exception e) {
			System.out.println("error" + e);
		}
	}
	
	void transform() {
		
		xCenter = bimage1.getWidth()/2;
		yCenter = bimage1.getHeight()/2;		
		bimage2 = new BufferedImage(bimage1.getWidth(),
				bimage1.getHeight(), bimage1.getType());
		AffineTransform affine = new AffineTransform();
		
		affine.translate(xCenter,yCenter);
		affine.scale(scale,scale);
		affine.rotate(rotate);
		affine.translate(-xCenter, -yCenter);
		
		AffineTransformOp operater =
				new AffineTransformOp(affine, AffineTransformOp.TYPE_BICUBIC);
		
		operater.filter(bimage1,bimage2);
	}

	
	public static void main(String[] args) {
		new Exercise28();

	}

}
