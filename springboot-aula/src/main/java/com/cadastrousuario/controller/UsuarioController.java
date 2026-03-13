package com.cadastrousuario.controller;

import com.cadastrousuario.dto.AlterarNomeRequest;
import com.cadastrousuario.dto.CriarUsuarioRequest;
import com.cadastrousuario.model.DadosUsuario;
import com.cadastrousuario.service.UsuarioService;
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

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DadosUsuario criarUsuario(@Valid @RequestBody CriarUsuarioRequest request) {
        DadosUsuario usuario = new DadosUsuario(
                request.nome,
                request.email,
                request.idade,
                request.meses,
                request.endereco,
                request.cpf,
                request.telefone
        );
        return usuarioService.salvarUsuario(usuario);
    }

    @GetMapping
    public DadosUsuario obterUsuario() {
        return usuarioService.obterUsuario();
    }

    @PatchMapping("/nome")
    public DadosUsuario alterarNome(@Valid @RequestBody AlterarNomeRequest request) {
        return usuarioService.alterarNome(request.novoNome);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirUsuario() {
        usuarioService.excluirUsuario();
    }
}
