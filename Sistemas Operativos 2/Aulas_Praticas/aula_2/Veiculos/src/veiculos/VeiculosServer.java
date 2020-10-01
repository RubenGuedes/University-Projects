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
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

/**
 *
 * @author rp
 */
public class VeiculosServer 
{
    private int serverPort;
    
    public VeiculosServer(int port)
    {
        serverPort = port;
    }
    
    public void servico(HashMap<String, String> storage) throws IOException
    {
        ServerSocket server = new ServerSocket(serverPort);
        Socket serverClient;
        
        while (true) 
        {
            try 
            {
                serverClient = server.accept();
            
                // Recebe a informação se foi tudo adicionado na base de dados
                BufferedReader breader= new BufferedReader(
                    new InputStreamReader(
                        serverClient.getInputStream()
                    )
                );
                String msg = breader.readLine();
        
                if (msg.contains("|"))
                {
                    // [matricula, nome]
                    String matricula = msg.substring(0, msg.indexOf("|")),
                           proprietario = msg.substring(msg.indexOf("|") + 1);

                    storage.put(matricula, proprietario);
                
                    // Envia a matricula
                    DataOutputStream sout= new DataOutputStream(
                        serverClient.getOutputStream()
                    );
                    sout.writeBytes("Novos dados guardados!\n");
                }
                else
                {
                    String refactored_msg = msg.replace("\n", "");
                    String proprietario = storage.get( refactored_msg );
                
                    // Envia o nome do proprietario
                    DataOutputStream sout= new DataOutputStream(
                        serverClient.getOutputStream()
                    );
                    sout.writeBytes(proprietario + '\n');
                }
                serverClient.close();
            } 
            catch (IOException e) 
            {
                System.out.println("Error = " + e.getMessage());
            }
        }
    }
    
    public static void main(String[] args) throws IOException 
    {
        HashMap<String, String> storage = new HashMap<>();
        
        int p = 0;
	System.err.println("SERVER...");
        
        if (args.length < 1) 
        {
            System.err.println("Missing parameter: port number");	
            System.exit(1);
        }
        
        try 
        {
            p= Integer.parseInt( args[0] );
                
            VeiculosServer serv = new VeiculosServer(p);
                
            serv.servico(storage);
        }
        catch (NumberFormatException e) 
        {
                System.exit(2);
        }
    }
}