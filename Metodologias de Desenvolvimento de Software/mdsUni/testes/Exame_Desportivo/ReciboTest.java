package Exame_Desportivo;

import Exame_Desportivo.Recibo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReciboTest {

    @Test
    void setNumero_recibo() {
        Recibo recibo = new Recibo();
        recibo.setNumero_recibo(102000);
        assertEquals(recibo.getNumero_recibo(), 102000);
    }

    @Test
    void getNumero_recibo() {
        Recibo recibo = new Recibo();
        recibo.setNumero_recibo(102000);
        assertEquals(recibo.getNumero_recibo(), 102000);
    }

    @Test
    void setNIF() {
        Recibo recibo = new Recibo();
        recibo.setNIF("a201");
        assertEquals(recibo.getNif(), "a201");
    }

    @Test
    void getNif() {
        Recibo recibo = new Recibo();
        recibo.setNIF("a201");
        assertEquals(recibo.getNif(), "a201");
    }
}