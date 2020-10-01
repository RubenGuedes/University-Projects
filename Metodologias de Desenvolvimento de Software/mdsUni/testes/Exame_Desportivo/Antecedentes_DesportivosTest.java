package Exame_Desportivo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Antecedentes_DesportivosTest {

    @org.junit.jupiter.api.Test
    void getDesporto_federado() {
        Antecedentes_Desportivos ad  = new Antecedentes_Desportivos();
        ad.setDesporto_federado(true);
        assertTrue(ad.getDesporto_federado());
    }

    @org.junit.jupiter.api.Test
    void setDesporto_federado() {
        Antecedentes_Desportivos ad = new Antecedentes_Desportivos();
        ad.setDesporto_federado(true);
        assertTrue(ad.getDesporto_federado());

    }

    @org.junit.jupiter.api.Test
    void getRetomara_atividade_fis() {
        Antecedentes_Desportivos ad  =  new Antecedentes_Desportivos();
        ad.setRetomara_atividade_fis(true);
        assertTrue(ad.getRetomara_atividade_fis());

    }

    @org.junit.jupiter.api.Test
    void setRetomara_atividade_fis() {
        Antecedentes_Desportivos ad = new Antecedentes_Desportivos();
        ad.setRetomara_atividade_fis(true);
        assertTrue(ad.getRetomara_atividade_fis());

    }


    @org.junit.jupiter.api.Test
    void getDesporto_regular() {
        Antecedentes_Desportivos ad = new Antecedentes_Desportivos();
        ad.setDesporto_regular(true);
        assertTrue(ad.getDesporto_regular());
    }



    @org.junit.jupiter.api.Test
    void setDesporto_regular() {
        Antecedentes_Desportivos ad = new Antecedentes_Desportivos();
        ad.setDesporto_regular(true);
        assertTrue(ad.getDesporto_regular());
    }

    @org.junit.jupiter.api.Test
    void getTreinos_semanais() {
        Antecedentes_Desportivos ad = new Antecedentes_Desportivos();
        ad.setTreinos_semanais("true");
        assertEquals(ad.getTreinos_semanais(), "true");
    }

    @org.junit.jupiter.api.Test
    void setTreinos_semanais() {
            Antecedentes_Desportivos ad = new Antecedentes_Desportivos();
            ad.setTreinos_semanais("true");
            assertEquals(ad.getTreinos_semanais(), "true");
    }
}