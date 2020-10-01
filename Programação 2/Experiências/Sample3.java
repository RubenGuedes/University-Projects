import java.lang.*;
import java.util.*;
class Sample3 {
  public static void main(String[] args) {
    Scanner raio = new Scanner(System.in);
    System.out.println("Indique o raio: ");
    double raio2 = raio.nextDouble();
    double volume_esf = (4/3)*Math.pow(raio2,3)*Math.PI;
    System.out.println("O volume da esfera é " + volume_esf);
  }
}