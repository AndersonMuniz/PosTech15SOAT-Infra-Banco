# Infraestrutura

## Objetivo

Pasta Terraform responsável pela infraestrutura AWS do projeto NumberOne.

Recursos provisionados pelo Terraform atual:

- VPC
- Subnets públicas
- Subnets privadas
- Internet Gateway
- Route Tables
- Security Groups
- Amazon EKS
- Managed Node Group
- Amazon RDS PostgreSQL
- Amazon ECR
- Backend remoto S3

## Estrutura

```text
infra/
├── backend.tf
├── bootstrap/
├── data.tf
├── locals.tf
├── main.tf
├── modules/
│   ├── ecr/
│   ├── eks/
│   ├── rds/
│   └── vpc/
├── outputs.tf
├── providers.tf
├── terraform.tfvars
├── variables.tf
└── versions.tf
```

## Ordem de Execução

```text
bootstrap
↓
backend S3
↓
terraform init
↓
terraform plan
↓
terraform apply
↓
outputs
```

## Módulos

| Módulo | Caminho | Objetivo |
|---|---|---|
| VPC | `modules/vpc` | Rede AWS |
| EKS | `modules/eks` | Cluster Kubernetes |
| RDS | `modules/rds` | Banco PostgreSQL |
| ECR | `modules/ecr` | Registry Docker |

## Backend Terraform

Arquivo: `backend.tf`

```hcl
bucket = "backend-terraform-numberone"
key    = "infra/terraform.tfstate"
region = "us-east-1"
```

Bootstrap do backend:

```text
infra/bootstrap/
```

Bucket definido em:

```text
infra/bootstrap/terraform.tfvars
```

Valor atual:

```text
bucket_name = "backend-terraform-numberone"
```

## Providers

Arquivo: `providers.tf`

- Provider: `aws`
- Região: `var.aws_region`
- Tags padrão: `local.common_tags`

Arquivo: `versions.tf`

- Terraform: `~> 1.15`
- AWS Provider: `~> 6.0`

## tfvars

Arquivo:

```text
infra/terraform.tfvars
```

Valores configurados:

- `project_name`
- `vpc_cidr`
- `availability_zones`
- `public_subnets`
- `private_subnets`
- `enable_nat_gateway`
- `node_group`
- `cluster_role_name`
- `node_role_name`
- `kubernetes_version`
- `db_name`
- `db_username`
- `db_password`
- `db_instance_class`
- `db_allocated_storage`

## Outputs

Arquivo:

```text
infra/outputs.tf
```

Outputs existentes:

- `vpc_id`
- `public_subnet_ids`
- `private_subnet_ids`
- `rds_endpoint`
- `rds_port`
- `rds_identifier`
- `rds_security_group_id`
- `ecr_repository_url`
- `ecr_repository_name`

## Como Executar

Executar a partir da pasta:

```text
infra/
```

Inicializar:

```bash
terraform init
```

Planejar:

```bash
terraform plan
```

Aplicar:

```bash
terraform apply
```

Destruir:

```bash
terraform destroy
```

## Fluxo Resumido

```text
Terraform
↓
VPC
↓
Subnets
↓
Security Groups
↓
EKS
↓
Managed Node Group
↓
ECR
↓
RDS PostgreSQL
↓
Outputs
```
