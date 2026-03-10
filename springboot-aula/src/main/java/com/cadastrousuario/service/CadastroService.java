package com.cadastrousuario.service;

import com.cadastrousuario.entities.Consulta;
import com.cadastrousuario.entities.DadosUsuario;
import com.cadastrousuario.repository.ConsultaRepository;
import com.cadastrousuario.repository.DadosUsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CadastroService {

    private final DadosUsuarioRepository dadosUsuarioRepository;
    private final ConsultaRepository consultaRepository;

    public CadastroService(DadosUsuarioRepository dadosUsuarioRepository, ConsultaRepository consultaRepository) {
        this.dadosUsuarioRepository = dadosUsuarioRepository;
        this.consultaRepository = consultaRepository;
    }

    @Transactional
    public DadosUsuario salvarUsuario(DadosUsuario dadosUsuario) {
        return dadosUsuarioRepository.save(dadosUsuario);
    }

    public DadosUsuario obterUsuario() {
        return obterUsuarioAtual();
    }

    @Transactional
    public DadosUsuario alterarNome(String novoNome) {
        DadosUsuario usuario = obterUsuarioAtual();
        usuario.setNome(novoNome);
        return dadosUsuarioRepository.save(usuario);
    }

    @Transactional
    public void excluirUsuario() {
        DadosUsuario usuario = obterUsuarioAtual();
        dadosUsuarioRepository.delete(usuario);
    }

    @Transactional
    public Consulta marcarConsulta(Consulta consulta) {
        DadosUsuario usuario = obterUsuarioAtual();
        consulta.setUsuario(usuario);
        return consultaRepository.save(consulta);
    }

    @Transactional
    public Consulta remarcarConsulta(Consulta consulta) {
        DadosUsuario usuario = obterUsuarioAtual();
        consulta.setUsuario(usuario);
        return consultaRepository.save(consulta);
    }

    public List<Consulta> listarConsultas() {
        DadosUsuario usuario = obterUsuarioAtual();
        return consultaRepository.findByUsuarioIdOrderByIdAsc(usuario.getId());
    }

    private DadosUsuario obterUsuarioAtual() {
        return dadosUsuarioRepository.findTopByOrderByIdDesc()
                .orElseThrow(() -> new IllegalStateException("Nenhum usuario cadastrado."));
    }
}