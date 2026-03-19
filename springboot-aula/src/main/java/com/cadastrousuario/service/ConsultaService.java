package com.cadastrousuario.service;

import com.cadastrousuario.model.Consulta;
import com.cadastrousuario.model.DadosUsuario;
import com.cadastrousuario.repository.ConsultaRepository;
import com.cadastrousuario.validation.ConsultaValidation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final UsuarioService usuarioService;
    private final ConsultaValidation consultaValidation;

    public ConsultaService(
            ConsultaRepository consultaRepository,
            UsuarioService usuarioService,
            ConsultaValidation consultaValidation
    ) {
        this.consultaRepository = consultaRepository;
        this.usuarioService = usuarioService;
        this.consultaValidation = consultaValidation;
    }

    @Transactional
    public Consulta marcarConsulta(Consulta consulta) {
        consultaValidation.validarConsulta(consulta);
        DadosUsuario usuario = usuarioService.obterUsuarioAtual();
        consulta.usuario = usuario;
        return consultaRepository.save(consulta);
    }

    @Transactional
    public Consulta remarcarConsulta(Consulta consulta) {
        consultaValidation.validarConsulta(consulta);
        DadosUsuario usuario = usuarioService.obterUsuarioAtual();
        consulta.usuario = usuario;
        return consultaRepository.save(consulta);
    }

    public List<Consulta> listarConsultas() {
        DadosUsuario usuario = usuarioService.obterUsuarioAtual();
        return consultaRepository.findByUsuarioIdOrderByIdAsc(usuario.id);
    }

    @Transactional
    public Consulta marcarConsultaParaUsuario(Long usuarioId, Consulta consulta) {
        consultaValidation.validarConsulta(consulta);
        DadosUsuario usuario = usuarioService.obterUsuarioPorId(usuarioId);
        consulta.usuario = usuario;
        return consultaRepository.save(consulta);
    }

    @Transactional
    public Consulta remarcarConsulta(Long consultaId, Long usuarioId, Consulta novosDados) {
        consultaValidation.validarConsulta(novosDados);
        Consulta consulta = obterConsultaDoUsuario(consultaId, usuarioId);
        consulta.data = novosDados.data;
        consulta.hora = novosDados.hora;
        consulta.medico = novosDados.medico;
        consulta.especialidade = novosDados.especialidade;
        return consultaRepository.save(consulta);
    }

    @Transactional
    public void desmarcarConsulta(Long consultaId, Long usuarioId) {
        consultaRepository.delete(obterConsultaDoUsuario(consultaId, usuarioId));
    }

    public List<Consulta> listarConsultasDoUsuario(Long usuarioId) {
        usuarioService.obterUsuarioPorId(usuarioId);
        return consultaRepository.findByUsuarioIdOrderByIdAsc(usuarioId);
    }

    private Consulta obterConsultaDoUsuario(Long consultaId, Long usuarioId) {
        usuarioService.obterUsuarioPorId(usuarioId);
        return consultaRepository.findByIdAndUsuarioId(consultaId, usuarioId)
                .orElseThrow(() -> new IllegalStateException("Consulta nao encontrada para o usuario informado."));
    }
}
