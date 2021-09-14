/* 8th oct 2019
 * exercise3 sentence wordcount(with space) character length and average
 * by Cho keun hee
 */


public class Exercise3 {
	String sentence = "Students gain their practical skills together through project based learning";
	String [] list;
	

	public static void main(String[] args) {
		new Exercise3();
	}
	
	public Exercise3() {
		list = sentence.split(" ");
		double total = 0;
		
		for (int i=0; i<list.length;i++) {
			total=total + list[i].length();
		}
		System.out.println("The number of word is " + list.length);
		System.out.println("The number of character including space is " + sentence.length());
		System.out.println("The total word length is " + total);
		System.out.println("The average word length is " + total/list.length);
	}
	
}