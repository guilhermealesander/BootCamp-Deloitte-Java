# BootCamp Deloitte Java

![Java](https://img.shields.io/badge/Java-17-E76F00?style=for-the-badge)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.3.5-6DB33F?style=for-the-badge)
![H2](https://img.shields.io/badge/Database-H2-1E88E5?style=for-the-badge)
![Status](https://img.shields.io/badge/Status-Em_Construcao-F4A261?style=for-the-badge)

## Visao Geral

Este repositório reúne a evolução de um sistema de cadastro de usuário e agendamento de consultas médicas desenvolvido no BootCamp da Deloitte.

O projeto saiu de uma estrutura mais simples e evoluiu para uma API REST com Spring Boot, aplicando testes e princípios SOLID ao longo da implementação.

## Destaques

- Cadastro, consulta, alteração e exclusão de usuário
- Agendamento, listagem e remarcar consultas
- Persistência com H2
- Testes unitários e de integração
- Aplicação prática de `SRP` e `OCP`
- Validações extensíveis para usuário

## Estrutura

```text
BootCamp-Deloitte-Java
├── springboot-aula
│   ├── src/main/java/com/cadastrousuario
│   │   ├── controller
│   │   ├── dto
│   │   ├── model
│   │   ├── repository
│   │   ├── service
│   │   └── validation
│   └── src/test/java/com/cadastrousuario
└── src
```

## O que foi aplicado

### SRP

As responsabilidades foram separadas por contexto:

- `UsuarioController` e `ConsultaController`
- `UsuarioService` e `ConsultaService`
- `validation` isolada da regra principal de serviço

### OCP

As validações de usuário foram abertas para extensão por meio da abstração `UsuarioValidator`.

Hoje o projeto já suporta validadores independentes como:

- `EmailUnicoValidator`
- `CPFValidation`
- `TelefoneValidation`

Novas regras podem ser adicionadas sem alterar o fluxo principal do `UsuarioService`.

## Projeto principal

O módulo ativo e mais atualizado está em:

[`springboot-aula`](./springboot-aula/README.md)

## Tecnologias

- Java 17
- Spring Boot
- Spring Web
- Spring Validation
- Spring Data JPA
- H2 Database
- JUnit 5
- Mockito
- Maven

## Status

O projeto continua sendo usado como base de estudo e evolução prática de arquitetura, testes e organização de código.
