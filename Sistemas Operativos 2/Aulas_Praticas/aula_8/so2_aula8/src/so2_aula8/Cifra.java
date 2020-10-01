/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so2_aula8;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.Security;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;


/**
 *
 * @author rp
 */
public class Cifra 
{
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException 
    {
        Provider[] providers = Security.getProviders();
        
        /**
         * Exercício 1
         */
//        for (Provider provider : providers)
//            System.out.println(
//                    String.format(
//                            "Nome = %s; Versão = %s; Info = %s",
//                            provider.getName(),
//                            provider.getVersion(),
//                            provider.getInfo()
//                    )
//            );

        /**
         * Exercício 2
         */
//        for (Provider.Service service : providers[0].getServices())
//            System.out.println(
//                    String.format(
//                            "Serviço = %s; Tipo = %s; ToString = %s",
//                            service.getAlgorithm(),
//                            service.getType(),
//                            service.toString()
//                            
//                    )
//            );

//        for(String algo: Security.getAlgorithms("Cipher"))
//            System.out.println(
//                    String.format("Algo = %s", 
//                        algo
//                    )
//            );

        /**
         * Exer
         */
        KeyGenerator generator = KeyGenerator.getInstance("AES");
        System.out.println(String.format("%s",generator.getAlgorithm()));
        
        SecretKey secretKey = generator.generateKey();
        System.out.println(
                String.format("%s", secretKey.toString())
        );
        
        /**
         * Exercício 5
         */
        Cipher cipher;
        cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        /* para vários blocos usar-se-ia o método update() (multiple-part encryption)
            até ao penúltimo bloco, seguido de doFinal()
        */
        String MSG = "Teste 123";
        byte[] plaintext = MSG.getBytes();
        byte[] ciphertext = cipher.doFinal(plaintext);  // cifrar num "bloco" só

        // teste rápido para ver o q fica, que não será visível:
        System.out.println( new String(ciphertext) );
        
        // Desencriptar
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] test2 = cipher.doFinal(ciphertext); // Obrigatório
        System.out.println(new String(test2));
    }
}
