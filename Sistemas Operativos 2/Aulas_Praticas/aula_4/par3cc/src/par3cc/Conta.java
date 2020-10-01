package par3cc;

import java.util.concurrent.atomic.AtomicReference;

public class Conta {

    // private double saldo;
    private AtomicReference<Double> saldo;
    
    // construtores
    public Conta() {
        // saldo = 0;
        Double init = new Double(0);
        saldo = new AtomicReference<>(init);
    }

    public Conta(Double saldoInicial) {
        saldo = new AtomicReference<>(saldoInicial);
    }

    public Double getSaldo() {
        return saldo.get();
    }

    // Produtor
    public void credito(Double valor) 
    {
        System.out.println(" ++credito pedido " + Thread.currentThread().getName() + ", saldo incial=" + saldo);

        Double saldoTmp = valor + saldo;
        
	// simular uma demora
        try { Thread.sleep(1000); } 
        catch (InterruptedException e) {}

        //saldo = saldoTmp;
        saldo.set(saldoTmp);
        
        System.out.println("\t ++credito realizado " + Thread.currentThread().getName() + " saldo= " + saldo);
    }

    // Consumir
    public void debito(Double valor) throws Exception 
    {
        System.out.println(" ++ debito pedido " + Thread.currentThread().getName() + ", saldo incial=" + saldo);
        boolean espera = false;

        Double saldoTmp = saldo - valor;
        
	// simular uma demora
        try { Thread.sleep(1000);} 
        catch ( InterruptedException e) {}

        // saldo = saldoTmp;
        saldo.set(saldoTmp);
        
        System.out.println("\t ++ debito realizado " + Thread.currentThread().getName() + " saldo= " + saldo);
    }
}
