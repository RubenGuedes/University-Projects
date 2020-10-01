package Exame_Desportivo;

import Exame_Desportivo.Exame_Medico_Desportivo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Exame_Medico_DesportivoTest {
    @Test
    void setNumero_exame() {
        Exame_Medico_Desportivo exame = new Exame_Medico_Desportivo();
        exame.setNumero_exame(301);
        assertEquals(exame.getNumero_exame(), 301);
    }

    @Test
    void getNumero_exame() {
        Exame_Medico_Desportivo exame = new Exame_Medico_Desportivo();
        exame.setNumero_exame(301);
        assertEquals(exame.getNumero_exame(), 301);
    }

    @Test
    void setData() {
        Exame_Medico_Desportivo exame = new Exame_Medico_Desportivo();
        exame.setData("10-01-2018");
        assertEquals(exame.getData(), "10-01-2018");
    }

    @Test
    void getData() {
        Exame_Medico_Desportivo exame = new Exame_Medico_Desportivo();
        exame.setData("10-01-2018");
        assertEquals(exame.getData(), "10-01-2018");
    }

    @Test
    void setTipo_exame() {
        Exame_Medico_Desportivo exame = new Exame_Medico_Desportivo();
        exame.setTipo_exame("normal");
        assertEquals(exame.getTipo_exame(), "normal");
    }

    @Test
    void getTipo_exame() {
        Exame_Medico_Desportivo exame = new Exame_Medico_Desportivo();
        exame.setTipo_exame("normal");
        assertEquals(exame.getTipo_exame(), "normal");
    }
}