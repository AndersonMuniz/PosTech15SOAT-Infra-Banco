variable "aws_region" {
  type    = string
  default = "us-east-1"
}

variable "project_name" {
  description = "Nome do projeto."
  type        = string
}

#VPC
variable "vpc_cidr" {
  description = "Bloco CIDR da VPC."
  type        = string
}

variable "availability_zones" {
  description = "Zonas de disponibilidade."
  type        = list(string)
  #TODO: adiciona validação para garantir que a quantidade de AZs seja igual a quantidade de subnets públicas e privadas, e que eks exije pelo menos 2 AZs.
}

variable "public_subnets" {
  description = "CIDRs das subnets públicas."
  type        = list(string)

  validation {
    condition     = length(var.public_subnets) <= length(var.availability_zones)
    error_message = "A quantidade de subnets públicas não pode ser maior que a quantidade de Availability Zones."
  }
}

variable "private_subnets" {
  description = "CIDRs das subnets privadas."
  type        = list(string)

  validation {
    condition     = length(var.private_subnets) <= length(var.availability_zones)
    error_message = "A quantidade de subnets privadas não pode ser maior que a quantidade de Availability Zones."
  }
}

variable "enable_nat_gateway" {
  description = "Habilita NAT Gateway."
  type        = bool
}

#EKS
variable "kubernetes_version" {
  description = "Versão do Kubernetes."
  type        = string
}

variable "node_group" {
  description = "Configuração do Node Group."

  type = object({
    instance_types = list(string)

    desired_size = number
    min_size     = number
    max_size     = number

    capacity_type = string

    disk_size = number
  })
}

variable "cluster_role_name" {
  description = "Nome da IAM Role do Cluster."
  type        = string
}

variable "node_role_name" {
  description = "Nome da IAM Role dos Nodes."
  type        = string
}

#RDS
variable "db_name" {
  description = "Nome do banco."
  type        = string
}

variable "db_username" {
  description = "Usuário administrador."
  type        = string
}

variable "db_password" {
  description = "Senha do banco."
  type        = string
  sensitive   = true
}

variable "db_instance_class" {
  description = "Classe da instância."
  type        = string
}

variable "db_allocated_storage" {
  description = "Espaço em disco."
  type        = number
}
