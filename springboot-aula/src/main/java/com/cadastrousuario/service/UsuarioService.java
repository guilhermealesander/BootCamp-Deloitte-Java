package com.cadastrousuario.service;

import com.cadastrousuario.model.DadosUsuario;
import com.cadastrousuario.repository.DadosUsuarioRepository;
import com.cadastrousuario.validation.UsuarioValidation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {

    private final DadosUsuarioRepository dadosUsuarioRepository;
    private final UsuarioValidation usuarioValidation;

    public UsuarioService(
            DadosUsuarioRepository dadosUsuarioRepository,
            UsuarioValidation usuarioValidation
    ) {
        this.dadosUsuarioRepository = dadosUsuarioRepository;
        this.usuarioValidation = usuarioValidation;
    }

    @Transactional
    public DadosUsuario salvarUsuario(DadosUsuario dadosUsuario) {
        usuarioValidation.validar(dadosUsuario);
        return dadosUsuarioRepository.save(dadosUsuario);
    }

    public DadosUsuario obterUsuario() {
        return obterUsuarioAtual();
    }

    @Transactional
    public DadosUsuario alterarNome(String novoNome) {
        DadosUsuario usuario = obterUsuarioAtual();
        usuario.nome = novoNome;
        usuarioValidation.validar(usuario);
        return dadosUsuarioRepository.save(usuario);
    }

    @Transactional
    public void excluirUsuario() {
        dadosUsuarioRepository.delete(obterUsuarioAtual());
    }

    public DadosUsuario obterUsuarioAtual() {
        return usuarioValidation.validarUsuarioExistente(
                dadosUsuarioRepository.findTopByOrderByIdDesc()
        );
    }
}
