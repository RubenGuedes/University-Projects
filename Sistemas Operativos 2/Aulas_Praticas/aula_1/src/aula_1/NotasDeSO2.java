/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula_1;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author rp
 */
public class NotasDeSO2 
{
    List<Classificacao> notas_aluno;

    public NotasDeSO2() 
    {
        this.notas_aluno = new LinkedList<>();
    }
    
    void adicionaClassif(Classificacao c)
    {
        this.notas_aluno.add(c);
    }
    
    List<Classificacao> getListaOrdenadaDeClassif()
    {
        List<Classificacao> lista_auxiliar = new LinkedList<>();
        
        for (Classificacao c : this.notas_aluno)
            insert(c, lista_auxiliar);
        
        return lista_auxiliar;
    }

    private static void insert(Classificacao s, List<Classificacao> list)
    {
        int pos = 0;
        
        for( ; pos< list.size(); pos++)
        
            if (list.get(pos).compareTo(s) > 0)
                break;

        list.add(pos, s);
    }
}