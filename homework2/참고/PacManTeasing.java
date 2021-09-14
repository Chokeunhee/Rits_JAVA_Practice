	/*
	 * PacMan With tongue/ Teasing Someone
	 * Created by Chung Sung Woong
	 * Date: 2018/11/26
	 */
import java.awt.Color;
import java.awt.Graphics;

public class PacManTeasing extends Face {
	
	public PacManTeasing() {
		
	}
	
	public PacManTeasing(int size_, int x_, int y_, int angle_) {
		super(size_,  x_,  y_, angle_);
	}
 
	
	
	void make(Graphics g) {
		int xCenter = super.getXCenter();
		int yCenter = super.getYCenter();
		int size = super.getSize();
		super.make(g);
		g.setColor(Color.black);
		g.fillArc(xCenter - size / 12,  yCenter - size / 3, size / 6, size / 6, 160, 180);
		for(int m = 0; m < 25; m++) {
			g.setColor(new Color(246, 49, 0));
			g.fillOval((xCenter +3 +2*m), (yCenter - 3 - m), (size/10+m), (size/10+m)); //Tongue 
		}
	}
}