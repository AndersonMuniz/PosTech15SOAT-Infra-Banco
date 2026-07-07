# Módulo ECR

## Objetivo

Provisionar o repositório Amazon ECR usado pela imagem Docker da aplicação.

## Entradas

| Variável | Origem |
|---|---|
| `project_name` | `variables.tf` |

## Saídas

| Output | Origem |
|---|---|
| `repository_url` | `outputs.tf` |
| `repository_name` | `outputs.tf` |

## Recursos AWS Criados

- `aws_ecr_repository`
- `aws_ecr_lifecycle_policy`

## Dependências

- Provider AWS do projeto raiz
- `project_name`

## Fluxo Resumido

```text
project_name
↓
ECR Repository
↓
Lifecycle Policy
↓
Outputs
```
