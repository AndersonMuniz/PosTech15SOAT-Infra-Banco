# Módulo EKS

## Objetivo

Provisionar o cluster Amazon EKS e o Managed Node Group.

## Entradas

| Variável | Origem |
|---|---|
| `cluster_name` | `variables.tf` |
| `kubernetes_version` | `variables.tf` |
| `vpc_id` | `variables.tf` |
| `subnet_ids` | `variables.tf` |
| `endpoint_public_access` | `variables.tf` |
| `enabled_cluster_log_types` | `variables.tf` |
| `node_group` | `variables.tf` |
| `tags` | `variables.tf` |
| `cluster_role_arn` | `variables.tf` |
| `node_role_arn` | `variables.tf` |

## Saídas

| Output | Origem |
|---|---|
| `cluster_name` | `outputs.tf` |
| `cluster_endpoint` | `outputs.tf` |
| `cluster_certificate_authority` | `outputs.tf` |
| `node_security_group_id` | `outputs.tf` |

## Recursos AWS Criados

- `aws_eks_cluster`
- `aws_eks_node_group`
- `aws_security_group` do cluster
- `aws_security_group` dos nodes
- `aws_vpc_security_group_ingress_rule`
- `aws_vpc_security_group_egress_rule`

## Dependências

- VPC
- Subnets
- IAM Role do cluster
- IAM Role dos nodes

## Fluxo Resumido

```text
VPC/Subnets
↓
Security Groups
↓
EKS Cluster
↓
Managed Node Group
↓
Outputs
```
