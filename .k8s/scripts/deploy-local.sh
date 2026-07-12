#!/bin/bash

set -e

kubectl apply -k .k8s/overlays/local/database
kubectl rollout status deployment/numberone-postgres -n numberone --timeout=180s

kubectl rollout status deployment/mailpit -n numberone --timeout=180s

kubectl apply -k .k8s/overlays/local/api
kubectl rollout status deployment/numberone-api -n numberone --timeout=240s
