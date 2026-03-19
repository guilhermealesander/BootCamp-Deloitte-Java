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
        validarDuplicidade(dadosUsuario);
        return dadosUsuarioRepository.save(dadosUsuario);
    }

    public DadosUsuario obterUsuario() {
        return obterUsuarioAtual();
    }

    public DadosUsuario obterUsuarioPorId(Long id) {
        return usuarioValidation.validarUsuarioExistente(dadosUsuarioRepository.findById(id));
    }

    public DadosUsuario login(String cpf, String email) {
        return usuarioValidation.validarUsuarioExistente(
                dadosUsuarioRepository.findByCpfAndEmail(normalizarCpf(cpf), normalizarEmail(email))
        );
    }

    @Transactional
    public DadosUsuario alterarNome(String novoNome) {
        DadosUsuario usuario = obterUsuarioAtual();
        usuario.nome = novoNome;
        usuarioValidation.validar(usuario);
        validarDuplicidade(usuario);
        return dadosUsuarioRepository.save(usuario);
    }

    @Transactional
    public DadosUsuario atualizarUsuario(Long id, DadosUsuario dadosAtualizados) {
        DadosUsuario usuario = obterUsuarioPorId(id);
        usuario.nome = dadosAtualizados.nome;
        usuario.email = dadosAtualizados.email;
        usuario.idade = dadosAtualizados.idade;
        usuario.meses = dadosAtualizados.meses;
        usuario.endereco = dadosAtualizados.endereco;
        usuario.cpf = dadosAtualizados.cpf;
        usuario.telefone = dadosAtualizados.telefone;
        usuarioValidation.validar(usuario);
        validarDuplicidade(usuario);
        return dadosUsuarioRepository.save(usuario);
    }

    @Transactional
    public void excluirUsuario() {
        dadosUsuarioRepository.delete(obterUsuarioAtual());
    }

    @Transactional
    public void excluirUsuario(Long id) {
        dadosUsuarioRepository.delete(obterUsuarioPorId(id));
    }

    public DadosUsuario obterUsuarioAtual() {
        return usuarioValidation.validarUsuarioExistente(
                dadosUsuarioRepository.findTopByOrderByIdDesc()
        );
    }

    private void validarDuplicidade(DadosUsuario usuario) {
        if (usuario.id == null) {
            if (dadosUsuarioRepository.existsByCpf(usuario.cpf)) {
                throw new IllegalArgumentException("Ja existe um usuario cadastrado com este CPF.");
            }
            if (dadosUsuarioRepository.existsByEmail(usuario.email)) {
                throw new IllegalArgumentException("Ja existe um usuario cadastrado com este email.");
            }
            return;
        }

        if (dadosUsuarioRepository.existsByCpfAndIdNot(usuario.cpf, usuario.id)) {
            throw new IllegalArgumentException("Ja existe um usuario cadastrado com este CPF.");
        }

        if (dadosUsuarioRepository.existsByEmailAndIdNot(usuario.email, usuario.id)) {
            throw new IllegalArgumentException("Ja existe um usuario cadastrado com este email.");
        }
    }

    private String normalizarCpf(String cpf) {
        return cpf == null ? "" : cpf.replaceAll("\\D", "");
    }

    private String normalizarEmail(String email) {
        return email == null ? "" : email.trim().toLowerCase();
    }
}
