package com.cadastrousuario.controller;

import com.cadastrousuario.dto.AlterarNomeRequest;
import com.cadastrousuario.dto.ConsultaRequest;
import com.cadastrousuario.dto.CriarUsuarioRequest;
import com.cadastrousuario.entities.Consulta;
import com.cadastrousuario.entities.DadosUsuario;
import com.cadastrousuario.service.CadastroService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CadastroController {

    public final CadastroService cadastroService;

    public CadastroController(CadastroService cadastroService) {
        this.cadastroService = cadastroService;
    }

    @PostMapping("/usuarios")
    @ResponseStatus(HttpStatus.CREATED)
    public DadosUsuario criarUsuario(@Valid @RequestBody CriarUsuarioRequest request) {
        DadosUsuario usuario = new DadosUsuario(
                request.getNome(),
                request.getEmail(),
                request.getIdade(),
                request.getEndereco(),
                request.getTelefone()
        );
        return cadastroService.salvarUsuario(usuario);
    }

    @GetMapping("/usuarios")
    public DadosUsuario obterUsuario() {
        return cadastroService.obterUsuario();
    }

    @PatchMapping("/usuarios/nome")
    public DadosUsuario alterarNome(@Valid @RequestBody AlterarNomeRequest request) {
        return cadastroService.alterarNome(request.getNovoNome());
    }

    @DeleteMapping("/usuarios")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirUsuario() {
        cadastroService.excluirUsuario();
    }

    @PostMapping("/consultas")
    @ResponseStatus(HttpStatus.CREATED)
    public Consulta marcarConsulta(@Valid @RequestBody ConsultaRequest request) {
        Consulta consulta = new Consulta(
                request.getData(),
                request.getHora(),
                request.getMedico(),
                request.getEspecialidade()
        );
        return cadastroService.marcarConsulta(consulta);
    }

    @PostMapping("/consultas/remarcar")
    @ResponseStatus(HttpStatus.CREATED)
    public Consulta remarcarConsulta(@Valid @RequestBody ConsultaRequest request) {
        Consulta consulta = new Consulta(
                request.getData(),
                request.getHora(),
                request.getMedico(),
                request.getEspecialidade()
        );
        return cadastroService.remarcarConsulta(consulta);
    }

    @GetMapping("/consultas")
    public List<Consulta> listarConsultas() {
        return cadastroService.listarConsultas();
    }
}
