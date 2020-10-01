/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

/**
 *
 * @author rp
 */
public class Cliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        NewJerseyClient client= new NewJerseyClient();
        String resp= client.getTurma_JSON(String.class);
        System.err.println("EXEMPLO: "+resp);
    }
    
}
