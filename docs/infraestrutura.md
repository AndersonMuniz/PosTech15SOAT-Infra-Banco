# Infraestrutura do banco

O diretório `infra/` cria uma instância PostgreSQL 17 no Amazon RDS, um DB Subnet Group e um Security Group dedicado.

## Pré-requisitos

- Terraform 1.6 ou superior;
- credenciais AWS configuradas;
- uma VPC existente;
- duas ou mais subnets privadas em zonas de disponibilidade distintas;
- CIDRs autorizados a alcançar a porta 5432.

Copie `terraform.tfvars.example` para `terraform.tfvars` e preencha valores reais. A senha é sensível e não deve ser gravada no repositório. Para automação, prefira `TF_VAR_db_password`.

O banco é privado, criptografado, possui backups configuráveis e proteção contra exclusão habilitada por padrão. O output `db_endpoint` fornece o host sem expor credenciais.
