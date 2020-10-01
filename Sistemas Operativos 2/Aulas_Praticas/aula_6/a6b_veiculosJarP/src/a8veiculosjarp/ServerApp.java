package a8veiculosjarp;
import so2.VeiculosServer;

/**
 *
 * @author jsaias
 */
public class ServerApp 
{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        // inicialize o Servidor Aqui... pode por exemplo chamar o metodo main() da classe do servidor
        VeiculosServer.main(args);
    }
    /**
     * 
     * Para executar:
     *  java -cp build/classesrc/veiculos3-semAtend.jar:src/so2-objfile.jar a8veiculosjarp.ServerApp 9000
     */
}
