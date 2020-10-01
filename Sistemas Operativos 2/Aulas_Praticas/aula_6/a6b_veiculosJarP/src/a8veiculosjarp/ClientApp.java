package a8veiculosjarp;

import so2.VeiculosClient;
/**
 *
 * @author jsaias
 */
public class ClientApp 
{
    public static void main(String[] args) 
    {
        // inicialize o Cliente Aqui... pode por exemplo chamar o metodo main() da classe do servidor
        VeiculosClient.main(args);
    }
    /**
     * 
     * Para executar:
     *  java -cp build/classrc/veiculos3-semAtend.jar:src/so2-objfile.jar a8veiculosjarp.ClientApp localhost 9000
     */
}
