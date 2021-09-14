/*
 * 15 Oct 2019
 * Exercise4 Dog info Doberman by the method by calss dog (inheritance)
 * by Cho Keun Hee
 */


public class Doberman extends Dog {
	 
	public Doberman(String breed_, String size_, int age_, String color_) {
		super(breed_, size_, age_, color_);
		showData();
	 	}
	 
	public static void main(String[] args) {
		new Doberman("Doberman", "Large", 5, "Black");
	 	}
	}

