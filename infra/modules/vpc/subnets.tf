resource "aws_subnet" "public" {

  for_each = local.public_subnets_map
  vpc_id   = aws_vpc.this.id

  cidr_block              = each.value.cidr_block
  availability_zone       = each.value.az
  map_public_ip_on_launch = true

  tags = merge(
    local.common_tags,
    {
      Name = "${var.project_name}-public-${each.value.az}"
      Type = "public"
    }
  )
}

resource "aws_subnet" "private" {

  for_each = local.private_subnets_map
  vpc_id   = aws_vpc.this.id

  cidr_block              = each.value.cidr_block
  availability_zone       = each.value.az
  map_public_ip_on_launch = false

  tags = merge(
    local.common_tags,
    {
      Name = "${var.project_name}-private-${each.value.az}"
      Type = "private"
    }
  )
}
