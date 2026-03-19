package com.cadastrousuario.controller;

import com.cadastrousuario.dto.ConsultaRequest;
import com.cadastrousuario.model.Consulta;
import com.cadastrousuario.service.ConsultaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @PostMapping("/usuario/{usuarioId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Consulta marcarConsultaParaUsuario(@PathVariable Long usuarioId, @Valid @RequestBody ConsultaRequest request) {
        Consulta consulta = new Consulta(
                request.data,
                request.hora,
                request.medico,
                request.especialidade
        );
        return consultaService.marcarConsultaParaUsuario(usuarioId, consulta);
    }

    @PutMapping("/{consultaId}/usuario/{usuarioId}")
    public Consulta remarcarConsultaPorUsuario(
            @PathVariable Long consultaId,
            @PathVariable Long usuarioId,
            @Valid @RequestBody ConsultaRequest request
    ) {
        Consulta consulta = new Consulta(
                request.data,
                request.hora,
                request.medico,
                request.especialidade
        );
        return consultaService.remarcarConsulta(consultaId, usuarioId, consulta);
    }

    @DeleteMapping("/{consultaId}/usuario/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desmarcarConsulta(@PathVariable Long consultaId, @PathVariable Long usuarioId) {
        consultaService.desmarcarConsulta(consultaId, usuarioId);
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<Consulta> listarConsultasDoUsuario(@PathVariable Long usuarioId) {
        return consultaService.listarConsultasDoUsuario(usuarioId);
    }
}
