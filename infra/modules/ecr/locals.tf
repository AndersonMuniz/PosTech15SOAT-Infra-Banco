locals {
  common_tags = {
    Project   = var.project_name
    ManagedBy = "Terraform"
  }

  repository_name = "${var.project_name}-api"
}