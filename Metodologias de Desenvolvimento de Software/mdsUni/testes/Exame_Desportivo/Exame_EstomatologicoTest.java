package Exame_Desportivo;
import Exame_Desportivo.Exame_Estomatologico;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Exame_EstomatologicoTest {

    @Test
    void setSem_carie() {
        Exame_Estomatologico exame = new Exame_Estomatologico();
        exame.setSem_carie(true);
        assertEquals(exame.getSem_carie(), true);
    }

    @Test
    void getSem_carie() {
        Exame_Estomatologico exame = new Exame_Estomatologico();
        exame.setSem_carie(true);
        assertEquals(exame.getSem_carie(), true);
    }

    @Test
    void setCaries_nao_tratadas() {
        Exame_Estomatologico exame = new Exame_Estomatologico();
        exame.setCaries_nao_tratadas(true);
        assertEquals(exame.getCaries_nao_tratadas(), true);
    }

    @Test
    void getCaries_nao_tratadas() {
        Exame_Estomatologico exame = new Exame_Estomatologico();
        exame.setCaries_nao_tratadas(true);
        assertEquals(exame.getCaries_nao_tratadas(), true);
    }
}