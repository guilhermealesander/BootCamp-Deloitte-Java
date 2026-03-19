package com.cadastrousuario.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class LoginRequest {

    @NotBlank
    public String cpf;

    @Email
    @NotBlank
    public String email;
}
