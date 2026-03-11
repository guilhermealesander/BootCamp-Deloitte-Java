package com.cadastrousuario.controller;

import com.cadastrousuario.dto.AlterarNomeRequest;
import com.cadastrousuario.dto.ConsultaRequest;
import com.cadastrousuario.dto.CriarUsuarioRequest;
import com.cadastrousuario.entities.Consulta;
import com.cadastrousuario.entities.DadosUsuario;
import com.cadastrousuario.service.CadastroService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("CadastroController unit tests")
class CadastroControllerTest {

    @Mock
    private CadastroService cadastroService;

    @InjectMocks
    private CadastroController cadastroController;

    @Test
    @DisplayName("Should create user successfully")
    void criarUsuarioCase1() {
        CriarUsuarioRequest request = new CriarUsuarioRequest();
        request.setNome("Guilherme");
        request.setEmail("Guilherme@gmail.com");
        request.setIdade(24);
        request.setEndereco("Rua sei la");
        request.setTelefone("81999999999");

        DadosUsuario usuarioSalvo = new DadosUsuario("Guilherme", "guilherme@gmail.com", 24, "Rua Sei la", "81999999999");

        when(cadastroService.salvarUsuario(org.mockito.ArgumentMatchers.any(DadosUsuario.class))).thenReturn(usuarioSalvo);

        DadosUsuario resultado = cadastroController.criarUsuario(request);

        assertEquals("Guilherme", resultado.getNome());
        assertEquals("guilherme@gmail.com", resultado.getEmail());
        verify(cadastroService).salvarUsuario(org.mockito.ArgumentMatchers.any(DadosUsuario.class));
    }

    @Test
    @DisplayName("Should return current user")
    void obterUsuarioCase1() {
        DadosUsuario usuario = new DadosUsuario("Guilherme", "guilherme@gmail.com", 24, "Rua Sei la", "81999999999");

        when(cadastroService.obterUsuario()).thenReturn(usuario);

        DadosUsuario resultado = cadastroController.obterUsuario();

        assertSame(usuario, resultado);
        verify(cadastroService).obterUsuario();
    }

    @Test
    @DisplayName("Should update user name successfully")
    void alterarNomeCase1() {
        AlterarNomeRequest request = new AlterarNomeRequest();
        request.setNovoNome("Melo");

        DadosUsuario usuarioAtualizado = new DadosUsuario("Melo", "Melo@Hotmail.com", 25, "Rua ABCD", "81999999999");

        when(cadastroService.alterarNome("Melo")).thenReturn(usuarioAtualizado);

        DadosUsuario resultado = cadastroController.alterarNome(request);

        assertEquals("Melo", resultado.getNome());
        verify(cadastroService).alterarNome("Melo");
    }

    @Test
    @DisplayName("Should create appointment successfully")
    void marcarConsultaCase1() {
        ConsultaRequest request = new ConsultaRequest();
        request.setData("2026-03-20");
        request.setHora("14:00");
        request.setMedico("Dr. Alesander");
        request.setEspecialidade("Oftalmologista");

        Consulta consulta = new Consulta("2026-03-20", "14:00", "Dr. Alesander", "Oftalmologista");

        when(cadastroService.marcarConsulta(org.mockito.ArgumentMatchers.any(Consulta.class))).thenReturn(consulta);

        Consulta resultado = cadastroController.marcarConsulta(request);

        assertEquals("Dr. Alesander", resultado.getMedico());
        verify(cadastroService).marcarConsulta(org.mockito.ArgumentMatchers.any(Consulta.class));
    }

    @Test
    @DisplayName("Should list appointments successfully")
    void listarConsultasCase1() {
        List<Consulta> consultas = List.of(
                new Consulta("2026-03-20", "14:00", "Dr. Alesander", "Oftalmologista"),
                new Consulta("2026-03-21", "10:00", "Dra. Souza", "Dermatologia")
        );

        when(cadastroService.listarConsultas()).thenReturn(consultas);

        List<Consulta> resultado = cadastroController.listarConsultas();

        assertEquals(2, resultado.size());
        verify(cadastroService).listarConsultas();
    }
}
