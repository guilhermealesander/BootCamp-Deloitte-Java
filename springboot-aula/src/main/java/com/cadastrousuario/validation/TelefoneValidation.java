package com.cadastrousuario.validation;

import com.cadastrousuario.model.DadosUsuario;
import org.springframework.stereotype.Component;

@Component
public class TelefoneValidation {

    public void validar(DadosUsuario usuario) {
        String telefone = normalizarDigitos(usuario.telefone);
        if (telefone.length() < 10 || telefone.length() > 11) {
            throw new IllegalArgumentException("O telefone do usuario e invalido.");
        }
        usuario.telefone = telefone;
    }

    private String normalizarDigitos(String valor) {
        return valor == null ? "" : valor.replaceAll("\\D", "");
    }
}
