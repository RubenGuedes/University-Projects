/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so2.db;

/**
 *
 * @author rp
 */
public class Aluno {
    int numero, telefone;
    String nome, bi;

    public Aluno(int numero, int telefone, String nome, String bi) 
    {
        this.numero = numero;
        this.telefone = telefone;
        this.nome = nome;
        this.bi = bi;
    }
    
    @Override
    public String toString()
    {
        return String.format(
                "Nome = %s; Numero = %d; Bi = %s; Telefone = %d", 
                nome, numero, bi, telefone
        );
    }
}
