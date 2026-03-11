package com.cadastrousuario.repository;

import com.cadastrousuario.entities.DadosUsuario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("DadosUsuarioRepository mock tests")
class DadosUsuarioRepositoryTest {

    @Mock
    private DadosUsuarioRepository dadosUsuarioRepository;

    @Test
    @DisplayName("Should return current user from mocked repository")
    void findTopByOrderByIdDescCase1() {
        DadosUsuario usuario = new DadosUsuario("Guilherme", "guilherme@gmail.com", 25, "Rua Sei la", "81999999999");

        when(dadosUsuarioRepository.findTopByOrderByIdDesc()).thenReturn(Optional.of(usuario));

        Optional<DadosUsuario> resultado = dadosUsuarioRepository.findTopByOrderByIdDesc();

        assertSame(usuario, resultado.orElseThrow());
    }
}
