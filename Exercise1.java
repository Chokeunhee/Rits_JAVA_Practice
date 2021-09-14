/*
 * 8 Oct2019
 * Exercise1 total max and average
 * by Cho keun hee
 */


public class Exercise1 {
	
	double[] data = {12.03, 7.34, 23.42, 94.85, 7.83, 3.19, 43.23};
	
	public Exercise1() {
		
		double total = 0.0;
		double max = 0.0;
			for (int i = 0; i < data.length; i++) {
				total += data[i];
				if (max < data[i]) { 
					max = data[i];
				}
			}
		
		
		
		double average = total/data.length;
		
		

		
		String string1 = "The total is ";
		String string2 = "The average is ";
		String string3 = "The max value is ";
		
		System.out.println(string1 + total);
		System.out.println(string2 + average);
		System.out.println(string3 + max);
	
	}
	

	public static void main(String[] args) { 
		new Exercise1();
	}
	
}
