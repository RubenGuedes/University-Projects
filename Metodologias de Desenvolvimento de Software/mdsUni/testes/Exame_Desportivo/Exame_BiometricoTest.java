package Exame_Desportivo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Exame_BiometricoTest {

    @Test
    void getPeso() {
        Exame_Biometrico eb = new Exame_Biometrico();
        eb.setPeso(80);
        assertEquals(eb.getPeso(),80);
    }


    @Test
    void setPeso() {
        Exame_Biometrico eb = new Exame_Biometrico();
        eb.setPeso(80);
        assertEquals(eb.getPeso(),80);
    }


    @Test
    void getEstatura() {
        Exame_Biometrico eb = new Exame_Biometrico();
        eb.setEstatura(180);
        assertEquals(eb.getEstatura(),180);
    }

    @Test
    void setEstatura() {
        Exame_Biometrico eb = new Exame_Biometrico();
        eb.setEstatura(180);
        assertEquals(eb.getEstatura(),180);
    }
}