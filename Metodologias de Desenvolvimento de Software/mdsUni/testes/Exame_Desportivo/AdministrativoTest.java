package Exame_Desportivo;
import Exame_Desportivo.Administrativo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdministrativoTest {

    @Test
    void setId_especial() {
        Administrativo admin = new Administrativo();
        admin.setId_especial("admin201");
        assertEquals(admin.getId_especial(), "admin201");
    }

    @Test
    void getId_especial() {
        Administrativo admin = new Administrativo();
        admin.setId_especial("admin201");
        assertEquals(admin.getId_especial(), "admin201");
    }
}