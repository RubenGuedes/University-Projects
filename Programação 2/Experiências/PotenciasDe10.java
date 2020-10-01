import java.lang.*;
import java.util.Scanner;
/*
 * Indica a qual número pertence a potência de 10 
 */
class PotenciasDe10 {
  public static void main (String[] args){
    Scanner numero;
    numero = new Scanner(System.in);
    System.out.println("Insira o primero valor: ");
    int x = numero.nextInt();    
    Switch(x){
      
      case 6 :  System.out.println(" Milhão");
      break;
      case 9 : System.out.println("Bilhão");
      break;
      case 12 : System.out.println(" Trilhão"); 
      break;
      case 15 : System.out.println("          15             //     Quadrillion")
  }
}
    