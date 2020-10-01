package Exame_Desportivo;

import Exame_Desportivo.Exames_Complementares_Diagnostico;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Exames_Complementares_DiagnosticoTest {

    @Test
    void setEcg_normal()
    {
        Exames_Complementares_Diagnostico exames_complementares = new Exames_Complementares_Diagnostico();
        exames_complementares.setEcg_normal(true);
        assertEquals(exames_complementares.getEcg_normal(), true);
    }

    @Test
    void getEcg_normal() {
        Exames_Complementares_Diagnostico exames_complementares = new Exames_Complementares_Diagnostico();
        exames_complementares.setEcg_normal(true);
        assertEquals(exames_complementares.getEcg_normal(), true);
    }

    @Test
    void setRad_torax_normal()
    {
        Exames_Complementares_Diagnostico exames_complementares = new Exames_Complementares_Diagnostico();
        exames_complementares.setRad_torax_normal(true);
        assertEquals(exames_complementares.getRad_torax_normal(), true);
    }

    @Test
    void getRad_torax_normal() {
        Exames_Complementares_Diagnostico exames_complementares = new Exames_Complementares_Diagnostico();
        exames_complementares.setRad_torax_normal(true);
        assertEquals(exames_complementares.getRad_torax_normal(), true);
    }

    @Test
    void setOutros() {
        Exames_Complementares_Diagnostico exames_complementares = new Exames_Complementares_Diagnostico();
        exames_complementares.setOutros("doenca x");
        assertEquals(exames_complementares.getOutros(), "doenca x");
    }

    @Test
    void getOutros() {
        Exames_Complementares_Diagnostico exames_complementares = new Exames_Complementares_Diagnostico();
        exames_complementares.setOutros("doenca x");
        assertEquals(exames_complementares.getOutros(), "doenca x");
    }
}