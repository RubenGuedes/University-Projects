package Exame_Desportivo;
import Exame_Desportivo.Exame_CardioCirculRespi;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Exame_CardioCirculRespiTest {

    @Test
    void setPulso_radial() {
        Exame_CardioCirculRespi exame = new Exame_CardioCirculRespi();
        exame.setPulso_radial(true);
        assertEquals(exame.getPulso_radial(), true);
    }

    @Test
    void getPulso_radial() {
        Exame_CardioCirculRespi exame = new Exame_CardioCirculRespi();
        exame.setPulso_radial(true);
        assertEquals(exame.getPulso_radial(), true);
    }

    @Test
    void setPulso_femural() {
        Exame_CardioCirculRespi exame = new Exame_CardioCirculRespi();
        exame.setPulso_femural(true);
        assertEquals(exame.getPulso_femural(), true);
    }

    @Test
    void getPulso_femural() {
        Exame_CardioCirculRespi exame = new Exame_CardioCirculRespi();
        exame.setPulso_femural(true);
        assertEquals(exame.getPulso_femural(), true);
    }

    @Test
    void setAusc_cardiaca_normal() {
        Exame_CardioCirculRespi exame = new Exame_CardioCirculRespi();
        exame.setAusc_cardiaca_normal(true);
        assertEquals(exame.getAusc_cardiaca_normal(), true);
    }

    @Test
    void getAusc_cardiaca_normal() {
        Exame_CardioCirculRespi exame = new Exame_CardioCirculRespi();
        exame.setAusc_cardiaca_normal(true);
        assertEquals(exame.getAusc_cardiaca_normal(), true);
    }

    @Test
    void setAusc_pulm_normal() {
        Exame_CardioCirculRespi exame = new Exame_CardioCirculRespi();
        exame.setAusc_pulm_normal(true);
        assertEquals(exame.getAusc_pulm_normal(), true);
    }

    @Test
    void getAusc_pulm_normal() {
        Exame_CardioCirculRespi exame = new Exame_CardioCirculRespi();
        exame.setAusc_pulm_normal(true);
        assertEquals(exame.getAusc_pulm_normal(), true);
    }

    @Test
    void setFreq_cardiaca() {
        Exame_CardioCirculRespi exame = new Exame_CardioCirculRespi();
        exame.setFreq_cardiaca(70);
        assertEquals(exame.getFreq_cardiaca(), 70);
    }

    @Test
    void getFreq_cardiaca() {
        Exame_CardioCirculRespi exame = new Exame_CardioCirculRespi();
        exame.setFreq_cardiaca(70);
        assertEquals(exame.getFreq_cardiaca(), 70);
    }

    @Test
    void setP_arterial() {
        Exame_CardioCirculRespi exame = new Exame_CardioCirculRespi();
        exame.setP_arterial(20);
        assertEquals(exame.getP_arterial(), 20);
    }

    @Test
    void getP_arterial() {
        Exame_CardioCirculRespi exame = new Exame_CardioCirculRespi();
        exame.setP_arterial(20);
        assertEquals(exame.getP_arterial(), 20);
    }
}