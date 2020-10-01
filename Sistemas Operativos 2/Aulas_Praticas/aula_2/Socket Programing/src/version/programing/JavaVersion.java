/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package version.programing;

/**
 *
 * @author rp
 */
public class JavaVersion {
    public static void main(String[] args) 
    {
        System.out.println("System java version: "+System.getProperty("java.version"));
        System.out.println("System java vendor: "+System.getProperty("java.vendor"));
        System.out.println("System classpath: "+System.getProperty("java.class.path"));
    }
}
