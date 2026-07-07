# Módulo RDS

## Objetivo

Provisionar o Amazon RDS PostgreSQL usado pela aplicação.

## Entradas

| Variável | Origem |
|---|---|
| `project_name` | `variables.tf` |
| `vpc_id` | `variables.tf` |
| `private_subnet_ids` | `variables.tf` |
| `db_name` | `variables.tf` |
| `username` | `variables.tf` |
| `password` | `variables.tf` |
| `instance_class` | `variables.tf` |
| `allocated_storage` | `variables.tf` |
| `allowed_cidr_blocks` | `variables.tf` |

## Saídas

| Output | Origem |
|---|---|
| `endpoint` | `outputs.tf` |
| `port` | `outputs.tf` |
| `identifier` | `outputs.tf` |
| `db_name` | `outputs.tf` |
| `security_group_id` | `outputs.tf` |

## Recursos AWS Criados

- `aws_security_group`
- `aws_vpc_security_group_ingress_rule`
- `aws_vpc_security_group_egress_rule`
- `aws_db_subnet_group`
- `aws_db_instance`

## Dependências

- VPC
- Subnets privadas
- CIDRs autorizados

## Fluxo Resumido

```text
VPC
↓
Subnets privadas
↓
Security Group
↓
DB Subnet Group
↓
RDS PostgreSQL
↓
Outputs
```
