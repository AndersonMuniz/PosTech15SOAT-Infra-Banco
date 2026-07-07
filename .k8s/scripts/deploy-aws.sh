#!/bin/bash

set -e

echo "Carregando variáveis..."
set -a
source .k8s/env/aws.env
set +a

echo "Criando namespace..."
kubectl apply -f .k8s/namespace.yaml

echo "Aplicando ConfigMap..."
envsubst < .k8s/app/configmap.yaml | kubectl apply -f -

echo "Aplicando Secret..."
envsubst < .k8s/app/secret.yaml | kubectl apply -f -

echo "Aplicando Service..."
kubectl apply -f .k8s/app/service-aws.yaml

echo "Aplicando Deployment..."
envsubst < .k8s/app/deployment.yaml | kubectl apply -f -

echo "Aplicando HPA..."
kubectl apply -f .k8s/app/hpa.yaml

echo "Deploy concluído."