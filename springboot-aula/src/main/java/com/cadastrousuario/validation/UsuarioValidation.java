package com.cadastrousuario.validation;

import com.cadastrousuario.model.DadosUsuario;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UsuarioValidation {

    public void validar(DadosUsuario usuario) {
        validarNome(usuario);
        validarEmail(usuario);
        validarIdade(usuario);
        validarCpf(usuario);
        validarTelefone(usuario);
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

    private void validarCpf(DadosUsuario usuario) {
        String cpf = normalizarDigitos(usuario.cpf);
        if (cpf.length() != 11 || todosDigitosIguais(cpf) || !cpfValido(cpf)) {
            throw new IllegalArgumentException("O CPF do usuario e invalido.");
        }
        usuario.cpf = cpf;
    }

    private void validarTelefone(DadosUsuario usuario) {
        String telefone = normalizarDigitos(usuario.telefone);
        if (telefone.length() < 10 || telefone.length() > 11) {
            throw new IllegalArgumentException("O telefone do usuario e invalido.");
        }
        usuario.telefone = telefone;
    }

    private String normalizarDigitos(String valor) {
        return valor == null ? "" : valor.replaceAll("\\D", "");
    }

    private boolean todosDigitosIguais(String cpf) {
        return cpf.chars().distinct().count() == 1;
    }

    private boolean cpfValido(String cpf) {
        return calcularDigito(cpf, 9) == Character.getNumericValue(cpf.charAt(9))
                && calcularDigito(cpf, 10) == Character.getNumericValue(cpf.charAt(10));
    }

    private int calcularDigito(String cpf, int tamanhoBase) {
        int soma = 0;
        int peso = tamanhoBase + 1;
        for (int i = 0; i < tamanhoBase; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (peso - i);
        }
        int resto = (soma * 10) % 11;
        return resto == 10 ? 0 : resto;
    }
}
