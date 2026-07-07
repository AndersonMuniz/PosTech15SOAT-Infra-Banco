resource "aws_security_group" "this" {
  name        = "${var.project_name}-rds"
  description = "RDS Security Group"
  vpc_id      = var.vpc_id
  tags = merge(
    local.common_tags,
    {
      Name = "${var.project_name}-rds-sg"
    }
  )
}

resource "aws_vpc_security_group_ingress_rule" "postgres" {
  for_each = toset(var.allowed_cidr_blocks)

  security_group_id = aws_security_group.this.id

  ip_protocol = "tcp"
  from_port   = 5432
  to_port     = 5432

  cidr_ipv4 = each.value
}

resource "aws_vpc_security_group_egress_rule" "all" {
  security_group_id = aws_security_group.this.id
  ip_protocol = "-1"
  cidr_ipv4 = "0.0.0.0/0"
}

resource "aws_db_subnet_group" "this" {
  name = "${var.project_name}-db-subnet-group"
  subnet_ids = var.private_subnet_ids
  tags = merge(
    local.common_tags,
    {
      Name = "${var.project_name}-db-subnet-group"
    }
  )
}

resource "aws_db_instance" "this" {
  identifier = "${var.project_name}-postgres"

  engine         = "postgres"
  engine_version = "17.5"

  instance_class = var.instance_class

  allocated_storage = var.allocated_storage
  storage_type      = "gp3"

  db_name  = var.db_name
  username = var.username
  password = var.password

  port = 5432

  db_subnet_group_name   = aws_db_subnet_group.this.name
  vpc_security_group_ids = [aws_security_group.this.id]

  publicly_accessible = false

  skip_final_snapshot = true
  deletion_protection = false

  backup_retention_period = 0

  multi_az = false

  tags = merge(
    local.common_tags,
    {
      Name = "${var.project_name}-postgres"
    }
  )
}