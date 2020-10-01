import galapagos.*;
import java.util.*;
class Sample10{
  public static void main(String[] args){
    Turtle myturtle = new Turtle();
    Scanner tartaruga = new Scanner(System.in);
    System.out.println(" Largura do rec: ");
    double larg = tartaruga.nextDouble();
    System.out.println("Comprimento do rec:");
    double comp = tartaruga.nextDouble();
    myturtle.move( comp);
    myturtle.turn(90);
    myturtle.move(larg);
    myturtle.turn(90);
    myturtle.move(comp);
    myturtle.turn(90);
    myturtle.move(larg);
    
    myturtle.move( comp*1.4);
    myturtle.turn(90);
    myturtle.move(larg*1.4);
    myturtle.turn(90);
    myturtle.move(comp*1.4);
    myturtle.turn(90);
    myturtle.move(larg*1.4);
    
    myturtle.move( comp*1.8);
    myturtle.turn(90);
    myturtle.move(larg*1.8);
    myturtle.turn(90);
    myturtle.move(comp*1.8);
    myturtle.turn(90);
    myturtle.move(larg*1.8);
    
  }
}