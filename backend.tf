terraform {
  backend "s3" {
    bucket = "backend-terraform-numberone"
    key    = "infra-banco/terraform.tfstate"
    region = "us-east-1"
  }
}
