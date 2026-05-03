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
- username: `admin`
- password: `admin`

Ou sobrescreva por variavel de ambiente:

- `DB_URL`
- `DB_USERNAME`
- `DB_PASSWORD`

### 2. Rodar a aplicacao

```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=local
```

O profile `local` carrega `src/main/resources/application-local.properties`, com defaults para banco local, Mailpit, JWT e usuario admin de desenvolvimento.

Sem profile ativo, a aplicacao usa `src/main/resources/application.properties`, que e a configuracao produtiva empacotada na imagem Docker. Nesse modo, valores sensiveis e dependentes do ambiente devem ser informados por variaveis de ambiente.

Alternativa:

```bash
SPRING_PROFILES_ACTIVE=local ./mvnw spring-boot:run
```

### 3. Mailpit para emails locais

Suba o Mailpit com:

```bash
docker compose up -d mailpit
```

Com a configuracao atual da aplicacao, ele ficara disponivel em:

- SMTP: `localhost:1025`
- inbox web: `http://localhost:8025`

Assim, os emails enviados pela aplicacao ficarao visiveis na interface web do Mailpit.

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
- mailpit smtp: `localhost:1025`
- mailpit web: `http://localhost:8025`

## Proximos passos do dominio

Esta base foi preparada para receber os modulos do time:

- client e vehicle
- servico, item e estoque
- ordem de servico e orcamento

Ou seja, a parte de infraestrutura e seguranca ja fica pronta para a evolucao dos modulos de negocio.

## Analise de Seguranca com SonarQube

A documentacao completa para executar a analise local de qualidade e seguranca com SonarQube esta em `doc/security/README.md`.

Fluxo resumido:

```bash
./scripts/sonar-up.sh
```

Depois:

```text
Acessar http://localhost:9000
Login inicial: admin/admin
Criar token em My Account > Security
```

Depois:

```bash
export SONAR_TOKEN=seu_token_aqui
./scripts/sonar-scan.sh
```

Depois:

```bash
./scripts/security-evidence.sh
```

Salve as evidencias em `docs/security/evidencias/` e preencha o relatorio final em `docs/security/relatorio-vulnerabilidades.md`.

## Justificativa do banco de dados relacional e da escolha do PostgreSQL

A escolha por um **banco de dados relacional** neste projeto foi feita para garantir consistencia e confiabilidade no tratamento dos dados de negocio, especialmente porque o dominio possui entidades com relacionamentos claros (como clientes, veiculos, servicos, itens, estoque e ordens de servico). Nesse contexto, o modelo relacional oferece:

- **Integridade referencial nativa** por meio de chaves primarias e estrangeiras, reduzindo risco de inconsistencias entre tabelas.
- **Transacoes ACID**, importantes para operacoes criticas (por exemplo: abertura de ordem, atualizacao de estoque e faturamento), evitando estados parciais em caso de falha.
- **Consultas estruturadas com SQL**, facilitando filtros, agregacoes e relatorios operacionais sem perda de legibilidade.
- **Evolucao controlada do schema**, alinhada ao uso de migrations com Flyway ja adotado no projeto.

Dentro desse contexto, o **PostgreSQL** foi escolhido por combinar robustez, maturidade e excelente integracao com o ecossistema Java/Spring:

- **Confiabilidade e estabilidade em producao**, sendo amplamente utilizado em sistemas corporativos.
- **Aderencia completa ao SQL e recursos avancados** (indices, constraints, views, funcoes e tipos customizados), permitindo crescimento tecnico sem trocar de tecnologia.
- **Otima integracao com Spring Data JPA e Flyway**, simplificando mapeamento de entidades, versionamento de banco e deploy continuo.
- **Bom desempenho para cargas transacionais** e capacidade de escalar verticalmente e horizontalmente conforme a necessidade do projeto.
- **Software livre e comunidade ativa**, reduzindo custo de licenciamento e facilitando suporte de longo prazo.

Em resumo, a combinacao **modelo relacional + PostgreSQL** atende tanto aos requisitos atuais de consistencia e seguranca dos dados quanto a evolucao futura da aplicacao.
