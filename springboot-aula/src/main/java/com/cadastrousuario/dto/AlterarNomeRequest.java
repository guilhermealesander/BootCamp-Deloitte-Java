package com.cadastrousuario.dto;

import jakarta.validation.constraints.NotBlank;

public class AlterarNomeRequest {

    @NotBlank
    public String novoNome;
}
