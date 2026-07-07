output "endpoint" {
  description = "Endpoint do banco."
  value       = aws_db_instance.this.address
}

output "port" {
  description = "Porta do banco."
  value       = aws_db_instance.this.port
}

output "identifier" {
  description = "Identificador do RDS."
  value       = aws_db_instance.this.identifier
}

output "db_name" {
  description = "Nome do banco."
  value       = aws_db_instance.this.db_name
}

output "security_group_id" {
  description = "Security Group do RDS."
  value       = aws_security_group.this.id
}