package Exame_Desportivo;

import Exame_Desportivo.Praticante_Desportivo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Praticante_DesportivoTest {

    @Test
    void setExame_n() {
        Praticante_Desportivo praticante = new Praticante_Desportivo();
        praticante.setExame_n(1002);
        assertEquals(praticante.getExame_n(),1002);
    }

    @Test
    void getExame_n() {
        Praticante_Desportivo praticante = new Praticante_Desportivo();
        praticante.setExame_n(1002);
        assertEquals(praticante.getExame_n(),1002);
    }
}