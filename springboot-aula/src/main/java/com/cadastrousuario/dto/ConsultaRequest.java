package com.cadastrousuario.dto;

import jakarta.validation.constraints.NotBlank;

public class ConsultaRequest {

    @NotBlank
    private String data;

    @NotBlank
    private String hora;

    @NotBlank
    private String medico;

    @NotBlank
    private String especialidade;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getMedico() {
        return medico;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
}
