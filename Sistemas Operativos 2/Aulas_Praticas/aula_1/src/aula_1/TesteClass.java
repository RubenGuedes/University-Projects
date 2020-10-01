/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula_1;

/**
 *
 * @author rp
 */
public class TesteClass 
{    
    public static void main(String[] args)
    {
        short num_aluno = 1500;
    
        NotasDeSO2 aluno = new NotasDeSO2();
    
        Classificacao teste1 = new Classificacao(num_aluno, 10);
        Classificacao teste2 = new Classificacao(num_aluno, 12);
        Classificacao teste3 = new Classificacao(num_aluno, 15);
        
        aluno.adicionaClassif(teste2);
        aluno.adicionaClassif(teste1);
        aluno.adicionaClassif(teste3);
        
        System.out.println(aluno.notas_aluno.toString());
        
        System.out.println(aluno.getListaOrdenadaDeClassif().toString());
    }
}
