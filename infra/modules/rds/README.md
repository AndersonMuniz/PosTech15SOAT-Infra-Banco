# Módulo RDS

Provisiona PostgreSQL 17 em subnets privadas, com armazenamento criptografado, Security Group dedicado, backups e proteção contra exclusão configurável.

O módulo não cria VPC nem subnets. O chamador deve fornecer `vpc_id`, `private_subnet_ids` e os CIDRs autorizados em `allowed_cidr_blocks`.
