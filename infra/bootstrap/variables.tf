variable "aws_region" {
  description = "Região da AWS onde o bucket será criado."
  type        = string
  default     = "us-east-1"
}

variable "bucket_name" {
  description = "Nome do bucket S3 para backend do Terraform."
  type        = string
}