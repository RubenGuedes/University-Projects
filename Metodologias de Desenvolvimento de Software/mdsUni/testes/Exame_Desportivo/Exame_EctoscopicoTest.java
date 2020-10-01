package Exame_Desportivo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Exame_EctoscopicoTest {

    @Test
    void getDesenv_Normal() {
        Exame_Ectoscopico ee = new Exame_Ectoscopico();
        ee.setDesenv_Normal(true);
        assertTrue(ee.getDesenv_Normal());

    }

    @Test
    void setDesenv_Normal() {
        Exame_Ectoscopico ee = new Exame_Ectoscopico();
        ee.setDesenv_Normal(true);
        assertTrue(ee.getDesenv_Normal());
    }

    @Test
    void getAlter_derma() {
        Exame_Ectoscopico ee = new Exame_Ectoscopico();
        ee.setAlter_derma(true);
        assertTrue(ee.getAlter_derma());
    }

    @Test
    void setAlter_derma() {
        Exame_Ectoscopico ee = new Exame_Ectoscopico();
        ee.setAlter_derma(true);
        assertTrue(ee.getAlter_derma());
    }

    @Test
    void getEscoliose() {
        Exame_Ectoscopico ee = new Exame_Ectoscopico();
        ee.setEscoliose(true);
        assertTrue(ee.getEscoliose());
    }

    @Test
    void setEscoliose() {
        Exame_Ectoscopico ee = new Exame_Ectoscopico();
        ee.setEscoliose(true);
        assertTrue(ee.getEscoliose());
    }

    @Test
    void getDismetria_membros() {
        Exame_Ectoscopico ee = new Exame_Ectoscopico();
        ee.setDismetria_membros(true);
        assertTrue(ee.getDismetria_membros());
    }

    @Test
    void setDismetria_membros() {
        Exame_Ectoscopico ee = new Exame_Ectoscopico();
        ee.setDismetria_membros(true);
        assertTrue(ee.getDismetria_membros());
    }

    @Test
    void getGenus_valgus() {
        Exame_Ectoscopico ee = new Exame_Ectoscopico();
        ee.setGenus_valgus(true);
        assertTrue(ee.getGenus_valgus());
    }

    @Test
    void setGenus_valgus() {
        Exame_Ectoscopico ee = new Exame_Ectoscopico();
        ee.setGenus_valgus(true);
        assertTrue(ee.getGenus_valgus());
    }

    @Test
    void getPe_plano() {
        Exame_Ectoscopico ee = new Exame_Ectoscopico();
        ee.setPe_plano(true);
        assertTrue(ee.getPe_plano());
    }

    @Test
    void setPe_plano() {
        Exame_Ectoscopico ee = new Exame_Ectoscopico();
        ee.setPe_plano(true);
        assertTrue(ee.getPe_plano());
    }

    @Test
    void getVarizes() {
        Exame_Ectoscopico ee = new Exame_Ectoscopico();
        ee.setVarizes(true);
        assertTrue(ee.getVarizes());
    }

    @Test
    void setVarizes() {
        Exame_Ectoscopico ee = new Exame_Ectoscopico();
        ee.setVarizes(true);
        assertTrue(ee.getVarizes());
    }

    @Test
    void getOutros() {
        Exame_Ectoscopico ee = new Exame_Ectoscopico();
        ee.setOutros(true);
        assertTrue(ee.getOutros());
    }

    @Test
    void setOutros() {
        Exame_Ectoscopico ee = new Exame_Ectoscopico();
        ee.setOutros(true);
        assertTrue(ee.getOutros());
    }
}