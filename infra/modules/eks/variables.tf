variable "cluster_name" {
  description = "Nome do cluster EKS."
  type        = string
}

variable "kubernetes_version" {
  description = "Versão do Kubernetes."
  type        = string
}

variable "vpc_id" {
  description = "ID da VPC."
  type        = string
}

variable "subnet_ids" {
  description = "IDs das subnets onde o EKS será criado."
  type        = list(string)
}

variable "endpoint_public_access" {
  description = "Permite acesso público ao endpoint do cluster."
  type        = bool
}

variable "enabled_cluster_log_types" {
  description = "Logs enviados para o CloudWatch."
  type        = list(string)

  default = [
    "api",
    "audit",
    "authenticator"
  ]
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

variable "tags" {
  description = "Tags aplicadas aos recursos."
  type        = map(string)

  default = {}
}

variable "cluster_role_arn" {
  description = "ARN da Role utilizada pelo Cluster."
  type = string
}

variable "node_role_arn" {
  description = "ARN da Role utilizada pelo Node Group."
  type = string
}