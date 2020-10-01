import javax.swing.JFrame;
import java.util.Scanner;
/*
 * Criar um programa que me peça o nome da pessoa e crie uma janela cujo o título é o apelido seguido do nome
 */ 

class Ch2Pag79ex18{
  public static void main (String[] args){
    Scanner nome;
    String usuario1, usuario2; 
    JFrame janela;
    
    nome = new Scanner(System.in);
    janela = new JFrame();
    
    // Para o utilizador introduzir o seu nome.
    System.out.println(usuario1 = nome.next());
    System.out.println(usuario2 = nome.next());
    
    
    // Janela
    janela.setSize(1000,1000);
    janela.setTitle(usuario2 + " " +usuario1);
    janela.setVisible(true);
  }
}