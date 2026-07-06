locals {
  common_tags = {
    Project     = var.project_name
    ManagedBy   = "Terraform"
    Environment = "default"
  }

  vpc_name = "${var.project_name}-vpc"

  public_subnets_map = {
    for i, cidr in var.public_subnets :
    i => {
      cidr_block = cidr
      az         = var.availability_zones[i]
    }
  }

  private_subnets_map = {
    for i, cidr in var.private_subnets :
    i => {
      cidr_block = cidr
      az         = var.availability_zones[i]
    }
  }
}
