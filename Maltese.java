/*
 * 15 Oct 2019
 * exercise6 maltese using override 
 * by Cho Keun Hee
 */

public class Maltese extends Dog{
 int spaces;

 public Maltese(String breed_, String size_, int age_, String color_) {
  super(breed_, size_, age_, color_);
  showData();
 }
 
 public Maltese() {
 }

 public void print20(String ss1, String ss2) {
  spaces = 20 - (ss1.length() + ss2.length());
  System.out.print(ss1);
  for (int i = spaces; i > 0; i--) {
   System.out.print(" ");
  }
  System.out.println(ss2);
 }
 
 public void showData(){
  Maltese maltese = new Maltese();
  maltese.print20("Breed is", breed );
  maltese.print20("Size is", size);
  maltese.print20("Age is", age + " years old");
  maltese.print20("Color is", color);
 }
 public static void main(String[] args) {
  new Maltese("Maltese", "Small", 2, "White");
 }
}