# CadastroUsuario - Spring Boot

Migracao do app Java de console para API REST com Spring Boot e persistencia em banco H2.

## Estrutura atual

- `src/app`, `src/entity`, `src/service`: versao legada (console).
- `src/main/java/com/cadastrousuario`: nova versao Spring Boot.

## Como executar

```bash
mvn spring-boot:run
```

API sobe em `http://localhost:8080`.

## Banco de dados

- Banco configurado: H2 em arquivo local (`./data/cadastrodb`)
- Console H2: `http://localhost:8080/h2-console`
- JDBC URL no console: `jdbc:h2:file:./data/cadastrodb`

## Endpoints

- `POST /api/usuarios` cria um usuario (o mais recente vira o usuario atual da API)
- `GET /api/usuarios` consulta usuario atual
- `PATCH /api/usuarios/nome` altera nome
- `DELETE /api/usuarios` exclui usuario e limpa consultas
- `POST /api/consultas` marca consulta
- `POST /api/consultas/remarcar` remarcar (adiciona nova consulta)
- `GET /api/consultas` lista consultas

### Exemplo de payload

`POST /api/usuarios`

```json
{
  "nome": "Maria Silva",
  "email": "maria@email.com",
  "idade": 29,
  "endereco": "Rua A, 100",
  "telefone": "85999999999"
}
```

`POST /api/consultas`

```json
{
  "data": "2026-03-10",
  "hora": "14:30",
  "medico": "Dr. Joao",
  "especialidade": "Cardiologia"
}
```
