package Exame_Desportivo;

import Exame_Desportivo.Centro_Medicina_Desportiva;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Centro_Medicina_DesportivaTest {

    @Test
    void setLocal() {
        Centro_Medicina_Desportiva centro = new Centro_Medicina_Desportiva();
        centro.setLocal("Lisboa");
        assertEquals(centro.getLocal(), "Lisboa");
    }

    @Test
    void getLocal() {
        Centro_Medicina_Desportiva centro = new Centro_Medicina_Desportiva();
        centro.setLocal("Lisboa");
        assertEquals(centro.getLocal(), "Lisboa");
    }
}