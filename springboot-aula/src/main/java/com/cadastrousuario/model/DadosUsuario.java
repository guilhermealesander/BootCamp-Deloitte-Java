package com.cadastrousuario.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuarios")
public class DadosUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(nullable = false)
    public String nome;

    @Column(nullable = false)
    public String email;

    @Column(nullable = false)
    public Integer idade;

    @Column(nullable = false)
    public Integer meses;

    @Column(nullable = false)
    public String endereco;

    @Column(nullable = false)
    public String cpf;

    @Column(nullable = false)
    public String telefone;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Consulta> consultas = new ArrayList<>();

    public DadosUsuario() {
    }

    public DadosUsuario(String nome, String email, Integer idade, Integer meses, String endereco, String cpf, String telefone) {
        this.nome = nome;
        this.email = email;
        this.idade = idade;
        this.meses = meses;
        this.endereco = endereco;
        this.cpf = cpf;
        this.telefone = telefone;
    }
}
