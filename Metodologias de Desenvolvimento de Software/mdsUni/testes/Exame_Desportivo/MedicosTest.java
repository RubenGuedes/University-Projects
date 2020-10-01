package Exame_Desportivo;

import Exame_Desportivo.Medicos;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MedicosTest {

    @Test
    void setEspecialidade() {
        Medicos medicos = new Medicos();
        medicos.setEspecialidade("Enfermeiro");
        assertEquals(medicos.getEspecialidade(), "Enfermeiro");
    }

    @Test
    void getEspecialidade() {
        Medicos medicos = new Medicos();
        medicos.setEspecialidade("Enfermeiro");
        assertEquals(medicos.getEspecialidade(), "Enfermeiro");
    }
}