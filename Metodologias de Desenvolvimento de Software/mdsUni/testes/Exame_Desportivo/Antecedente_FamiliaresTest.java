package Exame_Desportivo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Antecedente_FamiliaresTest {

    @Test
    void getDoenca_cardio() {
        Antecedente_Familiares af = new Antecedente_Familiares();
        af.setDoenca_cardio(true);
        assertTrue(af.getDoenca_cardio());
    }

    @Test
    void setDoenca_cardio() {
        Antecedente_Familiares af = new Antecedente_Familiares();
        af.setDoenca_cardio(true);
        assertTrue(af.getDoenca_cardio());
    }

    @Test
    void getHipertensao_arterial() {
        Antecedente_Familiares af = new Antecedente_Familiares();
        af.setHipertensao_arterial(true);
        assertTrue(af.getHipertensao_arterial());
    }

    @Test
    void setHipertensao_arterial() {
        Antecedente_Familiares af = new Antecedente_Familiares();
        af.setHipertensao_arterial(true);
        assertTrue(af.getHipertensao_arterial());
    }

    @Test
    void getTraumatismo() {
        Antecedente_Familiares af = new Antecedente_Familiares();
        af.setTraumatismo(true);
        assertTrue(af.getTraumatismo());
    }

    @Test
    void setTraumatismo() {
        Antecedente_Familiares af = new Antecedente_Familiares();
        af.setTraumatismo(true);
        assertTrue(af.getTraumatismo());
    }

    @Test
    void getMorte_subita() {
        Antecedente_Familiares af = new Antecedente_Familiares();
        af.setMorte_subita(true);
        assertTrue(af.getMorte_subita());
    }

    @Test
    void setMorte_subita() {
        Antecedente_Familiares af = new Antecedente_Familiares();
        af.setMorte_subita(true);
        assertTrue(af.getMorte_subita());
    }

    @Test
    void getAsma() {
        Antecedente_Familiares af = new Antecedente_Familiares();
        af.setAsma(true);
        assertTrue(af.getAsma());
    }

    @Test
    void setAsma() {
        Antecedente_Familiares af = new Antecedente_Familiares();
        af.setAsma(true);
        assertTrue(af.getAsma());
    }

    @Test
    void getDiabetes() {
        Antecedente_Familiares af = new Antecedente_Familiares();
        af.setDiabetes(true);
        assertTrue(af.getDiabetes());
    }

    @Test
    void setDiabetes() {
        Antecedente_Familiares af = new Antecedente_Familiares();
        af.setDiabetes(true);
        assertTrue(af.getDiabetes());
    }

    @Test
    void getEpilepsia() {
        Antecedente_Familiares af = new Antecedente_Familiares();
        af.setEpilepsia(true);
        assertTrue(af.getEpilepsia());

    }

    @Test
    void setEpilepsia() {
        Antecedente_Familiares af = new Antecedente_Familiares();
        af.setEpilepsia(true);
        assertTrue(af.getEpilepsia());
    }

    @Test
    void getTumores() {
        Antecedente_Familiares af = new Antecedente_Familiares();
        af.setTumores(true);
        assertTrue(af.getTumores());
    }

    @Test
    void setTumores() {
        Antecedente_Familiares af = new Antecedente_Familiares();
        af.setTumores(true);
        assertTrue(af.getTumores());
    }

    @Test
    void getDoencas_hematologicas() {
        Antecedente_Familiares af = new Antecedente_Familiares();
        af.setDoencas_hematologicas(true);
        assertTrue(af.getDoencas_hematologicas());
    }

    @Test
    void setDoencas_hematologicas() {
        Antecedente_Familiares af = new Antecedente_Familiares();
        af.setDoencas_hematologicas(true);
        assertTrue(af.getDoencas_hematologicas());
    }

    @Test
    void getOutros() {
        Antecedente_Familiares af = new Antecedente_Familiares();
        af.setOutros(true);
        assertTrue(af.getOutros());
    }

    @Test
    void setOutros() {
        Antecedente_Familiares af = new Antecedente_Familiares();
        af.setOutros(true);
        assertTrue(af.getOutros());
    }
}