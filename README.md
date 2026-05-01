# PosTech15SOAT - NumberOne

API REST do Tech Challenge Fase 1 para gerenciamento de uma oficina mecanica. O projeto cobre cadastro de clientes e veiculos, catalogo de servicos automotivos, estoque de pecas e insumos, ordem de servico, orcamento, acompanhamento, seguranca JWT, Swagger e execucao com Docker.

## Stack

- Java 25
- Spring Boot 4.0.5
- Spring Web MVC
- Spring Security
- Spring Data JPA
- Flyway
- PostgreSQL
- Mailpit
- Docker e Docker Compose
- H2 para testes

## Modulos

- `customer`: cadastro de clientes, documento, tipo de documento e validacoes.
- `vehicle`: cadastro de veiculos, placa, marca, modelo, ano e vinculo com cliente.
- `automotiveservice`: catalogo de servicos automotivos, valor base e tempo estimado.
- `inventory`: cadastro de itens de estoque e movimentacoes de entrada, baixa e ajuste.
- `serviceorder`: ordem de servico, diagnostico, orcamento, itens, insumos, status e acompanhamento.
- `shared`: seguranca JWT, tratamento global de erros, Swagger, email e configuracoes comuns.

## Como Rodar com Um Comando

Pre-requisitos:

- Docker instalado
- Docker Compose instalado

Na raiz do projeto:

```bash
./executar-projeto.sh
```

Esse comando executa `docker compose up --build` e sobe a aplicacao, o banco PostgreSQL e o Mailpit.

## Como Rodar Manualmente com Docker

```bash
docker compose up --build
```

Servicos:

- API: `http://localhost:8080`
- Swagger: `http://localhost:8080/swagger-ui.html`
- PostgreSQL: `localhost:5432`
- Mailpit SMTP: `localhost:1025`
- Mailpit Web: `http://localhost:8025`

Para parar:

```bash
docker compose down
```

Para parar e apagar o volume do banco:

```bash
docker compose down -v
```

Mais detalhes em `doc/execucao-local.md`.

## Como Rodar Sem Docker para a Aplicacao

Suba apenas infraestrutura:

```bash
docker compose up -d postgres mailpit
```

Rode a aplicacao localmente:

```bash
./mvnw spring-boot:run
```

Configuracao padrao:

- database: `numberone`
- username: `admin`
- password: `admin`
- JDBC: `jdbc:postgresql://localhost:5432/numberone`

## Autenticacao JWT

Ao subir a aplicacao, um usuario administrativo e criado automaticamente caso a tabela `admin_users` esteja vazia.

Credenciais locais:

- usuario: `admin`
- senha: `admin123456`

Login:

```text
POST /api/public/auth/login
```

Body:

```json
{
  "username": "admin",
  "password": "admin123456"
}
```

Use o token retornado nas rotas administrativas:

```text
Authorization: Bearer <token>
```

## Swagger

Com a aplicacao rodando:

```text
http://localhost:8080/swagger-ui.html
```

O Swagger usa o esquema `bearerAuth`. Para testar rotas administrativas, faca login, copie o `accessToken`, clique em `Authorize` e informe:

```text
Bearer <token>
```

## Endpoints Principais

Publicos:

- `GET /api/public/health`
- `POST /api/public/auth/login`
- `GET /api/public/ordens-servico/{id}/acompanhamento`
- `GET /api/public/orcamentos-ordem-servico/{id}/aprovacao/aprovar`
- `GET /api/public/orcamentos-ordem-servico/{id}/aprovacao/rejeitar`

Administrativos:

- `GET /api/admin/session`
- `POST /api/admin/clientes`
- `GET /api/admin/clientes`
- `POST /api/admin/veiculos`
- `GET /api/admin/veiculos`
- `POST /api/servicos`
- `GET /api/servicos`
- `POST /api/itens`
- `GET /api/itens`
- `POST /api/estoque/entrada`
- `POST /api/estoque/baixa`
- `POST /api/estoque/ajuste`
- `POST /api/admin/ordens-servico`
- `GET /api/admin/ordens-servico`
- `POST /api/admin/ordens-servico/{serviceOrderId}/orcamentos`
- `PATCH /api/admin/orcamentos-ordem-servico/{id}/solicitar-aprovacao`

## Flyway

As migrations ficam em:

```text
src/main/resources/db/migrations
```

O Flyway roda automaticamente na subida da aplicacao e cria/atualiza as tabelas no PostgreSQL.

## Testes

```bash
./mvnw test
```

O projeto possui testes de integracao e testes de servico para os principais modulos.

## Documentacao do Projeto

- `doc/equipe/modelagem-banco-aprovada.md`: decisoes de modelagem do banco.
- `doc/equipe/*.md`: divisao de tarefas por integrante.
- `doc/modulos/*.md`: documentacao dos modulos de estoque e servicos.
- `doc/execucao-local.md`: passo a passo de execucao automatica e manual.
- `doc/padroes-java-25.md`: padroes de codigo Java definidos pelo grupo.
