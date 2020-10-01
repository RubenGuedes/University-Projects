package Exame_Desportivo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AutenticacaoTest {

    @Test
    void setId() {
        Autenticacao autenticacao = new Autenticacao();
        autenticacao.setId("1023");
        assertEquals(autenticacao.getId(), "1023");
    }

    @Test
    void getId() {
        Autenticacao autenticacao = new Autenticacao();
        autenticacao.setId("1023");
        assertEquals(autenticacao.getId(), "1023");
    }

    @Test
    void setPassword() {
        Autenticacao autenticacao = new Autenticacao();
        autenticacao.setPassword("ab102");
        assertEquals(autenticacao.getPassword(), "ab102");
    }

    @Test
    void getPassword() {
        Autenticacao autenticacao = new Autenticacao();
        autenticacao.setPassword("ab102");
        assertEquals(autenticacao.getPassword(), "ab102");
    }

    @Test
    void setChip() {
        Autenticacao autenticacao = new Autenticacao();
        autenticacao.setChip("ab102");
        assertEquals(autenticacao.getChip(), "ab102");
    }

    @Test
    void getChip() {
        Autenticacao autenticacao = new Autenticacao();
        autenticacao.setChip("ab102");
        assertEquals(autenticacao.getChip(), "ab102");
    }

    @Test
    void setAutorizado() {
        Autenticacao autenticacao = new Autenticacao();
        autenticacao.setAutorizado(true);
        assertEquals(autenticacao.getAutorizado(), true);
    }

    @Test
    void getAutorizado() {
        Autenticacao autenticacao = new Autenticacao();
        autenticacao.setAutorizado(true);
        assertEquals(autenticacao.getAutorizado(), true);
    }
}