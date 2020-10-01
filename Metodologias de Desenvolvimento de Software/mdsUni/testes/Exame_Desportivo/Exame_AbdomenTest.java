package Exame_Desportivo;
import Exame_Desportivo.Exame_Abdomen;

import static org.junit.jupiter.api.Assertions.*;

class Exame_AbdomenTest {

    @org.junit.jupiter.api.Test
    void getOrganomegalia() {
        Exame_Abdomen exame_abdomen = new Exame_Abdomen(true,"doenca");
        assertEquals(exame_abdomen.getOrganomegalia(), true);
    }

    @org.junit.jupiter.api.Test
    void setOrganomegalia() {
        Exame_Abdomen exame_abdomen = new Exame_Abdomen(true,"doenca");
        assertEquals(exame_abdomen.getOrganomegalia(), true);
    }

    @org.junit.jupiter.api.Test
    void getOutros() {
        Exame_Abdomen exame_abdomen = new Exame_Abdomen(true,"doenca");
        assertEquals(exame_abdomen.getOutros(), "doenca");
    }

    @org.junit.jupiter.api.Test
    void setOutros() {
        Exame_Abdomen exame_abdomen = new Exame_Abdomen(true,"doenca");
        assertEquals(exame_abdomen.getOutros(), "doenca");
    }
}