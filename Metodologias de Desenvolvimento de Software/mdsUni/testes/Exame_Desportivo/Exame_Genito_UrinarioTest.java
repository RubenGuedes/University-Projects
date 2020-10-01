package Exame_Desportivo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Exame_Genito_UrinarioTest {

    @Test
    void getMenarca_idade() {
        Exame_Genito_Urinario egu = new Exame_Genito_Urinario();
        egu.setMenarca_idade(9);
        assertEquals(egu.getMenarca_idade(),9);
    }


    @Test
    void setMenarca_idade() {
        Exame_Genito_Urinario egu = new Exame_Genito_Urinario();
        egu.setMenarca_idade(9);
        assertEquals(egu.getMenarca_idade(),9);
    }

    @Test
    void getAlt_ciclo_mentrual() {
        Exame_Genito_Urinario egu = new Exame_Genito_Urinario();
        egu.setAlt_ciclo_mentrual(true);
        assertTrue(egu.getAlt_ciclo_mentrual());
    }

    @Test
    void setAlt_ciclo_mentrual() {
        Exame_Genito_Urinario egu = new Exame_Genito_Urinario();
        egu.setAlt_ciclo_mentrual(true);
        assertTrue(egu.getAlt_ciclo_mentrual());
    }

    @Test
    void getOutros() {
        Exame_Genito_Urinario egu = new Exame_Genito_Urinario();
        egu.setOutros(true);
        assertTrue(egu.getOutros());
    }

    @Test
    void setOutros() {
        Exame_Genito_Urinario egu = new Exame_Genito_Urinario();
        egu.setOutros(true);
        assertTrue(egu.getOutros());
    }
}