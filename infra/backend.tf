terraform {
  backend "s3" {
    bucket = "backend-terraform-numberone"
    key    = "infra/terraform.tfstate"
    region = "us-east-1"
  }
}