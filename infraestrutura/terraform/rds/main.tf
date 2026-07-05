provider "aws" {
  region = var.aws_region
}

resource "aws_db_subnet_group" "numberone" {
  name       = "numberone-rds-subnet-group"
  subnet_ids = var.subnet_ids

  tags = {
    Name = "numberone-rds-subnet-group"
  }
}

resource "aws_security_group" "rds" {
  name        = "numberone-rds-sg"
  description = "Permite acesso PostgreSQL a partir do EKS"
  vpc_id      = var.vpc_id

  ingress {
    description     = "PostgreSQL a partir do EKS"
    from_port       = 5432
    to_port         = 5432
    protocol        = "tcp"
    security_groups = var.allowed_security_group_ids
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }

  tags = {
    Name = "numberone-rds-sg"
  }
}

resource "aws_db_instance" "numberone" {
  identifier                 = "numberone-postgres"
  engine                     = "postgres"
  engine_version             = "16"
  instance_class             = var.db_instance_class
  allocated_storage          = 20
  max_allocated_storage      = 100
  storage_type               = "gp3"
  db_name                    = var.db_name
  username                   = var.db_username
  password                   = var.db_password
  db_subnet_group_name       = aws_db_subnet_group.numberone.name
  vpc_security_group_ids     = [aws_security_group.rds.id]
  publicly_accessible        = false
  backup_retention_period    = 7
  auto_minor_version_upgrade = true
  deletion_protection        = true
  skip_final_snapshot        = false
  final_snapshot_identifier  = "numberone-postgres-final-snapshot"

  tags = {
    Name = "numberone-postgres"
  }
}
