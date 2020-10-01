/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package veiculos;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Arrays;

/**
 *
 * @author rp
 */
public class VeiculosClient 
{
    private int port;
    private String address;
    
    public VeiculosClient(String address, int port)
    {
        this.port = port;
        this.address = address;
    }
    /* 
        3 argumentos 
        consulta: dada uma matrícula espera-se o nome do proprietário
                  cliente executado com 3 argumentos
    */
    public String consulta(String matricula)
    {
        String proprietario = null;
        Socket client;
        
        try 
        {
            client = new Socket(address, port);
            
            // Envia a matricula
            DataOutputStream sout= new DataOutputStream(
                    client.getOutputStream()
            );
            sout.writeBytes(matricula + "\n");

            
            // Recebe Nome do proprietário
            BufferedReader breader= new BufferedReader(
                new InputStreamReader(
                    client.getInputStream()
                )
            );
            proprietario = breader.readLine();
            client.close();

            return proprietario;
        } 
        catch (IOException e) 
        {
            System.out.println(e.getMessage());
        }
        
        return proprietario;
    }
    
    /*
        registo: o cliente fornece ao servidor um par (matrícula,nome) para registo. 
        Neste exercício, o registo pode ser feito numa estrutura de dados em memória.
                cliente executado com 4 argumentos
    */  
    public String regista(String matricula, String nome) 
    {
        Socket client;
        String guardou = null;
        
        try 
        {
            client = new Socket(address, port);
            String novaMensagem = matricula + "|" + nome + '\n';
            
            // Envia a matricula e nome
            DataOutputStream sout= new DataOutputStream(
                    client.getOutputStream()
            );
            sout.writeBytes(novaMensagem);
            
            // Recebe a informação se foi tudo adicionado na base de dados
            BufferedReader breader = new BufferedReader(
                new InputStreamReader(
                    client.getInputStream()
                )
            );
            guardou = breader.readLine();
            client.close();
            
            return guardou;
        } 
        catch (IOException e) 
        {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
        
        return guardou;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        if (args.length < 3)
            System.out.println("Quantidade insuficiente de argumentos!");
        
        else if ( args.length == 3 ) 
        {
            int port = Integer.parseInt(args[0]);
            String address = args[1];
            String matricula = args[2];
            
            VeiculosClient client1 = new VeiculosClient(address, port);
            String prop = client1.consulta(matricula);
        
            System.out.println("Proprietario = " + prop);
        }
        
        else if ( args.length == 4 )
        {
            int port = Integer.parseInt(args[0]);
            String address = args[1];
            String matricula = args[2];
            String nome = args[3];
            
            
            VeiculosClient client1 = new VeiculosClient(address, port);
            String guardou = client1.regista(matricula, nome);
            
            String prop = null;
            if (guardou != null)
                prop = client1.consulta(matricula);
        
            System.out.println("Proprietário = " + prop);
        }
        
        else
            System.out.println("Argumentos em excesso!");
    }
}