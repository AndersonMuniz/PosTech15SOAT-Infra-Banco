#!/usr/bin/env bash

set -e

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
INFRA_DIR="$(cd "$SCRIPT_DIR/.." && pwd)"
PROJECT_DIR="$(cd "$INFRA_DIR/.." && pwd)"

cd "$PROJECT_DIR"

echo "======================================"
echo "Removendo aplicação"
echo "======================================"

kubectl delete -f .k8s/app --ignore-not-found=true

echo
echo "Aguardando remoção do LoadBalancer..."

cd "$INFRA_DIR"

VPC_ID=$(terraform output -raw vpc_id)

echo
echo "======================================"
echo "Removendo LoadBalancer do Kubernetes"
echo "======================================"

while true
do

    LB_COUNT=$(aws elb describe-load-balancers \
        --query "length(LoadBalancerDescriptions[?VPCId=='${VPC_ID}'])" \
        --output text)

    if [ "$LB_COUNT" = "0" ]; then
        break
    fi

    echo "Ainda existem ${LB_COUNT} LoadBalancer(s)..."

    sleep 10

done

echo
echo "======================================"
echo "Removendo Security Groups do Kubernetes"
echo "======================================"

K8S_SGS=$(aws ec2 describe-security-groups \
  --filters Name=vpc-id,Values="$VPC_ID" \
            Name=group-name,Values="k8s-elb-*" \
  --query "SecurityGroups[].GroupId" \
  --output text)

if [ -n "$K8S_SGS" ]; then
  for SG in $K8S_SGS
  do
    echo "Removendo Security Group $SG"

    aws ec2 delete-security-group \
      --group-id "$SG" || true
  done
fi

echo
echo "======================================"
echo "Terraform Destroy"
echo "======================================"

terraform destroy -auto-approve