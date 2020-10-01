package Exame_Desportivo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Dados_PessoaisTest {

    @Test
    void getExame_n() {
        Dados_Pessoais dap = new Dados_Pessoais();
        dap.setExame_n(20);
        assertEquals(dap.getData_exame(),20);
    }

    @Test
    void setExame_n() {
        Dados_Pessoais dap = new Dados_Pessoais();
        dap.setExame_n(20);
        assertEquals(dap.getData_exame(),20);

    }

    @Test
    void getData_exame() {
        Dados_Pessoais dap = new Dados_Pessoais();
        dap.setData_exame("Novembro");
        assertNull(dap.getExame_n());
    }

    @Test
    void setData_exame() {
        Dados_Pessoais dap = new Dados_Pessoais();
        dap.setData_exame("Novembro");
        assertNull(dap.getExame_n());
    }

    @Test
    void getNome_medico() {
        Dados_Pessoais dap = new Dados_Pessoais();
        dap.setNomeMedico("Pedro");
        assertNull(dap.getNome_medico());
    }

    @Test
    void setNome_medico() {
        Dados_Pessoais dap = new Dados_Pessoais();
        dap.setNomeMedico("Pedro");
        assertNull(dap.getNome_medico());
    }

    @Test
    void getNascimento() {
        Dados_Pessoais dap = new Dados_Pessoais();
        dap.setNascimento("Novembro");
        assertNull(dap.getNascimento());
    }

    @Test
    void setNascimento() {
        Dados_Pessoais dap = new Dados_Pessoais();
        dap.setNascimento("Novembro");
        assertNull(dap.getNascimento());
    }

    @Test
    void getNacionalidade() {
        Dados_Pessoais dap = new Dados_Pessoais();
        dap.setNacionalidade("Alemão");
        assertNull(dap.getNascimento());
    }

    @Test
    void setNacionalidade() {
        Dados_Pessoais dap = new Dados_Pessoais();
        dap.setNacionalidade("Alemão");
        assertNull(dap.getNascimento());

    }

    @Test
    void getMorada() {
        Dados_Pessoais dap = new Dados_Pessoais();
        dap.setMorada("Lisboa");
        assertNull(dap.getMorada());
    }

    @Test
    void setMorada() {
        Dados_Pessoais dap = new Dados_Pessoais();
        dap.setMorada("Lisboa");
        assertNull(dap.getMorada());
    }

    @Test
    void getC_postal() {
        Dados_Pessoais dap = new Dados_Pessoais();
        dap.setC_postal("2910");
        assertNull(dap.getMorada());
    }

    @Test
    void setC_postal() {
        Dados_Pessoais dap = new Dados_Pessoais();
        dap.setC_postal("2910");
        assertNull(dap.getMorada());
    }

    @Test
    void setLocalidade() {
        Dados_Pessoais dap = new Dados_Pessoais();
        dap.setLocalidade("Setubal");
        assertNull(dap.getLocalidade());
    }

    @Test
    void getLocalidade() {
        Dados_Pessoais dap = new Dados_Pessoais();
        dap.setLocalidade("Setubal");
        assertNull(dap.getLocalidade());
    }

    @Test
    void setTelemovel() {
        Dados_Pessoais dap = new Dados_Pessoais();
        dap.setTelemovel(123456789);
        assertNull(dap.getTelemovel());
    }

    @Test
    void getTelemovel() {
        Dados_Pessoais dap = new Dados_Pessoais();
        dap.setTelemovel(123456789);
        assertNull(dap.getTelemovel());
    }

    @Test
    void setClube() {
        Dados_Pessoais dap = new Dados_Pessoais();
        dap.setClube("Benfica");
        assertNull(dap.getClube());
    }

    @Test
    void getClube() {
        Dados_Pessoais dap = new Dados_Pessoais();
        dap.setClube("Benfica");
        assertNull(dap.getClube());
    }

    @Test
    void getModalidade() {
        Dados_Pessoais dap = new Dados_Pessoais();
        dap.setModalidade("Futebol");
        assertNull(dap.getModalidade());
    }

    @Test
    void setModalidade() {
        Dados_Pessoais dap = new Dados_Pessoais();
        dap.setModalidade("Futebol");
        assertNull(dap.getModalidade());
    }

    @Test
    void getEscalao() {
        Dados_Pessoais dap = new Dados_Pessoais();
        dap.setEscalao("Primeiro");
        assertNull(dap.getEscalao());
    }

    @Test
    void setEscalao() {
        Dados_Pessoais dap = new Dados_Pessoais();
        dap.setEscalao("Primeiro");
        assertNull(dap.getEscalao());
    }

    @Test
    void setNomeMedico() {
        Dados_Pessoais dap = new Dados_Pessoais();
        dap.setNomeMedico("Ricardo");
        assertNull(dap.getNome_medico());
    }

    @Test
    void getNomeMedico() {
        Dados_Pessoais dap = new Dados_Pessoais();
        dap.setNomeMedico("Ricardo");
        assertNull(dap.getNome_medico());
    }
}