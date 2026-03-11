package com.cadastrousuario.repository;

import com.cadastrousuario.entities.Consulta;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("ConsultaRepository mock tests")
class ConsultaRepositoryTest {

    @Mock
    private ConsultaRepository consultaRepository;

    @Test
    @DisplayName("Should return appointments from mocked repository")
    void findByUsuarioIdOrderByIdAscCase1() {
        List<Consulta> consultas = List.of(
                new Consulta("2026-03-20", "14:00", "Dr. Alesander", "Oftalmologista"),
                new Consulta("2026-03-21", "10:00", "Dra. Souza", "Dermatologia")
        );

        when(consultaRepository.findByUsuarioIdOrderByIdAsc(1L)).thenReturn(consultas);

        List<Consulta> resultado = consultaRepository.findByUsuarioIdOrderByIdAsc(1L);

        assertEquals(2, resultado.size());
    }
}
