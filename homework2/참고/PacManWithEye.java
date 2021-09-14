/*
	 * PacMan with a black Eye
	 * Created by Chung Sung Woong
	 * Date: 2018/11/26
	 */
import java.awt.Color;
import java.awt.Graphics;

public class PacManWithEye extends Face {
	
	public PacManWithEye() {
		
	}
	
	public PacManWithEye(int size_, int x_, int y_, int angle_) {
		super(size_,  x_,  y_, angle_);
	}

	void make(Graphics g) {
		int xCenter = super.getXCenter();
		int yCenter = super.getYCenter();
		int size = super.getSize();
		super.make(g);
		g.setColor(Color.black);
		g.fillOval(xCenter - size / 12,  yCenter - size / 3, size / 6, size / 6);
	}
}
	