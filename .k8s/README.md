# Kubernetes - NumberOne

Este diretorio contem os manifests Kubernetes da aplicacao **NumberOne** usando **Kustomize** para separar a configuracao comum das diferencas entre ambientes.

## Ideia principal

O Kustomize evita manter manifests duplicados para Local e AWS.

- `base/`: recursos comuns e reutilizaveis.
- `overlays/`: ajustes especificos de cada ambiente.

Assim, a API, banco e Mailpit ficam descritos uma vez na base, e cada ambiente altera apenas o necessario.

## Estrutura

```text
.k8s/
|-- base/
|   |-- namespace/
|   |-- app/
|   |-- database/
|   `-- mailpit/
|-- overlays/
|   |-- local/
|   |   |-- api/
|   |   |-- database/
|   |   `-- full/
|   `-- aws/
|       `-- api/
|-- env/
|-- scripts/
`-- README.md
```

## Bases

| Base | Conteudo |
| --- | --- |
| `base/namespace` | Namespace `numberone`. |
| `base/app` | ConfigMap, Secret, Service, Deployment e HPA da API. |
| `base/database` | PostgreSQL local, PVC, Service, ConfigMap e Secret. |
| `base/mailpit` | Mailpit local para captura de emails. |

## Overlays

| Overlay | Uso |
| --- | --- |
| `overlays/local/database` | Deploy local do PostgreSQL e Mailpit. Usado pelo workflow separado do banco. |
| `overlays/local/api` | Deploy local da API no Minikube. Usado pelo workflow separado da API. |
| `overlays/local/full` | Deploy local completo para uso manual. |
| `overlays/aws/api` | Deploy da API no EKS, usando RDS e imagem publicada no ECR. |

## Deploy Local

Deploy do banco e Mailpit:

```bash
kubectl apply -k .k8s/overlays/local/database
kubectl rollout status deployment/numberone-postgres -n numberone
kubectl rollout status deployment/mailpit -n numberone
```

Deploy da API:

```bash
kubectl apply -k .k8s/overlays/local/api
kubectl rollout status deployment/numberone-api -n numberone
```

Deploy local completo:

```bash
kubectl apply -k .k8s/overlays/local/full
```

Tambem existe o script:

```bash
.k8s/scripts/deploy-local.sh
```

## Deploy AWS

O overlay AWS contem placeholders como `${APP_IMAGE}`, `${DB_URL}`, `${DB_HOST}` e `${JWT_SECRET}`.

Esses valores sao preenchidos pelo script:

```text
infra/scripts/deploy-aws.sh
```

O script executa:

```bash
kubectl kustomize .k8s/overlays/aws/api | envsubst | kubectl apply -f -
```

Na AWS, o banco de dados fica fora do Kubernetes, provisionado como RDS pelo Terraform.

## Validacao

Validar os overlays sem aplicar no cluster:

```bash
kubectl apply -k .k8s/overlays/local/database --dry-run=client --validate=false
kubectl apply -k .k8s/overlays/local/api --dry-run=client --validate=false
kubectl apply -k .k8s/overlays/local/full --dry-run=client --validate=false
kubectl apply -k .k8s/overlays/aws/api --dry-run=client --validate=false
```

Verificar recursos:

```bash
kubectl get pods -n numberone
kubectl get svc -n numberone
kubectl get hpa -n numberone
```

## Documentacao Complementar

- Infraestrutura: [`../infra/README.md`](../infra/README.md)
- Amazon EKS: [`../infra/modules/eks/README.md`](../infra/modules/eks/README.md)
- CI/CD local com runner self-hosted: [`../doc/cicd/execucao-local-github-actions.md`](../doc/cicd/execucao-local-github-actions.md)
