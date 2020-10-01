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
public class Inscrito 
{
    String numero,
           codigo;

    public Inscrito(String numero, String codigo) 
    {
        this.numero = numero;
        this.codigo = codigo;
    }

    @Override
    public String toString() 
    {
        return String.format(
                "Numero do aluno = %s; Código da disciplina = %s",
                numero, codigo
        );
    }
}
