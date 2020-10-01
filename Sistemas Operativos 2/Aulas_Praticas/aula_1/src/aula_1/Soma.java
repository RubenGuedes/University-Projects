/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula_1;

import java.io.IOException;

/**
 * Soma
 */
public class Soma
{
    public static void main(String[] args) 
    {

        byte[] b = new byte[128];
        
        try {
            int lidos;
            Integer value;
            String byte_string;

            lidos = System.in.read(b);
            byte_string = new String(b, 0, lidos - 1);

            value = Integer.parseInt(byte_string);
            value++;

            System.out.println(value);
        } 
        catch (IOException e) 
        {
            System.out.printf("Nenhum cálculo efectuado!");
        }
        catch (NumberFormatException e)
        {
            System.out.println("Nenhum cálculo efectuado!");
        }
        finally
        {
            System.out.println("Finish");
        }
    }
}
