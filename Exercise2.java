/*
 * 8 Oct2019
 * Exercise2 wordcount character including space
 * by Cho keun hee
 */


public class Exercise2 {
	
	String sentence = "Students gain their practical skills together through project based learning";
	String[] list;
	
	String string4 = "The number of word is ";
	String string5 = "The number of characters including spaces is ";
	
	public Exercise2() {
	list = sentence.split(" ");
	System.out.println(string4 + list.length);
	System.out.println(string5 + sentence.length());
	
	}
	
	public static void main(String[] args) {
		  new Exercise2();
		 }
}