import java.lang.*;
import java.util.Scanner;
/*
 * Indica a qual n�mero pertence a pot�ncia de 10 
 */
class PotenciasDe10 {
  public static void main (String[] args){
    Scanner numero;
    numero = new Scanner(System.in);
    System.out.println("Insira o primero valor: ");
    int x = numero.nextInt();    
    Switch(x){
      
      case 6 :  System.out.println(" Milh�o");
      break;
      case 9 : System.out.println("Bilh�o");
      break;
      case 12 : System.out.println(" Trilh�o"); 
      break;
      case 15 : System.out.println("          15             //     Quadrillion")
  }
}
    