resource "aws_eks_cluster" "this" {

  name = var.cluster_name
  role_arn = var.cluster_role_arn
  version = var.kubernetes_version
  enabled_cluster_log_types = var.enabled_cluster_log_types

  vpc_config {
    subnet_ids = var.subnet_ids
    endpoint_public_access = var.endpoint_public_access
    security_group_ids = [
      aws_security_group.cluster.id
    ]
  }

  access_config {
    authentication_mode = "API_AND_CONFIG_MAP"
    bootstrap_cluster_creator_admin_permissions = true
  }

  tags = local.common_tags
}