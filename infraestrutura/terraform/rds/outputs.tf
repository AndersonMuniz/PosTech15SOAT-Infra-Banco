output "rds_endpoint" {
  description = "Endpoint do RDS PostgreSQL."
  value       = aws_db_instance.numberone.endpoint
}

output "spring_datasource_url" {
  description = "URL JDBC para configurar SPRING_DATASOURCE_URL."
  value       = "jdbc:postgresql://${aws_db_instance.numberone.address}:${aws_db_instance.numberone.port}/${var.db_name}"
}
