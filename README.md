# PosTech15SOAT — Banco de Dados

Repositório dedicado à persistência do projeto NumberOne. Ele reúne o schema PostgreSQL, dados de apoio, adapters Java extraídos da aplicação e a infraestrutura Terraform do Amazon RDS.

## Estrutura

```text
adapters/java/       adapters de persistência por domínio
database/migrations/ migrations versionadas do schema
database/seeds/      dados opcionais para desenvolvimento
docs/                documentação operacional
infra/               Amazon RDS via Terraform
```

## Banco local

1. Copie `.env.example` para `.env` e altere as senhas.
2. Execute `docker compose up -d`.
3. Confira a saúde com `docker compose ps`.

O PostgreSQL ficará disponível em `localhost:5432`. Os scripts em `database/` são a fonte de verdade; em aplicações Spring, use Flyway apontando para `database/migrations`.

> O seed é destinado a desenvolvimento e não deve ser executado automaticamente em produção.

## AWS

O Terraform provisiona somente o RDS e seus controles de rede. Uma VPC e ao menos duas subnets privadas já devem existir.

```bash
cd infra
cp terraform.tfvars.example terraform.tfvars
terraform init
terraform plan
terraform apply
```

Consulte [docs/infraestrutura.md](docs/infraestrutura.md) e [docs/variaveis-ambiente.md](docs/variaveis-ambiente.md).

## Adapters

Os adapters mantêm os pacotes originais para facilitar sua incorporação pela aplicação Java. Eles dependem dos contratos e modelos de domínio do serviço consumidor; este repositório não compila nem publica a aplicação monolítica.
