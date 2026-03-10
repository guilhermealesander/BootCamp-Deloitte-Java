package com.cadastrousuario.repository;

import com.cadastrousuario.entities.DadosUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DadosUsuarioRepository extends JpaRepository<DadosUsuario, Long> {

    Optional<DadosUsuario> findTopByOrderByIdDesc();
}
