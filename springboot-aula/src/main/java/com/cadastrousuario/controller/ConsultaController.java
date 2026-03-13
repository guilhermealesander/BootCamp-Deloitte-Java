package com.cadastrousuario.controller;

import com.cadastrousuario.dto.ConsultaRequest;
import com.cadastrousuario.model.Consulta;
import com.cadastrousuario.service.ConsultaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/consultas")
public class ConsultaController {

    private final ConsultaService consultaService;

    public ConsultaController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Consulta marcarConsulta(@Valid @RequestBody ConsultaRequest request) {
        Consulta consulta = new Consulta(
                request.data,
                request.hora,
                request.medico,
                request.especialidade
        );
        return consultaService.marcarConsulta(consulta);
    }

    @PostMapping("/remarcar")
    @ResponseStatus(HttpStatus.CREATED)
    public Consulta remarcarConsulta(@Valid @RequestBody ConsultaRequest request) {
        Consulta consulta = new Consulta(
                request.data,
                request.hora,
                request.medico,
                request.especialidade
        );
        return consultaService.remarcarConsulta(consulta);
    }

    @GetMapping
    public List<Consulta> listarConsultas() {
        return consultaService.listarConsultas();
    }
}
