package com.cadastrousuario.validation;

import com.cadastrousuario.model.Consulta;
import org.springframework.stereotype.Component;

@Component
public class ConsultaValidation {

    public void validarConsulta(Consulta consulta) {
        validarCampo(consulta.data, "Informe a data da consulta.");
        validarCampo(consulta.hora, "Informe a hora da consulta.");
        validarCampo(consulta.medico, "Informe o medico especialista.");
        validarCampo(consulta.especialidade, "A especialidade da consulta deve ser informada.");
    }

    private void validarCampo(String valor, String mensagem) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException(mensagem);
        }
    }
}
