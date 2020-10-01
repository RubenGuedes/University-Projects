import java.lang.*;
import java.util.*;
class Sample4{
  public static void main(String[] args){
  Scanner scan = new Scanner(System.in);
  System.out.println("Valor de a:");
  double a = scan.nextDouble();
  System.out.println("Valor de b:");
  double b = scan.nextDouble();
  System.out.println("Valor de c:");
  double c = scan.nextDouble();
  double discri = Math.sqrt(Math.pow(b,2) - 4*a*c);
  double sol1 = (( -b )+ discri) /2*a ;
  double sol2 = (( -b ) - discri) /2*a ;
  System.out.print("Os resultados são: " + sol1 + ", " + sol2);
  
  }
}