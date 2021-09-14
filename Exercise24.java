/*
 * Exercise 24 - Getting data from data.txt showing as a graph showing the frequancy of each number(0 to 9)
 * 				 Changing the length of bar based on the max number of number of data
 * by Cho keun hee
 * on Dec 10th 2019
 */


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileReader;
import javax.swing.JFrame;


public class Exercise24 extends JFrame{

	int record[] = new int[10];
 
 public Exercise24() {
	 setTitle("Exercise24");
	 setSize(500, 500);
	 setDefaultCloseOperation(EXIT_ON_CLOSE);
  	countData();
  	setVisible(true);
 	}
 
 public void paint(Graphics g) {
	 Font font1 = new Font("Dialoge", Font.PLAIN, 20);
	 g.setFont(font1);
	 	int unit = record[0];
	 	for (int i = 1; i < record.length; i++) {
	 		if (record[i] > unit) unit = record[i];
	 	}
	 	for(int i = 0; i < 10; i++) {
	 		g.setColor(Color.red);
	 		g.drawString(Integer.toString(i), 20, 70 + 40 * i);
	 		g.setColor(Color.blue);
	 		g.drawString(Integer.toString(record[i]), 50, 70 + 40 * i);
	 		g.fillRect(100, 50 + 40 * i, 380 / unit * record[i], 30);
	 	}
 }
 
 public void countData() {
	 String str;
  
	 try {
		 FileReader isr = new FileReader("data.txt");
		 BufferedReader br = new BufferedReader(isr);
		 str = br.readLine();
		 while(str != null) {
			 record[Integer.parseInt(str)]++;
			 str = br.readLine();
		 }
		 System.out.println("end of file loading");
		 br.close();
	 }catch (Exception e) {
		 System.out.println(e);
	 }
  	}
 
 public static void main(String[] args) {
	 new Exercise24();
 	}
}