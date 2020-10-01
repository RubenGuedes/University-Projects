package Exame_Desportivo;

import Exame_Desportivo.Exame_Oftalmologico;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Exame_OftalmologicoTest {

    @Test
    void setAcuidade_vis_sem_correcao() {
        Exame_Oftalmologico exame = new Exame_Oftalmologico();
        exame.setAcuidade_vis_sem_correcao(0);
        assertEquals(exame.getAcuidade_vis_sem_correcao(), 0);
    }

    @Test
    void getAcuidade_vis_sem_correcao() {
        Exame_Oftalmologico exame = new Exame_Oftalmologico();
        exame.setAcuidade_vis_sem_correcao(0);
        assertEquals(exame.getAcuidade_vis_sem_correcao(), 0);
    }

    @Test
    void setAcuidade_vis_com_correcao() {
        Exame_Oftalmologico exame = new Exame_Oftalmologico();
        exame.setAcuidade_vis_sem_correcao(0);
        assertEquals(exame.getAcuidade_vis_sem_correcao(), 0);
    }

    @Test
    void getAcuidade_vis_com_correcao() {
        Exame_Oftalmologico exame = new Exame_Oftalmologico();
        exame.setAcuidade_vis_com_correcao(0);
        assertEquals(exame.getAcuidade_vis_com_correcao(), 0);
    }

    @Test
    void setMiopia() {
        Exame_Oftalmologico exame = new Exame_Oftalmologico();
        exame.setMiopia(0.5f);
        assertEquals(exame.getMiopia(), 0.5f);
    }

    @Test
    void getMiopia() {
        Exame_Oftalmologico exame = new Exame_Oftalmologico();
        exame.setMiopia(0.9f);
        assertEquals(exame.getMiopia(), 0.9f);
    }

    @Test
    void setOutros() {
        Exame_Oftalmologico exame = new Exame_Oftalmologico();
        exame.setOutros(0.9f);
        assertEquals(exame.getOutros(), 0.9f);
    }

    @Test
    void getOutros() {
        Exame_Oftalmologico exame = new Exame_Oftalmologico();
        exame.setOutros(0.9f);
        assertEquals(exame.getOutros(), 0.9f);
    }
}