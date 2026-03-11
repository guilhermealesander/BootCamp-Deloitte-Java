package com.cadastrousuario.entities;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

@DisplayName("DadosUsuario unit tests")
class DadosUsuarioTest {

    @Test
    @DisplayName("Should create user with constructor")
    void dadosUsuarioCase1() {
        DadosUsuario usuario = new DadosUsuario("Guilherme", "guilherme@gmail.com", 25, "Rua Sei la", "81999999999");

        assertEquals("Guilherme", usuario.getNome());
        assertEquals("guilherme@gmail.com", usuario.getEmail());
        assertEquals(25, usuario.getIdade());
        assertEquals("Rua Sei la", usuario.getEndereco());
        assertEquals("81999999999", usuario.getTelefone());
    }

    @Test
    @DisplayName("Should update user fields and consultations")
    void dadosUsuarioCase2() {
        DadosUsuario usuario = new DadosUsuario();
        Consulta consulta = new Consulta("2026-03-20", "14:00", "Dr. Alesander", "Oftalmologista");

        usuario.setNome("Guilherme");
        usuario.setEmail("guilherme@gmail.com");
        usuario.setIdade(25);
        usuario.setEndereco("Rua sei la");
        usuario.setTelefone("81888888888");
        usuario.setConsultas(List.of(consulta));

        assertEquals("Guilherme", usuario.getNome());
        assertEquals("guilherme@gmail.com", usuario.getEmail());
        assertEquals(25, usuario.getIdade());
        assertEquals("Rua sei la", usuario.getEndereco());
        assertEquals("81888888888", usuario.getTelefone());
        assertSame(consulta, usuario.getConsultas().get(0));
    }
}
