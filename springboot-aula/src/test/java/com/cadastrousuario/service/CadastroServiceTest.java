package com.cadastrousuario.service;

import com.cadastrousuario.entities.Consulta;
import com.cadastrousuario.entities.DadosUsuario;
import com.cadastrousuario.repository.ConsultaRepository;
import com.cadastrousuario.repository.DadosUsuarioRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("CadastroService unit tests")
class CadastroServiceTest {

    @Mock
    private DadosUsuarioRepository dadosUsuarioRepository;

    @Mock
    private ConsultaRepository consultaRepository;

    @InjectMocks
    private CadastroService cadastroService;

    @Test
    @DisplayName("Should save user successfully")
    void salvarUsuarioCase1() {
        DadosUsuario usuario = new DadosUsuario("Guilherme", "Guilherme@gmail.com", 24, "Rua sei la", "81999999999");

        when(dadosUsuarioRepository.save(usuario)).thenReturn(usuario);

        DadosUsuario resultado = cadastroService.salvarUsuario(usuario);

        assertSame(usuario, resultado);
        verify(dadosUsuarioRepository).save(usuario);
    }

    @Test
    @DisplayName("Should update current user name successfully")
    void alterarNomeCase1() {
        DadosUsuario usuario = new DadosUsuario("Guilherme", "Guilherme@gmail.com", 25, "Rua sei la", "81999999999");

        when(dadosUsuarioRepository.findTopByOrderByIdDesc()).thenReturn(Optional.of(usuario));
        when(dadosUsuarioRepository.save(usuario)).thenReturn(usuario);

        DadosUsuario resultado = cadastroService.alterarNome("Melo");

        assertEquals("Melo", resultado.getNome());
        verify(dadosUsuarioRepository).save(usuario);
    }

    @Test
    @DisplayName("Should create appointment for current user")
    void marcarConsultaCase1() {
        DadosUsuario usuario = new DadosUsuario("Guilherme", "Guilherme@gmail.com", 24, "Rua sei la", "81999999999");
        Consulta consulta = new Consulta("2026-03-20", "14:00", "Dr. Jorge", "Oftalmologista");

        when(dadosUsuarioRepository.findTopByOrderByIdDesc()).thenReturn(Optional.of(usuario));
        when(consultaRepository.save(consulta)).thenReturn(consulta);

        Consulta resultado = cadastroService.marcarConsulta(consulta);

        assertSame(usuario, resultado.getUsuario());
        verify(consultaRepository).save(consulta);
    }

    @Test
    @DisplayName("Should list appointments from current user")
    void listarConsultasCase1() {
        DadosUsuario usuario = new DadosUsuario("Guilherme", "Guilherme@gmail.com", 25, "Rua sei la", "81999999999");
        usuario.id = 1L;
        List<Consulta> consultas = List.of(
                new Consulta("2026-03-20", "14:00", "Dr. Alesander", "Oftalmologista"),
                new Consulta("2026-03-21", "10:00", "Dra. Souza", "Dermatologia")
        );

        when(dadosUsuarioRepository.findTopByOrderByIdDesc()).thenReturn(Optional.of(usuario));
        when(consultaRepository.findByUsuarioIdOrderByIdAsc(1L)).thenReturn(consultas);

        List<Consulta> resultado = cadastroService.listarConsultas();

        assertEquals(2, resultado.size());
        assertEquals("Dr. Alesander", resultado.get(0).getMedico());
        verify(consultaRepository).findByUsuarioIdOrderByIdAsc(1L);
    }

    @Test
    @DisplayName("Should throw exception when there is no registered user")
    void obterUsuarioCase2() {
        when(dadosUsuarioRepository.findTopByOrderByIdDesc()).thenReturn(Optional.empty());

        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> cadastroService.obterUsuario()
        );

        assertEquals("Nenhum usuario cadastrado.", exception.getMessage());
    }
}
