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
public class Disciplina 
{
    String codigo,
           nome,
           descricao;

    public Disciplina(String codigo, String nome, String descricao) 
    {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
    }
    
    @Override
    public String toString()
    {
        return String.format(
                "Código = %s; Nome = %s; Descricao = %s",
                codigo, nome, descricao
        );
    }
}
