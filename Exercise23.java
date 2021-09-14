/*
 * Exercise23 - based on the numbers.txt created by exercise22 listing and counting the number and average.
 * by Cho keun hee
 * on Dec 10th 2019
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Exercise23 {
 public Exercise23() {
	 String str;
	 double count = 0, total = 0;
	 double Y;
	 int X;

	 
  
	 try {
		 FileReader isr = new FileReader("numbers.txt");
		 BufferedReader br = new BufferedReader(isr);
   
		 str = br.readLine();
		 while(str !=null) {
			 System.out.println(str);
			 total += Integer.parseInt(str);
			 count++;
			 str = br.readLine();
		}
		X = (int)count;
		Y = total/count;
		System.out.println("the number of data is " + X  + ", and the average is " + Y);
		br.close();
	 } catch (IOException e) {
	 	System.out.println (e);
	 }
 }
 
 public static void main(String[] args) {
	 new Exercise23();
}
}

