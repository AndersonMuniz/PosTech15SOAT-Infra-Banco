# Módulo RDS

Responsável por provisionar o banco de dados PostgreSQL utilizado pela aplicação.

## Recursos Criados

O módulo cria os seguintes recursos:

- Amazon RDS PostgreSQL
- DB Subnet Group
- Security Group do banco
- Regras de acesso ao banco

---

## Estrutura

```text
modules/rds/
├── main.tf
├── variables.tf
├── outputs.tf
└── README.md
```

---

## Variáveis

| Variável | Descrição |
|----------|-----------|
| `project_name` | Nome do projeto |
| `vpc_id` | VPC onde o banco será criado |
| `private_subnet_ids` | Subnets privadas |
| `db_name` | Nome do banco |
| `username` | Usuário administrador |
| `password` | Senha do banco |
| `instance_class` | Tipo da instância |
| `allocated_storage` | Espaço em disco |
| `allowed_cidr_blocks` | CIDRs autorizados |

---

## Outputs

| Output | Descrição |
|----------|-----------|
| `endpoint` | Endpoint do banco |
| `port` | Porta |
| `identifier` | Nome da instância |
| `db_name` | Nome do banco |
| `security_group_id` | Security Group do banco |

---

## Fluxo

```text
Private Subnets
        │
DB Subnet Group
        │
Security Group
        │
Amazon RDS
```

---

## Exemplo de Utilização

```hcl
module "rds" {
  source = "./modules/rds"

  project_name       = var.project_name
  vpc_id             = module.vpc.vpc_id
  private_subnet_ids = module.vpc.private_subnet_ids

  db_name            = var.db_name
  username           = var.db_username
  password           = var.db_password
  instance_class     = var.db_instance_class
  allocated_storage  = var.db_allocated_storage
}
```

---

## Observações

- O banco é criado em subnets privadas.
- Atualmente a instância utiliza **Single-AZ**.
- O acesso é controlado por Security Groups.