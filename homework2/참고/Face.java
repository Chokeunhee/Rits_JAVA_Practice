/* PacMan Face class
 * Create by Chung Sung Woong
 * 2018/11/26
 */

import java.awt.Color;
import java.awt.Graphics;

public class Face {
	private int size;
	private int xCenter;
	private int yCenter;
	private int angle;

	public Face() {
	}
	
	
	public Face(int size_, int x_, int y_, int angle_) {
		setSize(size_);
		setXCenter(x_);
		setYCenter(y_);
		setAngle(angle_);
	}
	
	int getSize() {
		return(size);
	}
	
	void setSize(int num) {
		size = num;
		if(xCenter != 0 && yCenter != 0) {
			setXCenter(getXCenter());
		}
	}
	
	int getXCenter() {
		return(xCenter);
	}
	
	void setXCenter(int num) {
		if(size != 0) {
			if(num<size/2) {
				size = num*2;
			}
			if(num+size/2>400) {
				size = num/2;
			}
		}
		xCenter = num;
	}
	
	
	int getYCenter() {
		return(yCenter);
	}
	
	void setYCenter(int num) {
		if(size != 0) {
			if((num-size/2)<25) {
				size = num+5;
			}
			if((num+size/2)>300) {
				size = num-150;
			}
		}
		yCenter = num;
			
	}
	
	int getAngle() {
		return(angle);
		
	}
	
	void setAngle(int num) {
		angle = num;
		if(num>90) {
			angle=90;
		}else {
			angle=num;
		}
	}
	
	

	
	void make(Graphics g) {
		g.setColor(Color.yellow);
		g.fillArc(xCenter - size / 2, yCenter - size /2 , size , size, angle / 2, 360 - angle);
		
	}
}
	

