# Kubernetes - NumberOne

Este diretório contém os manifests Kubernetes utilizados para executar a aplicação **NumberOne** nos ambientes **Local (Minikube)** e **AWS (Amazon EKS)**.

## Arquitetura

A infraestrutura Kubernetes utiliza os recursos abaixo:

- Namespace
- Deployment
- Service
- ConfigMap
- Secret
- Horizontal Pod Autoscaler (HPA)

O ambiente AWS utiliza o cluster Amazon EKS provisionado pelo Terraform.

---

## Estrutura

```text
.k8s/
├── app/
├── db/
├── env/
├── mailpit/
├── scripts/
├── namespace.yaml
└── README.md
```

### Diretórios

| Diretório | Descrição |
|-----------|-----------|
| `app/` | Manifests da aplicação. |
| `db/` | PostgreSQL utilizado apenas no ambiente local. |
| `env/` | Variáveis utilizadas pelos scripts de deploy. |
| `mailpit/` | Serviço SMTP para desenvolvimento local. |
| `scripts/` | Scripts de deploy para Local e AWS. |

---

## Deploy Local

No ambiente local são utilizados:

- Minikube
- Docker
- PostgreSQL em container
- Mailpit

Arquivos utilizados:

```text
app/service.yaml
db/
mailpit/
env/local.env
```

Executar:

```bash
./scripts/deploy-local.sh
```

---

## Deploy AWS

No ambiente AWS são utilizados:

- Amazon EKS
- Amazon ECR
- Amazon RDS PostgreSQL
- LoadBalancer AWS

Arquivos utilizados:

```text
app/service-aws.yaml
env/aws.env
```

Executar:

```bash
./scripts/deploy-aws.sh
```

O script realiza automaticamente:

- Terraform Apply
- Atualização do kubeconfig
- Build da imagem Docker
- Push para o Amazon ECR
- Deploy dos manifests
- Validação do deployment

---

## Scripts

```text
scripts/
├── deploy-local.sh
├── deploy-aws.sh
└── destroy-aws.sh
```

| Script | Descrição |
|---------|-----------|
| `deploy-local.sh` | Deploy da aplicação no Minikube. |
| `deploy-aws.sh` | Provisiona a infraestrutura e realiza o deploy no Amazon EKS. |
| `destroy-aws.sh` | Remove os recursos Kubernetes e destrói a infraestrutura AWS. |

---

## Validação

Verificar os pods:

```bash
kubectl get pods -n numberone
```

Verificar os serviços:

```bash
kubectl get svc -n numberone
```

Acompanhar o deployment:

```bash
kubectl rollout status deployment/numberone-api -n numberone
```

Logs da aplicação:

```bash
kubectl logs -n numberone deploy/numberone-api
```

---

## Estrutura da Aplicação

```text
Namespace
│
├── ConfigMap
├── Secret
├── Deployment
│     └── Pods
├── Service
└── HPA
```

---

## Documentação Complementar

- Infraestrutura: [`../infra/README.md`](../infra/README.md)
- Amazon EKS: [`../infra/modules/eks/README.md`](../infra/modules/eks/README.md)