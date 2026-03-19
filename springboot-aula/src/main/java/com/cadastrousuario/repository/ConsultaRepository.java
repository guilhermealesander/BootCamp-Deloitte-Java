package com.cadastrousuario.repository;

import com.cadastrousuario.model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    List<Consulta> findByUsuarioIdOrderByIdAsc(Long usuarioId);

    Optional<Consulta> findByIdAndUsuarioId(Long id, Long usuarioId);
}
