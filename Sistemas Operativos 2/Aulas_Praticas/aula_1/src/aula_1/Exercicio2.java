/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula_1;

import java.util.LinkedList;

/**
 * Exercício 2
 */
public class Exercicio2 
{
    public static void main(String[] args) 
    {
        LinkedList<String> list = new LinkedList<>(),
                           ord_list;      
    
        for (int i = 0; i < args.length; i++) 
            list.add(args[i]);  
        
        ord_list = insertionSort(list);
        System.out.println(ord_list.toString());
    }

    public static LinkedList<String> insertionSort(LinkedList<String> list)
    {
        LinkedList<String> return_list = new LinkedList<>();

        for (String s: list) 
            insert(s, return_list);    

        return return_list;
    }

    private static void insert(String s, LinkedList<String> list)
    {
        int pos = 0;

        // Procurar o local para inserir
        for( ; pos< list.size(); pos++)
        
            if (list.get(pos).compareTo(s) > 0)
                break;

        list.add(pos, s);
    }
}
