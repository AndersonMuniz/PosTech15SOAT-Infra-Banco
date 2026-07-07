# Módulo VPC

## Objetivo

Provisionar a rede AWS usada pelo projeto.

## Entradas

| Variável | Origem |
|---|---|
| `project_name` | `variables.tf` |
| `vpc_cidr` | `variables.tf` |
| `availability_zones` | `variables.tf` |
| `enable_nat_gateway` | `variables.tf` |
| `public_subnets` | `variables.tf` |
| `private_subnets` | `variables.tf` |

## Saídas

| Output | Origem |
|---|---|
| `vpc_id` | `outputs.tf` |
| `public_subnet_ids` | `outputs.tf` |
| `private_subnet_ids` | `outputs.tf` |

## Recursos AWS Criados

- `aws_vpc`
- `aws_subnet` públicas
- `aws_subnet` privadas
- `aws_internet_gateway`
- `aws_route_table` pública
- `aws_route_table` privada
- `aws_route_table_association` públicas
- `aws_route_table_association` privadas

## Dependências

- Provider AWS do projeto raiz
- Variáveis do projeto raiz

## Fluxo Resumido

```text
VPC
↓
Subnets públicas
↓
Subnets privadas
↓
Internet Gateway
↓
Route Tables
↓
Associations
↓
Outputs
```
