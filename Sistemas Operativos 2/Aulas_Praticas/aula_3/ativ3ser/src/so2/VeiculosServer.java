package so2;

import java.io.*;
import java.net.*;
import java.util.Vector;

public class VeiculosServer extends Thread
{
    private static final String FILENAME = "/home/rp/Desktop/University/SO2/Aulas_Praticas/aula_3/ativ3ser/src/so2/repositorio";
    
    private int serverPort;	
    
    private Vector<Registo> repositorio;
    
    public VeiculosServer(int p) 
    {
	serverPort= p;
	repositorio= new Vector<>(); 
    }
    
    public static void main(String[] args)
    {
	System.err.println("SERVER...");
	if (args.length<1) 
        {
		System.err.println("Missing parameter: port number");	
		System.exit(1);
	}
	int p=0;
	try 
        {
	    p= Integer.parseInt( args[0] );
	}
	catch (Exception e) 
        {
		e.printStackTrace();
		System.exit(2);
	}
        
	VeiculosServer serv= new VeiculosServer(p);
        
        // activa o servico com threads
        serv.start();   
    }

    public static void addToFile(Registo registo) 
            throws FileNotFoundException, IOException
    {
        String matricula = registo.getMatricula();
        
        FileOutputStream fos = new FileOutputStream(FILENAME);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        
        oos.writeObject(matricula);
        oos.writeObject(registo);
        
        oos.close();
    }
    
    public static Registo findInFile(String matricula) 
            throws FileNotFoundException, IOException, ClassNotFoundException
    {
        FileInputStream fis = new FileInputStream(FILENAME);
        
        // Se if has something in file
        try (ObjectInputStream ois = new ObjectInputStream(fis)) 
        {
            int available = ois.available();
            while ( available == 0 ) 
            {                
                Object obj = ois.readObject();
                String mat = (String) obj;
                
                // Se encontrou a matricula
                if (mat.compareTo(matricula) == 0)
                {
                    Registo result = (Registo) ois.readObject();
                    ois.close();
                    return result;
                }
                
                // Ignorar o objecto 'Registo' no ficheiro
                ois.readObject();
            }
            ois.close();
            return null;
        }
        catch (EOFException eOFException)
        {
            return null;
        }
    }
    
    public void run() 
    {
	try 
        {
	    // inicializa o socket para receber ligacoes
            ServerSocket socketServer = new ServerSocket(serverPort);
	    
            while (true) 
            {
		// espera uma ligacao
                Socket socket = socketServer.accept();
		
		try 
                {
		    Object objPedido = null;
		    
                    // le os dados do pedido, como um OBJECTO "objPedido"		
                    ObjectInputStream ois = new ObjectInputStream (
                            socket.getInputStream()
                    );
                    objPedido = ois.readObject();
		    
		    // apreciar o objecto com o pedido recebido:
		    if (objPedido == null)
			System.err.println("PEDIDO NULL: esqueceu-se de alguma coisa");
		    
		    if (objPedido instanceof PedidoDeConsulta) 
                    {
                        // Cast
			PedidoDeConsulta pc = (PedidoDeConsulta) objPedido;
			
			// procurar o registo associado a matricula pretendida
                        Registo registo = findInFile(pc.getMatricula());
                        
                        // Se encontra devolve o registo, se não, 
                        // devolve um novo objecto ou string a representar que nao encontrou
                        if ( registo == null ) 
                        {
                            // enviar objecto Cliente via socket		    
                            ObjectOutputStream oos = new ObjectOutputStream (
                                    socket.getOutputStream()
                            );
                            oos.writeObject("Matricula não encontrada");
                        }
                        else
                        {                            
                            // enviar objecto Cliente via socket		    
                            ObjectOutputStream oos = new ObjectOutputStream (
                                    socket.getOutputStream()
                            );
                            oos.writeObject(registo);
                        }
		    }
		    else if (objPedido instanceof PedidoDeRegisto) 
                    {
                        // Cast
			PedidoDeRegisto pr = (PedidoDeRegisto) objPedido;
                        
			// ver se ja existia registo desta matricula
                        Registo registo = findInFile(pr.getRegisto().getMatricula());
                        
			// responder ao cliente
                        if ( registo == null )
                        {
                            // adicionar ao rep local (se nao e' uma repeticao)
                            addToFile(pr.getRegisto());
                            
                            // Informação adicionada no repositorio
                            // enviar objecto Cliente via socket		    
                            ObjectOutputStream oos = new ObjectOutputStream (
                                    socket.getOutputStream()
                            );
                            oos.writeObject("Informação adicionada no repositorio");
                        }
                        else
                        {
                            // Informação não adicionada
                            ObjectOutputStream oos = new ObjectOutputStream (
                                    socket.getOutputStream()
                            );
                            oos.writeObject("Informação já se encontrava no servidor");
                        }
		    }
		    else
			System.out.println("PROBLEMA");
                }
                catch (Exception exNoAtendimento) 
                {
                    exNoAtendimento.printStackTrace();
                }
                // fechar o socket de dados
                finally 
                {  
                    // fechar o socket de dados dedicado a este cliente:
                    try 
                    {
                        socket.close();
                    }
                    catch (Exception e002) 
                    {
                        e002.printStackTrace();
                    }
                }
	    }  // ... ciclo de atendimento
	}
	catch (Exception problemaBindAccept) 
        {
	    problemaBindAccept.printStackTrace();
	}
    }
}