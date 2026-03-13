package com.cadastrousuario.validation;

import com.cadastrousuario.model.DadosUsuario;
import org.springframework.stereotype.Component;

@Component
public class CPFValidation {

    public void validar(DadosUsuario usuario) {
        String cpf = normalizarDigitos(usuario.cpf);
        if (cpf.length() != 11 || todosDigitosIguais(cpf) || !cpfValido(cpf)) {
            throw new IllegalArgumentException("O CPF do usuario e invalido.");
        }
        usuario.cpf = cpf;
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
