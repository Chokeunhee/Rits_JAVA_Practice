/*
 * 15 Oct 2019
 * Exercise5 making a datashowing method (spitting in the middle) 
 * by Cho Keun Hee
 */

public class Exercise5 {
	
	public void print20(String ss1, String ss2) {
		/* Complete this part */
		int num = 20 - ss1.length() - ss2.length();
		System.out.print(ss1);
		for(int i=0; i<num; i++){
			System.out.print(" ");
		}
		System.out.println(ss2);
		
	}
	
	public static void main(String[] args) {
		Exercise5 ex5 = new Exercise5();
		ex5.print20("This is", "a program");
		ex5.print20("which makes", "the");
		ex5.print20("length of", "all line");
		ex5.print20("is", "20");
	}

}

