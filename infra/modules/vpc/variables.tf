variable "project_name" {
  description = "Nome do projeto."
  type        = string
}

variable "vpc_cidr" {
  description = "Bloco CIDR da VPC."
  type        = string
}

variable "availability_zones" {
  description = "Lista de zonas de disponibilidade."
  type        = list(string)
}

variable "enable_nat_gateway" {
  description = "Habilita NAT Gateway."
  type        = bool
  default     = false
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
