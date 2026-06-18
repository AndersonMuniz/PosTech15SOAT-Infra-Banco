variable "aws_region" {
  description = "Região AWS onde o RDS será provisionado."
  type        = string
}

variable "vpc_id" {
  description = "ID da VPC usada pelo EKS e pelo RDS."
  type        = string
}

variable "subnet_ids" {
  description = "Subnets privadas para o subnet group do RDS."
  type        = list(string)
}

variable "allowed_security_group_ids" {
  description = "Security groups dos nodes/pods do EKS autorizados a acessar o PostgreSQL."
  type        = list(string)
}

variable "db_name" {
  description = "Nome do banco da aplicação."
  type        = string
  default     = "numberone"
}

variable "db_username" {
  description = "Usuário administrador do RDS."
  type        = string
  sensitive   = true
}

variable "db_password" {
  description = "Senha administrador do RDS."
  type        = string
  sensitive   = true
}

variable "db_instance_class" {
  description = "Classe da instância RDS."
  type        = string
  default     = "db.t4g.micro"
}
