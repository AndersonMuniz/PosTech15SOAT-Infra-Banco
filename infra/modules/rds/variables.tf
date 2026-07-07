variable "project_name" {
  description = "Nome do projeto."
  type        = string
}

variable "vpc_id" {
  description = "ID da VPC."
  type        = string
}

variable "private_subnet_ids" {
  description = "Subnets privadas."
  type        = list(string)
}

variable "db_name" {
  description = "Nome do banco."
  type        = string
}

variable "username" {
  description = "Usuário administrador."
  type        = string

  validation {
    condition     = lower(var.username) != "admin"
    error_message = "O usuário 'admin' é reservado pelo PostgreSQL no Amazon RDS."
  }
}

variable "password" {
  description = "Senha do banco."
  type        = string
  sensitive   = true
}

variable "instance_class" {
  description = "Classe da instância."
  type        = string
}

variable "allocated_storage" {
  description = "Armazenamento em GB."
  type        = number
}

variable "allowed_cidr_blocks" {
  description = "CIDRs autorizados a acessar o banco."
  type        = list(string)
}