#!/usr/bin/env bash

set -e

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
INFRA_DIR="$(cd "$SCRIPT_DIR/.." && pwd)"
PROJECT_DIR="$(cd "$INFRA_DIR/.." && pwd)"

cd "$INFRA_DIR"

echo "======================================"
echo "Terraform Init"
echo "======================================"
terraform init

echo
echo "======================================"
echo "Terraform Apply"
echo "======================================"
terraform apply -auto-approve

echo
echo "======================================"
echo "Obtendo Outputs"
echo "======================================"

AWS_REGION=$(terraform output -raw aws_region)
CLUSTER_NAME=$(terraform output -raw cluster_name)
ECR_URL=$(terraform output -raw ecr_repository_url)
RDS_ENDPOINT=$(terraform output -raw rds_endpoint)
RDS_PORT=$(terraform output -raw rds_port)

echo
echo "======================================"
echo "Atualizando kubeconfig"
echo "======================================"

aws eks update-kubeconfig \
    --region "$AWS_REGION" \
    --name "$CLUSTER_NAME"

echo
echo "======================================"
echo "Login no ECR"
echo "======================================"

aws ecr get-login-password \
| docker login \
    --username AWS \
    --password-stdin "$ECR_URL"

IMAGE_TAG=$(date +%Y%m%d%H%M%S)

APP_IMAGE="${ECR_URL}:${IMAGE_TAG}"

echo
echo "======================================"
echo "Build Docker"
echo "======================================"

cd "$PROJECT_DIR"

docker build \
    -t numberone-api:"$IMAGE_TAG" .

docker tag \
    numberone-api:"$IMAGE_TAG" \
    "$APP_IMAGE"

docker push "$APP_IMAGE"

echo
echo "======================================"
echo "Carregando aws.env"
echo "======================================"

# Carrega apenas credenciais e configurações da aplicação.
# Valores de infraestrutura (RDS, ECR, Cluster) vêm do Terraform.
set -a
source .k8s/env/aws.env
set +a

export APP_IMAGE
export DB_HOST="$RDS_ENDPOINT"
export DB_URL="jdbc:postgresql://${RDS_ENDPOINT}:${RDS_PORT}/numberone"

required_vars=(
  APP_IMAGE
  DB_HOST
  DB_URL
  DB_USERNAME
  DB_PASSWORD
  JWT_SECRET
  BOOTSTRAP_ADMIN_USERNAME
  BOOTSTRAP_ADMIN_PASSWORD
  SPRING_PROFILES_ACTIVE
  MAIL_HOST
)

for var in "${required_vars[@]}"; do
  if [ -z "${!var}" ]; then
    echo "Erro: variável $var não definida."
    exit 1
  fi
done

echo
echo "======================================"
echo "Aplicando Kubernetes"
echo "======================================"

kubectl kustomize .k8s/overlays/aws/api \
| envsubst \
| kubectl apply -f -

echo
echo "======================================"
echo "Deploy realizado com sucesso!"
echo "======================================"

kubectl get svc -n numberone

echo
echo "======================================"
echo "Aguardando Deployment..."
echo "======================================"

if ! kubectl rollout status deployment/numberone-api \
    -n numberone \
    --timeout=5m; then

    echo
    echo "ERRO: Deployment não ficou pronto."
    echo
    echo "Verifique:"
    echo "kubectl get pods -n numberone"
    echo "kubectl describe pod -n numberone -l app=numberone-api"
    echo "kubectl logs deployment/numberone-api -n numberone"

    exit 1
fi

echo
echo "======================================"
echo "Aguardando LoadBalancer..."
echo "======================================"

HOSTNAME=""

for i in {1..60}; do
    HOSTNAME=$(kubectl get svc numberone-api-service \
        -n numberone \
        -o jsonpath='{.status.loadBalancer.ingress[0].hostname}')

    if [ -n "$HOSTNAME" ]; then
        break
    fi

    sleep 5
done

if [ -z "$HOSTNAME" ]; then
    echo
    echo "ERRO: LoadBalancer não recebeu DNS."
    echo
    echo "Verifique:"
    echo "kubectl get svc -n numberone"

    exit 1
fi

API_URL="http://${HOSTNAME}"

echo
echo "======================================"
echo "Validando Health Check..."
echo "======================================"

for i in {1..24}; do

    if curl -fs "${API_URL}/api/public/health" >/dev/null 2>&1; then

        echo
        echo "======================================"
        echo "Deploy realizado com sucesso!"
        echo "======================================"
        echo
        echo "API:"
        echo "${API_URL}"
        echo
        echo "Swagger:"
        echo "${API_URL}/swagger-ui/index.html"
        echo
        echo "Health:"
        echo "${API_URL}/api/public/health"

        exit 0
    fi

    sleep 5

done

echo
echo "ERRO: API não respondeu ao Health Check."

echo
echo "Verifique:"
echo "kubectl get pods -n numberone"
echo "kubectl logs deployment/numberone-api -n numberone"
echo "kubectl describe pod -n numberone -l app=numberone-api"

exit 1
