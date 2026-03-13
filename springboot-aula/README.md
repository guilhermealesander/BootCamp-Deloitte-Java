# CadastroUsuario - Spring Boot

API REST para cadastro de usuarios e agendamento de consultas com Spring Boot e banco H2.

## Estrutura

- `src/main/java/com/cadastrousuario/controller`
- `src/main/java/com/cadastrousuario/dto`
- `src/main/java/com/cadastrousuario/model`
- `src/main/java/com/cadastrousuario/repository`
- `src/main/java/com/cadastrousuario/service`
- `src/main/java/com/cadastrousuario/validation`

## Como executar

```bash
mvn spring-boot:run
```

API em `http://localhost:8080`.

## Banco de dados

- Banco configurado: H2 em arquivo local (`./data/cadastrodb`)
- Console H2: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:file:./data/cadastrodb`

## Endpoints

- `POST /api/usuarios`
- `GET /api/usuarios`
- `PATCH /api/usuarios/nome`
- `DELETE /api/usuarios`
- `POST /api/consultas`
- `POST /api/consultas/remarcar`
- `GET /api/consultas`

## Exemplo de payload

`POST /api/usuarios`

```json
{
  "nome": "Guilherme",
  "email": "guilherme@gmail.com",
  "idade": 24,
  "meses": 10,
  "endereco": "Rua Sei la, 0",
  "cpf": "529.982.247-25",
  "telefone": "(81) 99999-9999"
}
```

`POST /api/consultas`

```json
{
  "data": "2026-03-20",
  "hora": "14:00",
  "medico": "Dr. Joao",
  "especialidade": "Cardiologia"
}
```

## Teste

Teste de integracao executado com:

```bash
mvn -q -Dtest=UsuarioControllerIntegrationTest test
```
