package com.cadastrousuario.repository;

import com.cadastrousuario.entities.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    List<Consulta> findByUsuarioIdOrderByIdAsc(Long usuarioId);
}
