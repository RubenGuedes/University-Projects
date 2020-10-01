package Exame_Desportivo;

import Exame_Desportivo.Pessoa;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PessoaTest {

    @Test
    void setNome() {
        Pessoa pessoa = new Pessoa("ruben", 1520, 9753190);
        assertEquals(pessoa.getNome(), "ruben");
    }

    @Test
    void getNome() {
        Pessoa pessoa = new Pessoa("ruben", 1520, 9753190);
        assertEquals(pessoa.getNome(), "ruben");
    }

    @Test
    void setNif() {
        Pessoa pessoa = new Pessoa("ruben", 1520, 9753190);
        assertEquals(pessoa.getNif(), 1520);
    }

    @Test
    void getNif() {
        Pessoa pessoa = new Pessoa("ruben", 1520, 9753190);
        assertEquals(pessoa.getNif(), 1520);
    }

    @Test
    void setTelemovel() {
        Pessoa pessoa = new Pessoa("ruben", 1520, 9753190);
        assertEquals(pessoa.getTelemovel(), 9753190);
    }

    @Test
    void getTelemovel() {
        Pessoa pessoa = new Pessoa("ruben", 1520, 9753190);
        assertEquals(pessoa.getTelemovel(), 9753190);
    }
}