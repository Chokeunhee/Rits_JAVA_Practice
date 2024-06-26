/*
	 * PacMan Data
	 * Created by Chung Sung Woong
	 * Date: 2018/11/26
	 */
import java.awt.Color;
import java.awt.Graphics;

public class PacMan extends Face{
	int special = 7;

	public PacMan(int size_, int x_, int y_, int angle_) {
		super(size_, x_, y_, angle_);
	}
	
	void make(Graphics g) {
		super.make(g);
	}
	
	void showData() {
		System.out.println("The size is " + getSize());
		System.out.println("The coordinate of center is (" + getXCenter() + ", " + getYCenter() + ")");
	}
}
