package so2;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.io.InputStreamReader;

public class EchoClient 
{
    private String address= null;
    private int sPort= 0;
    
    public EchoClient(String add, int p) 
    {
	address = add;
	sPort = p;
    }
    
    public static void main(String[] args)
    {
	// exigir os argumentos necessarios para 3 argumentos
	if (args.length < 3) 
        {
	    System.err.println("Argumentos insuficientes:  java EchoClient ADDRESS PORT");
	    System.exit(1);
	}
	
	try 
        {
	    String addr= args[0];
	    int p= Integer.parseInt(args[1]);	
	    
	    EchoClient cl= new EchoClient(addr,p);
	    
	    // Mensagem escrita no argumento
            String msg = args[2];
	    cl.go( msg );   // faz o pretendido
	}
	catch (Exception e) 
        {
	    System.out.println("Problema no formato dos argumentos");
	    e.printStackTrace();
	}
    }
    
    public void go(String msg) throws IOException
    {	
	// Exercicio 1: mostrar a mensagem que vai ser enviada
        System.out.println("Print cliente = " + msg);
	
        try 
        {
            // Cria o socket cliente
            Socket s = new Socket(address, sPort);
            
            // Mensagem a enviar
            DataOutputStream sout = new DataOutputStream (
                s.getOutputStream()
            );
            sout.writeChars(msg + '\n');
        
            // Resposta do servidor
            BufferedReader breader = new BufferedReader (
                new InputStreamReader(
                        s.getInputStream()
                )
            );
            String resp = breader.readLine();
            
            s.close();
            /*byte[] b = new byte[256];
            int lidos = socketIn.read(b, 0, 256);
            String resp = new String(b, 0, lidos);*/
        
            // Print da resposta
            System.out.println("Resposta = " + resp);
        }
        catch (IOException e)
        {
            System.out.println(e.toString());
        }
    }
}
