data "aws_iam_role" "cluster" {
  name = var.cluster_role_name
}

data "aws_iam_role" "node" {
  name = var.node_role_name
}