# PosTech15SOAT

Base tecnica inicial do back-end do Tech Challenge da oficina.

Neste momento, o projeto ja possui:

- estrutura base em Spring Boot 4
- configuracao de Flyway
- autenticacao JWT para APIs administrativas
- bootstrap automatico de usuario admin local
- padrao de tratamento de erros
- documentacao OpenAPI via Swagger
- Dockerfile e docker-compose
- testes de integracao iniciais para seguranca

## Stack

- Java 25
- Spring Boot 4.0.5
- Spring Web MVC
- Spring Security
- Spring Data JPA
- Flyway
- PostgreSQL
- H2 para testes

## Estrutura inicial implementada

Pacotes principais:

- `shared/api`: health check e padrao de erro
- `shared/config`: configuracao de OpenAPI
- `shared/security`: JWT, login, bootstrap do admin e seguranca da API

## Subida local sem Docker

### 1. Banco

Suba um PostgreSQL local com:

- database: `numberone`
- username: `postgres`
- password: `postgres`

Ou sobrescreva por variavel de ambiente:

- `DB_URL`
- `DB_USERNAME`
- `DB_PASSWORD`

### 2. Rodar a aplicacao

```bash
./mvnw spring-boot:run
```

Por padrao, o profile `dev` sera usado.

## Usuario admin local

Ao subir a aplicacao pela primeira vez, um usuario admin e criado automaticamente se a tabela `admin_users` estiver vazia.

Credenciais locais padrao:

- usuario: `admin`
- senha: `admin123456`

Esses valores podem ser sobrescritos por:

- `BOOTSTRAP_ADMIN_USERNAME`
- `BOOTSTRAP_ADMIN_PASSWORD`
- `BOOTSTRAP_ADMIN_ROLE`

## JWT

O login administrativo pode ser feito em:

```text
POST /api/public/auth/login
```

Exemplo:

```json
{
  "username": "admin",
  "password": "admin123456"
}
```

O token retornado deve ser enviado como:

```text
Authorization: Bearer <token>
```

## Endpoints iniciais

Publicos:

- `GET /api/public/health`
- `POST /api/public/auth/login`

Administrativos protegidos:

- `GET /api/admin/session`

## Swagger

Com a aplicacao rodando:

```text
http://localhost:8080/swagger-ui.html
```

## Flyway

Migrations atuais:

- `V1__create_admin_users_table.sql`

## Rodando os testes

```bash
./mvnw test
```

## Subida com Docker

```bash
docker compose up --build
```

Servicos:

- app: `http://localhost:8080`
- postgres: `localhost:5432`

## Proximos passos do dominio

Esta base foi preparada para receber os modulos do time:

- client e vehicle
- servico, item e estoque
- ordem de servico e orcamento

Ou seja, a parte de infraestrutura e seguranca ja fica pronta para a evolucao dos modulos de negocio.

