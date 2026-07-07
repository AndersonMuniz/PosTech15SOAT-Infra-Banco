resource "aws_eks_node_group" "default" {

  cluster_name = aws_eks_cluster.this.name
  node_group_name = "${var.cluster_name}-default"
  node_role_arn = var.node_role_arn
  subnet_ids = var.subnet_ids
  instance_types = var.node_group.instance_types
  disk_size = var.node_group.disk_size
  capacity_type = var.node_group.capacity_type

  scaling_config {
    desired_size = var.node_group.desired_size
    min_size = var.node_group.min_size
    max_size = var.node_group.max_size
  }

  tags = local.common_tags

  remote_access {
    # removeremos depois se não for usar SSH
  }
}