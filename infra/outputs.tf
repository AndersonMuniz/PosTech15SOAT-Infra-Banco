output "vpc_id" {
  value = module.vpc.vpc_id
}

output "public_subnet_ids" {
  value = module.vpc.public_subnet_ids
}

output "private_subnet_ids" {
  value = module.vpc.private_subnet_ids
}

output "rds_endpoint" {
  value = module.rds.endpoint
}

output "rds_port" {
  value = module.rds.port
}

output "rds_identifier" {
  value = module.rds.identifier
}

output "rds_security_group_id" {
  value = module.rds.security_group_id
}

output "ecr_repository_url" {
  value = module.ecr.repository_url
}

output "ecr_repository_name" {
  value = module.ecr.repository_name
}