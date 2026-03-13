package com.cadastrousuario.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "consultas")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(nullable = false)
    public String data;

    @Column(nullable = false)
    public String hora;

    @Column(nullable = false)
    public String medico;

    @Column(nullable = false)
    public String especialidade;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    public DadosUsuario usuario;

    public Consulta() {
    }

    public Consulta(String data, String hora, String medico, String especialidade) {
        this.data = data;
        this.hora = hora;
        this.medico = medico;
        this.especialidade = especialidade;
    }
}
