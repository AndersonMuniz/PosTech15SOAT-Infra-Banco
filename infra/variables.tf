variable "aws_region" {
  description = "Região AWS."
  type        = string
  default     = "us-east-1"
}

variable "project_name" {
  description = "Nome usado na identificação dos recursos."
  type        = string
  default     = "numberone"
}

variable "environment" {
  description = "Ambiente da infraestrutura."
  type        = string
  default     = "dev"
}

variable "vpc_id" {
  description = "ID da VPC existente."
  type        = string
}

variable "private_subnet_ids" {
  description = "IDs de ao menos duas subnets privadas."
  type        = list(string)

  validation {
    condition     = length(var.private_subnet_ids) >= 2
    error_message = "Informe ao menos duas subnets privadas."
  }
}

variable "allowed_cidr_blocks" {
  description = "CIDRs autorizados a acessar o PostgreSQL."
  type        = list(string)
  default     = []
}

variable "db_name" {
  description = "Nome inicial do banco."
  type        = string
  default     = "numberone"
}

variable "db_username" {
  description = "Usuário administrador do banco."
  type        = string
  default     = "numberone"

  validation {
    condition     = lower(var.db_username) != "postgres"
    error_message = "O usuário postgres é reservado pelo RDS."
  }
}

variable "db_password" {
  description = "Senha do usuário administrador. Use TF_VAR_db_password em automações."
  type        = string
  sensitive   = true
}

variable "db_instance_class" {
  description = "Classe da instância RDS."
  type        = string
  default     = "db.t3.micro"
}

variable "db_allocated_storage" {
  description = "Armazenamento inicial em GB."
  type        = number
  default     = 20
}

variable "backup_retention_days" {
  description = "Quantidade de dias de retenção de backups."
  type        = number
  default     = 7
}

variable "deletion_protection" {
  description = "Protege a instância contra exclusão acidental."
  type        = bool
  default     = true
}

variable "multi_az" {
  description = "Habilita implantação Multi-AZ."
  type        = bool
  default     = false
}

variable "tags" {
  description = "Tags adicionais."
  type        = map(string)
  default     = {}
}
