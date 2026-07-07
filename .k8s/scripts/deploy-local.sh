#!/bin/bash

set -e

export $(grep -v '^#' .k8s/env/local.env | xargs)

envsubst < .k8s/app/configmap.yaml | kubectl apply -f -
envsubst < .k8s/app/secret.yaml | kubectl apply -f -
envsubst < .k8s/app/deployment.yaml | kubectl apply -f -

kubectl apply -f .k8s/app/service.yaml
kubectl apply -f .k8s/app/hpa.yaml