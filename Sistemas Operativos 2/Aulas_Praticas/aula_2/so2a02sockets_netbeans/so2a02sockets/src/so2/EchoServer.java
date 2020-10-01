package so2;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer 
{
    private final int serverPort;
    
    public EchoServer(int p) throws IOException 
    {
	serverPort= p;
    }
    
    // Activa o servidor no porto indicado em "serverPort"
    public void servico() throws IOException 
    {
        ServerSocket server = new ServerSocket(serverPort);
        
        // Exercicio 2: inicializar um socket para aceitar ligacoes...
        Socket socket_funct;
        
        try 
        {
            socket_funct = server.accept();
            
            // receive message from client socket
            BufferedReader breader = new BufferedReader (
                new InputStreamReader(
                    socket_funct.getInputStream()
                )
            );
            String msg = breader.readLine();
            msg = "so2_" + msg + "_2os\n";
            
            // retrieve message to client socket
            DataOutputStream sout= new DataOutputStream (
                socket_funct.getOutputStream()
            );
            sout.writeChars(msg);
            socket_funct.close();
        }
        catch(IOException e)
        {
            System.out.println("Error! = " + e.toString());    
        }
    }
    
    public static void main(String[] args) throws IOException
    {               
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
	}
	catch (Exception e) 
        {
	    e.printStackTrace();
	    System.exit(2);
	}
	EchoServer serv = new EchoServer(p);
        // activa o servico
	serv.servico(); 
    }
}