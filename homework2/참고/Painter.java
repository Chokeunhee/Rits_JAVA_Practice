	/*
	 * Pacman With Create, check, change mode
	 * Created by Chung Sung Woong
	 * Date: 2018/11/26
	 */

	import java.awt.BorderLayout; 
	import java.awt.Color;
	import java.awt.Graphics;
	import java.awt.event.MouseAdapter; 
	import java.awt.event.MouseEvent; 
	import javax.swing.JButton;
	import javax.swing.JFrame; 
	import javax.swing.JPanel; 
	import javax.swing.JTextField;

	public class Painter extends JFrame{
		Face face[] = new Face[10];
		int count = -1; 
		int flag = 0; 
		int flag1 = 0;
		
		JButton button1; 
		JButton button2;
		JTextField text;


	public Painter(){
		setTitle("Painter");
		setSize(500,500); 
		setDefaultCloseOperation(EXIT_ON_CLOSE); 
		setBackground(Color.white);
		MyListener myListener = new MyListener(); 
		addMouseListener(myListener); 
		addMouseMotionListener(myListener);
		button1 = new JButton(); 
		button1.addMouseListener(new ButtonListener1()); 
		button1.setForeground(Color.red);
		button2  = new JButton();
		button2.addMouseListener(new ButtonListener2());
		button2.setForeground(Color.black);
		text = new JTextField();
		text.setColumns(16);
		JPanel p = new JPanel();
		p.add(button2);
		p.add(button1);
		p.add(text);
		getContentPane().add(p, BorderLayout.SOUTH);
		button2.setText("Create Mode");
		button1.setText("PacMan"); 
		text.setText("Click the center point"); 
		setVisible(true);
	}
	
	public void paint(Graphics g){ super.paint(g);
		g.clearRect(0, 0, 500, 450); 
		if(count >= 0){
			if(count > 9){
				count = 9; 
				}
			for(int i = 0; i < count + 1; i++){
				face[i].make(g); 
				}
		} 
	}
	
	public static void main(String[] args) {
		new Painter(); 
	}
	class MyListener extends MouseAdapter { 
		int distance, xCenter, yCenter;

		public void mousePressed(MouseEvent e) {
			if(flag1 == 0){
				if(++count < 10){
					if(flag == 0) {
						face[count] = new PacMan(0, e.getX(), e.getY(), 60);
					} else if(flag == 1){
						face[count] = new PacManWithEye(0, e.getX(), e.getY(), 60); 
					} else if(flag == 2){
						face[count] = new PacManTeasing(0, e.getX(), e.getY(), 60);
					}
					repaint();
					text.setText("The center is (" + e.getX() + ", " + e.getY() + ")"); 
				} else {
					text.setText("No more PacMan."); 
				}
			}
			
			if(flag1 == 1){
				Boolean found = false;
				for(int num = 0; num < count + 1; num++) {
					if(Math.hypot(e.getX() - face[num].getXCenter(),
							e.getY() - face[num].getYCenter()) < face[num].getSize() / 2) {
						found = true;
						if(face[num] instanceof PacMan) {
							text.setText("This is PacMan");
						} else if(face[num] instanceof PacManWithEye) {
								text.setText("This is PacManWithEye"); 
						} else if(face[num] instanceof PacManTeasing) {
							text.setText("This is PacManTeasing");
						} else {
							text.setText("Type is not found"); 
							}
						} 
					}
					if(!found) {
						text.setText("Press a Pac-Man");
				}
		}
			
			if(flag1 == 2) {
				Boolean found = false;
					for(int num = 0; num < count + 1; num++) {
						if(Math.hypot(e.getX() - face[num].getXCenter(),
								e.getY() - face[num].getYCenter()) < face[num].getSize() / 2) {
							found = true;
							if(face[num] instanceof PacMan) {
								if(flag == 0) {
									text.setText("Already PacMan");
								} else if(flag == 1) {
									face[num] = new PacManWithEye(face[num].getSize(),
											face[num].getXCenter(), face[num].getYCenter(), face[num].getAngle());
								} else if(flag == 2) {
									face[num] = new PacManTeasing(face[num].getSize(),
											face[num].getXCenter(), face[num].getYCenter(), face[num].getAngle());
									}
										repaint(); 
								}
								else if(face[num] instanceof PacManWithEye) { 
									if(flag == 1) {
										text.setText("Already PacManWithEye"); 
									} else if(flag == 0) {
										face[num] = new PacMan(face[num].getSize(), face[num].getXCenter(), face[num].getYCenter(),
										face[num].getAngle());
									} else if(flag == 2) {
										face[num] = new PacManTeasing(face[num].getSize(),
										face[num].getXCenter(), face[num].getYCenter(), face[num].getAngle());
									}
										repaint(); 
								} 
							 else if(face[num] instanceof PacManTeasing) { 
								if(flag == 2) {
									text.setText("Already PacManTeasing"); 
								} else if(flag == 0) {
									face[num] = new PacMan(face[num].getSize(), face[num].getXCenter(), face[num].getYCenter(),
										face[num].getAngle());
								} else if(flag == 1) {
									face[num] = new PacManWithEye(face[num].getSize(),
										face[num].getXCenter(), face[num].getYCenter(), face[num].getAngle());
								}
								repaint(); 
							}
							else{
								text.setText("Type is not found");
							}	
							
						if(!found) {
							text.setText("Press a Pac-Man"); 
							}
						}
					}
				}
			}			
	
			public void mouseDragged(MouseEvent e) { 
				if(count < 10){
					distance = (int)Math.hypot(e.getX() - face[count].getXCenter(), e.getY() - face[count].getYCenter());
					face[count].setSize(distance * 2); 
					text.setText(Integer.toString(distance * 2)); 
					repaint();
				}
			}
		}

		class ButtonListener1 extends MouseAdapter{ 
			public void mousePressed(MouseEvent e){
				if(++flag > 2){
					flag = 0; 
				}
				if(flag == 0){ button1.setText("PacMan");
			} else if(flag == 1){
				button1.setText("PacManWithEye"); 
			} else if(flag == 2) {
				button1.setText("PacManTeasing");
			}
		}
	}

	class ButtonListener2 extends MouseAdapter{ 
		public void mousePressed(MouseEvent e){
			if(++flag1 > 2){
				flag1 = 0; 
			}	
			if(flag1 == 0){ 
				button2.setText("Create Mode");
			}
			if(flag1 == 1){
				button2.setText("Check Mode"); 
			}
			if(flag1 == 2) {
				button2.setText("Change Mode");
			}
		}
	}
}
	