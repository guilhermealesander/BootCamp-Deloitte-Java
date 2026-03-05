# 🏥 Sistema de Cadastro de Usuario e Agendamento de Consultas

![Java](https://img.shields.io/badge/Java-25-orange)
![Status](https://img.shields.io/badge/status-em%20desenvolvimento-yellow)
![Bootcamp](https://img.shields.io/badge/Bootcamp-Deloitte-green)
![GitHub](https://img.shields.io/badge/version-1.0-blue)

## 📌 Sobre o projeto

Este projeto é um **Sistema de Agendamento de Consultas Médicas** desenvolvido em **Java**, utilizando conceitos de **Programação Orientada a Objetos (POO)**.

O sistema funciona via **terminal (console)** e permite que o usuário:

- Realize cadastro
- Visualize e altere e exclua seus dados
- Agende consultas médicas
- Liste consultas agendadas
- Reagende consultas

> ⚠️ **Projeto em andamento:** novas melhorias serão adicionadas conforme a evolução do aprendizado.

---

## 🚀 Funcionalidades atuais

✔ Cadastro de usuário  
✔ Visualização dos dados cadastrados  
✔ Alteração do nome do usuário  
✔ Exclusão dos dados do usuário  
✔ Agendamento de consultas  
✔ Listagem de consultas  
✔ Reagendamento de consultas  

---

## 🧠 Conceitos aplicados

- Programação Orientada a Objetos (POO)
- Classes e Objetos
- Construtores e Métodos
- Encapsulamento (onde aplicável)
- Estruturas condicionais (`if`, `switch`)
- Estruturas de repetição (`for`)
- Coleções (`ArrayList`)
- Organização por pacotes

---

## 📂 Estrutura do projeto

```text
src
│
├── app
│   └── Main.java
│
├── entity
│   ├── DadosUsuario.java
│   └── Consulta.java
│
└── service
    ├── UsuarioService.java
    └── ConsultaService.java


📁 app

Contém a classe principal responsável pela execução do sistema e interação com o usuário.

📁 entity

Contém as entidades que representam os dados do sistema:

DadosUsuario → informações do usuário

Consulta → informações da consulta (data, hora, médico, especialidade)

📁 service

Contém a lógica de negócio do sistema (regras e operações):

UsuarioService → ações relacionadas ao usuário (exibir, alterar, excluir, etc.)

ConsultaService → ações relacionadas às consultas (marcar, listar, reagendar, etc.)


🚧 Status do projeto

⚠️ Em desenvolvimento

O projeto ainda será evoluído.
