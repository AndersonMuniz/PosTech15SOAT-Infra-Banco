# Infraestrutura

Este diretório contém toda a infraestrutura como código (Infrastructure as Code - IaC) do projeto, utilizando Terraform para provisionamento de recursos na AWS.

## Objetivo

Provisionar de forma automatizada toda a infraestrutura necessária para execução da aplicação, seguindo boas práticas de organização, modularização e reutilização de código.

Inicialmente serão provisionados os seguintes recursos:

* Amazon VPC
* Amazon EKS
* Amazon RDS PostgreSQL
* Application Load Balancer (ALB)
* Recursos de rede (Subnets, Route Tables, Internet Gateway e Security Groups)

## Estrutura do projeto

```text
infra/
├── bootstrap/
├── modules/
├── docs/
├── diagrams/
├── versions.tf
├── providers.tf
├── variables.tf
├── locals.tf
├── outputs.tf
└── main.tf
```

### bootstrap

Projeto Terraform independente responsável por provisionar a infraestrutura base necessária para execução do projeto principal.

Inicialmente será responsável apenas pela criação do bucket S3 utilizado como backend remoto do Terraform.

### modules

Contém módulos reutilizáveis da infraestrutura.

Cada módulo possui responsabilidade única e encapsula a implementação de um determinado componente da arquitetura.

Exemplos:

* VPC
* EKS
* RDS
* ALB

## Pré-requisitos

* Terraform 1.15 ou superior dentro da major 1
* AWS CLI v2
* Conta AWS com permissões para provisionar recursos

## Convenções adotadas

* Provider AWS definido apenas no projeto raiz.
* Módulos desacoplados e reutilizáveis.
* Versionamento do arquivo `.terraform.lock.hcl`.
* Código formatado utilizando `terraform fmt`.
* Validação utilizando `terraform validate`.
* Análise estática utilizando TFLint.

## Status da implementação

* [x] Estrutura inicial do projeto
* [x] Configuração do Terraform
* [x] Configuração do Provider AWS
* [ ] Bootstrap
* [ ] Backend remoto (S3)
* [ ] VPC
* [ ] EKS
* [ ] RDS
* [ ] ALB

-------------
evoluções

O bootstrap é executado apenas para criar ou atualizar a infraestrutura necessária ao backend (S3). Seu estado permanece local por simplicidade, evitando dependência circular.

