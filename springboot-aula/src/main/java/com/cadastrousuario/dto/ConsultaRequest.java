package com.cadastrousuario.dto;

import jakarta.validation.constraints.NotBlank;

public class ConsultaRequest {

    @NotBlank
    public String data;

    @NotBlank
    public String hora;

    @NotBlank
    public String medico;

    @NotBlank
    public String especialidade;
}
