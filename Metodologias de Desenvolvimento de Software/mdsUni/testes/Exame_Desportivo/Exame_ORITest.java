package Exame_Desportivo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Exame_ORITest {

    @Test
    void getAudicao_5m_sem_alteracoes() {
        Exame_ORI eori = new Exame_ORI();
        eori.setAudicao_5m_sem_alteracoes(true);
        assertTrue(eori.getAudicao_5m_sem_alteracoes());
    }

    @Test
    void setAudicao_5m_sem_alteracoes() {
        Exame_ORI eori = new Exame_ORI();
        eori.setAudicao_5m_sem_alteracoes(true);
        assertTrue(eori.getAudicao_5m_sem_alteracoes());

    }

    @Test
    void getSinusite() {
        Exame_ORI eori = new Exame_ORI();
        eori.setSinusite(true);
        assertTrue(eori.getSinusite());
    }

    @Test
    void setSinusite() {
        Exame_ORI eori = new Exame_ORI();
        eori.setSinusite(true);
        assertTrue(eori.getSinusite());
    }
}