/* 
Sample Program: Displaying a Window
File: Ch2Sample1.java
*/
import javax.swing.*;
class Ch2Sample1 {
  public static void main (String[] args){
    JFrame window;
    window = new JFrame();
    window.setSize(800,600);
    window.setTitle("My First Program in Java");
    window.setVisible(true);
  }
}