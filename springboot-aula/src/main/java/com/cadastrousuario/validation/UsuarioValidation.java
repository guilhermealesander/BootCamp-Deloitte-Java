package com.cadastrousuario.validation;

import com.cadastrousuario.model.DadosUsuario;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UsuarioValidation {

    private final CPFValidation cpfValidation;
    private final TelefoneValidation telefoneValidation;

    public UsuarioValidation(CPFValidation cpfValidation, TelefoneValidation telefoneValidation) {
        this.cpfValidation = cpfValidation;
        this.telefoneValidation = telefoneValidation;
    }

    public void validar(DadosUsuario usuario) {
        validarNome(usuario);
        validarEmail(usuario);
        validarIdade(usuario);
        cpfValidation.validar(usuario);
        telefoneValidation.validar(usuario);
    }

    public DadosUsuario validarUsuarioExistente(Optional<DadosUsuario> usuario) {
        return usuario.orElseThrow(() -> new IllegalStateException("Nenhum usuario cadastrado."));
    }

    private void validarNome(DadosUsuario usuario) {
        String nome = usuario.nome;
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do usuario deve ser informado.");
        }
        usuario.nome = nome.trim();
    }

    private void validarEmail(DadosUsuario usuario) {
        String email = usuario.email;
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("O email do usuario deve ser informado.");
        }
        usuario.email = email.trim();
    }

    private void validarIdade(DadosUsuario usuario) {
        Integer idade = usuario.idade;
        Integer meses = usuario.meses;

        if (idade == null || idade < 0) {
            throw new IllegalArgumentException("A idade em anos deve ser maior ou igual a zero.");
        }

        if (meses == null || meses < 0 || meses > 11) {
            throw new IllegalArgumentException("Os meses devem estar entre 0 e 11.");
        }
    }

}
