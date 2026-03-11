package com.cadastrousuario.entities;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

@DisplayName("Consulta unit tests")
class ConsultaTest {

    @Test
    @DisplayName("Should create appointment with constructor")
    void consultaCase1() {
        Consulta consulta = new Consulta("2026-03-20", "14:00", "Dr. Alesander", "Oftalmologista");

        assertEquals("2026-03-20", consulta.getData());
        assertEquals("14:00", consulta.getHora());
        assertEquals("Dr. Alesander", consulta.getMedico());
        assertEquals("Oftalmologista", consulta.getEspecialidade());
    }

    @Test
    @DisplayName("Should update appointment fields and user")
    void consultaCase2() {
        Consulta consulta = new Consulta();
        DadosUsuario usuario = new DadosUsuario("Guilherme", "Guilherme@gmail.com", 25, "Rua Sei la", "81999999999");

        consulta.setData("2026-03-21");
        consulta.setHora("09:00");
        consulta.setMedico("Dra. Souza");
        consulta.setEspecialidade("Dermatologia");
        consulta.setUsuario(usuario);

        assertEquals("2026-03-21", consulta.getData());
        assertEquals("09:00", consulta.getHora());
        assertEquals("Dra. Souza", consulta.getMedico());
        assertEquals("Dermatologia", consulta.getEspecialidade());
        assertSame(usuario, consulta.getUsuario());
    }
}
