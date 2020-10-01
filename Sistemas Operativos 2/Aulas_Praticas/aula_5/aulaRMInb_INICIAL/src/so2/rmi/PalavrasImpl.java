package so2.rmi;

/**
 * CLASSE DO OBJECTO REMOTO Tem a parte funcional... a implementação das
 * operações do serviço.
 */
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;
import java.util.Vector;

public class PalavrasImpl extends UnicastRemoteObject implements Palavras, Serializable 
{
    // deve existir um construtor
    public PalavrasImpl() throws RemoteException 
    {
        super();
    }

    // ... e a implementacao de
    // cada metodo declarado na interface remota

    /**
     * devolve a primeira palavra da frase recebida
     *
     * @param s
     * @return
     * @throws java.rmi.RemoteException
     */
    @Override
    public String primeiraPalavra(String s) throws RemoteException
    {
        System.err.println("invocacao primeiraPalavra() com: " + s);
        String s2 = s.trim();  // remove espacos no inicio ou fim
        
        int k = s2.indexOf(" ");  // indice de " "
        if (k < 0) // nao tem espacos
            return s2;
        else
            return s2.substring(0, k);  // apenas a 1a palavra
    }

    /**
     * devolve um vector com cada palavra da expressao recebida
     *
     * @param s
     * @return
     * @throws java.rmi.RemoteException
     */
    @Override
    public Vector<String> divide(String s) throws RemoteException 
    {
        System.err.println("invocacao divide() com: " + s);
        java.util.Vector<String> v = new Vector<String>();
        String s2 = s.trim();

        String[] s2pals = s2.split(" ");
        v.addAll(Arrays.asList(s2pals)); // adiciona a palavra i

        return v;
    }
}
