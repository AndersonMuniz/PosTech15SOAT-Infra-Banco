output "db_endpoint" {
  description = "Endpoint DNS do PostgreSQL."
  value       = module.rds.endpoint
}

output "db_port" {
  description = "Porta do PostgreSQL."
  value       = module.rds.port
}

output "db_identifier" {
  description = "Identificador da instância RDS."
  value       = module.rds.identifier
}

output "db_security_group_id" {
  description = "Security Group associado ao RDS."
  value       = module.rds.security_group_id
}
