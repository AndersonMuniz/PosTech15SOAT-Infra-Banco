project_name = "numberone"

#VPC
vpc_cidr = "10.0.0.0/16"
availability_zones = [
  "us-east-1a",
  "us-east-1b"
]
public_subnets = [
  "10.0.1.0/24",
  "10.0.2.0/24"
]
private_subnets = [
  "10.0.11.0/24",
  "10.0.12.0/24"
]
enable_nat_gateway = false

#EKS
node_group = {
  instance_types = ["t3.small"]

  desired_size = 2
  min_size     = 2
  max_size     = 4

  capacity_type = "ON_DEMAND"

  disk_size = 20
}

cluster_role_name = "c213429a5396203l15765906t1w949294-LabEksClusterRole-aql9Zb50CHUM"
node_role_name = "c213429a5396203l15765906t1w949294083-LabEksNodeRole-gyTcZ1PhKaKc"
kubernetes_version = "1.36"

#RDS
db_name = "numberone"
db_username = "numberone"
db_password = "SenhaMuitoSegura123!"
db_instance_class = "db.t3.micro"
db_allocated_storage = 20