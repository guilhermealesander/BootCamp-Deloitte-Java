package com.cadastrousuario.repository;

import com.cadastrousuario.model.DadosUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DadosUsuarioRepository extends JpaRepository<DadosUsuario, Long> {

    Optional<DadosUsuario> findTopByOrderByIdDesc();

    Optional<DadosUsuario> findByCpfAndEmail(String cpf, String email);

    boolean existsByCpf(String cpf);

    boolean existsByEmail(String email);

    boolean existsByCpfAndIdNot(String cpf, Long id);

    boolean existsByEmailAndIdNot(String email, Long id);
}
