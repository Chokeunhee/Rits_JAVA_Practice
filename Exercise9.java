/*
 * Oct 29 2019
 * Exercise9 display cone which change
 * by Cho keunhee
 */


import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;

public class Exercise9 extends JFrame{
 
 public Exercise9() {
  setSize(400,300);
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  setVisible(true);
 }
 
 
 
 
 public void paint(Graphics g) {
  for (int i = 0; i < 50 ; i++) {
   g.setColor(new Color(55 + 4 * i,255,255));
   g.fillOval(100+2*i, 190-3*i, 200-4*i, 100-2*i);
  }
 }

 
 
 
 
 public static void main(String[] args) {
  new Exercise9();
 }
}