module "vpc" {
  source = "./modules/vpc"

  project_name       = var.project_name
  vpc_cidr           = var.vpc_cidr
  availability_zones = var.availability_zones

  public_subnets  = var.public_subnets
  private_subnets = var.private_subnets

  enable_nat_gateway = var.enable_nat_gateway
}

module "eks" {

  source = "./modules/eks"
  cluster_name = "${var.project_name}-eks"
  kubernetes_version = var.kubernetes_version
  vpc_id = module.vpc.vpc_id
  subnet_ids = module.vpc.public_subnet_ids
  endpoint_public_access = true
  node_group = var.node_group
  cluster_role_arn = data.aws_iam_role.cluster.arn
  node_role_arn = data.aws_iam_role.node.arn
  tags = local.common_tags
}