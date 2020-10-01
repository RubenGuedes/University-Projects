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
public class Classificacao implements Comparable
{
    int nota;
    short numero;
    String descr, obs;
    
    public Classificacao(short numero, int nota)
    {
        this.nota = nota;
        this.numero = numero;
        this.descr = "";
        this.obs = "";
    }
    
    public Classificacao(short numero, int nota, String descr, String obs)
    {
        this.nota = nota;
        this.numero = numero;
        this.descr = descr;
        this.obs = obs;
    }
    
    @Override
    public String toString()
    {
        return "[aluno: " + Short.toString(numero) + " , nota: " + Integer.toString(nota) +"]";
    }
    
    /**
     *
     * @param t
     * @return
     */
    @Override
    public int compareTo(Object t)
    {
        Classificacao comp = (Classificacao) t;
        
        if (this.nota == comp.nota)
            return 0;
        
        else if (this.nota < comp.nota)
            return -1;
        
        else 
            return 1;
    }
}