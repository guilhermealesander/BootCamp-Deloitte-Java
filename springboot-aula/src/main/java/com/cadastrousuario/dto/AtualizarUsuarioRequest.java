package com.cadastrousuario.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AtualizarUsuarioRequest {

    @NotBlank
    public String nome;

    @Email
    @NotBlank
    public String email;

    @NotNull
    @Min(0)
    public Integer idade;

    @NotNull
    @Min(0)
    public Integer meses;

    @NotBlank
    public String endereco;

    @NotBlank
    public String cpf;

    @NotBlank
    public String telefone;
}
