#CLUSTER
resource "aws_security_group" "cluster" {

  name = "${var.cluster_name}-cluster-sg"
  description = "Security Group do Cluster EKS"
  vpc_id = var.vpc_id
  tags = local.common_tags
}

resource "aws_vpc_security_group_ingress_rule" "cluster_https" {

  security_group_id = aws_security_group.cluster.id
  ip_protocol = "tcp"
  from_port = 443
  to_port = 443
  cidr_ipv4 = "0.0.0.0/0"
  description = "HTTPS para API do Kubernetes"
}

resource "aws_vpc_security_group_egress_rule" "cluster" {

  security_group_id = aws_security_group.cluster.id
  ip_protocol = "-1"
  cidr_ipv4 = "0.0.0.0/0"
  description = "Saida para qualquer destino"
}

#NODE
resource "aws_security_group" "node" {
  name        = "${var.cluster_name}-node-sg"
  description = "Security Group dos Nodes do EKS"
  vpc_id      = var.vpc_id

  tags = local.common_tags
}

resource "aws_vpc_security_group_ingress_rule" "node_self" {
  security_group_id            = aws_security_group.node.id
  referenced_security_group_id = aws_security_group.node.id

  ip_protocol = "-1"
}

resource "aws_vpc_security_group_egress_rule" "node" {
  security_group_id = aws_security_group.node.id

  ip_protocol = "-1"
  cidr_ipv4   = "0.0.0.0/0"
}