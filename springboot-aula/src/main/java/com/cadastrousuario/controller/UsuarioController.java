package com.cadastrousuario.controller;

import com.cadastrousuario.dto.AlterarNomeRequest;
import com.cadastrousuario.dto.AtualizarUsuarioRequest;
import com.cadastrousuario.dto.CriarUsuarioRequest;
import com.cadastrousuario.dto.LoginRequest;
import com.cadastrousuario.model.DadosUsuario;
import com.cadastrousuario.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @PostMapping("/login")
    public DadosUsuario login(@Valid @RequestBody LoginRequest request) {
        return usuarioService.login(request.cpf, request.email);
    }

    @GetMapping
    public DadosUsuario obterUsuario() {
        return usuarioService.obterUsuario();
    }

    @GetMapping("/{id}")
    public DadosUsuario obterUsuarioPorId(@PathVariable Long id) {
        return usuarioService.obterUsuarioPorId(id);
    }

    @PatchMapping("/nome")
    public DadosUsuario alterarNome(@Valid @RequestBody AlterarNomeRequest request) {
        return usuarioService.alterarNome(request.novoNome);
    }

    @PutMapping("/{id}")
    public DadosUsuario atualizarUsuario(@PathVariable Long id, @Valid @RequestBody AtualizarUsuarioRequest request) {
        DadosUsuario usuario = new DadosUsuario(
                request.nome,
                request.email,
                request.idade,
                request.meses,
                request.endereco,
                request.cpf,
                request.telefone
        );
        return usuarioService.atualizarUsuario(id, usuario);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirUsuario() {
        usuarioService.excluirUsuario();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirUsuarioPorId(@PathVariable Long id) {
        usuarioService.excluirUsuario(id);
    }
}
