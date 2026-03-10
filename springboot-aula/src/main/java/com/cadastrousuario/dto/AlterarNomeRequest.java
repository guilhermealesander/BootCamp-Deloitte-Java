package com.cadastrousuario.dto;

import jakarta.validation.constraints.NotBlank;

public class AlterarNomeRequest {

    @NotBlank
    private String novoNome;

    public String getNovoNome() {
        return novoNome;
    }

    public void setNovoNome(String novoNome) {
        this.novoNome = novoNome;
    }
}
