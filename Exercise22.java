/*
 * Exercise22 - making a txt file named "numbers.txt" by inputing numbers end if negative number inputed
 * by Cho keun hee
 * on Dec 10th 2019
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;


public class Exercise22 {
	BufferedReader br;
	
	public Exercise22() {
		int data;
		
		
		InputStreamReader isr = new InputStreamReader(System.in);
		br = new BufferedReader(isr);
  
		try {
			FileWriter fw = new FileWriter("numbers.txt");
			BufferedWriter bw = new BufferedWriter(fw);
			do {
				data = getData();
				if (data > 0) {
					System.out.println(data);
					bw.write(Integer.toString(data));
					bw.newLine();
				} else {
					System.out.println("not integer or negative number");
				}
			} while (data >= 0);
			System.out.println("The system was terminatend");		
			
			bw.close();
		} catch (IOException e) {
			System.out.println(e);
			}
		}

	public int getData() {
		String kb;
		int data;
		try {
			System.out.print("Input: ");
			kb = br.readLine();
			data = Integer.parseInt(kb);
			return data;
		} catch (Exception e) {
			System.out.println(e);
			return 0;
		}
	}

 public static void main(String[] args) {
	 new Exercise22();
 	}
}




