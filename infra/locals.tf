locals {
  project_name = "numberone"
  environment  = "default"

  common_tags = {
    Project     = local.project_name
    Environment = local.environment
    ManagedBy   = "Terraform"
  }
}