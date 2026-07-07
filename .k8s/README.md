# Kubernetes - NumberOne

Este guia mostra como subir a aplicacao NumberOne em Kubernetes nos modos LOCAL e AWS.

## Estrutura

```text
.k8s/
├── namespace.yaml
├── app/
│   ├── configmap.yaml
│   ├── deployment.yaml
│   ├── hpa.yaml
│   ├── secret.yaml
│   ├── service.yaml
│   └── service-aws.yaml
├── db/
│   ├── configmap.yaml
│   ├── deployment.yaml
│   ├── pvc.yaml
│   ├── secret.yaml
│   └── service.yaml
├── env/
│   ├── aws.env
│   └── local.env
├── mailpit/
│   ├── deployment.yaml
│   └── service.yaml
└── scripts/
    ├── deploy-aws.sh
    └── deploy-local.sh
```

## Arquitetura

| Modo | Componentes |
|---|---|
| LOCAL | Docker, Minikube, PostgreSQL Container, Mailpit, Imagem Local |
| AWS | EKS, RDS, ECR, ELB, Imagem no ECR |

### LOCAL

```text
Docker
↓
Minikube
↓
Imagem Local
↓
PostgreSQL Container
↓
Mailpit
↓
Service ClusterIP
```

Arquivos usados:

- `.k8s/env/local.env`
- `.k8s/app/service.yaml`
- `.k8s/db/*`
- `.k8s/mailpit/*`
- `.k8s/app/*`

### AWS

```text
Amazon ECR
↓
Amazon EKS
↓
Amazon RDS PostgreSQL
↓
Service LoadBalancer
↓
ELB AWS
```

Arquivos usados:

- `.k8s/env/aws.env`
- `.k8s/app/service-aws.yaml`
- `.k8s/app/configmap.yaml`
- `.k8s/app/secret.yaml`
- `.k8s/app/deployment.yaml`
- `.k8s/app/hpa.yaml`

## Fluxo de Deploy

```text
Terraform
↓
Docker
↓
ECR
↓
Kubernetes
↓
Pods
↓
Service
↓
LoadBalancer
↓
Aplicação
```

## Execução Local

### O que Sobe no Cluster

- `numberone-api`: API Spring Boot.
- `numberone-postgres`: banco PostgreSQL.
- `mailpit`: servidor SMTP/Web para testes de e-mail.
- `numberone-api-service`: Service interno da API.
- `numberone-postgres-service`: Service interno do banco.
- `numberone-api-hpa`: autoscaling horizontal da API.
- `numberone-postgres-data`: volume persistente do PostgreSQL.

### Pre-requisitos

- Docker Desktop rodando.
- Minikube instalado.
- Kubectl instalado.
- Estar na raiz do projeto.

Valide:

```powershell
docker version
minikube version
kubectl version --client
```

### 1. Iniciar o Minikube

```powershell
minikube start --driver=docker
kubectl config use-context minikube
```

Confirme:

```powershell
minikube status
kubectl get nodes
```

### 2. Buildar a Imagem da API

Como o ambiente LOCAL usa:

```text
APP_IMAGE=numberone-api:latest
```

o build precisa acontecer dentro do ambiente Docker do Minikube.

No PowerShell:

```powershell
& minikube -p minikube docker-env --shell powershell | Invoke-Expression
docker build -t numberone-api:latest .
```

Confirme que a imagem existe no Minikube:

```powershell
docker images numberone-api
```

Para voltar o terminal para o Docker Desktop depois:

```powershell
& minikube -p minikube docker-env --shell powershell --unset | Invoke-Expression
```

### 3. Aplicar os Manifests Locais

Crie primeiro o namespace:

```powershell
kubectl apply -f .k8s/namespace.yaml
```

Depois aplique os recursos:

```powershell
kubectl apply -f .k8s/db
kubectl apply -f .k8s/mailpit
kubectl apply -f .k8s/app
```

Tambem pode usar o modo recursivo depois que o namespace ja existe:

```powershell
kubectl apply -f .k8s --recursive
```

Script local existente:

```bash
.k8s/scripts/deploy-local.sh
```

### 4. Acompanhar a Subida dos Pods

```powershell
kubectl get pods -n numberone -w
```

O estado esperado no final:

```text
mailpit                  1/1 Running
numberone-api            1/1 Running
numberone-api            1/1 Running
numberone-postgres       1/1 Running
```

Para sair do modo de acompanhamento:

```text
Ctrl + C
```

Esse comando para apenas o acompanhamento no terminal. Ele nao derruba os pods.

### 5. Entender o Init Container da API

A API possui um `initContainer` chamado `wait-for-postgres`.

Ele espera o Postgres responder na porta `5432` antes de iniciar o Spring Boot.

Para ver o log do init container:

```powershell
kubectl logs -n numberone <nome-do-pod-api> -c wait-for-postgres
```

### 6. Validar ConfigMap e Secret

Ver o ConfigMap da API:

```powershell
kubectl describe configmap numberone-api-config -n numberone
```

Ver algumas variaveis dentro do pod:

```powershell
kubectl exec -n numberone deploy/numberone-api -- printenv DB_URL
kubectl exec -n numberone deploy/numberone-api -- printenv MAIL_HOST
```

Nao imprima secrets sensiveis em ambientes compartilhados.

### 7. Testar a API Localmente

Abra um terminal e rode:

```powershell
kubectl port-forward -n numberone svc/numberone-api-service 8080:80
```

Deixe esse terminal aberto.

Em outro terminal:

```powershell
curl http://localhost:8080/api/public/health
```

Resposta esperada:

```json
{
  "status": "UP",
  "application": "numberone",
  "activeProfiles": ["k8s"],
  "timestamp": "..."
}
```

### 8. Autenticacao

Login:

```powershell
curl -X POST http://localhost:8080/api/public/auth/login `
  -H "Content-Type: application/json" `
  -d "{\"username\":\"admin\",\"password\":\"admin123456\"}"
```

Resposta esperada:

```json
{
  "accessToken": "...",
  "tokenType": "Bearer",
  "expiresInSeconds": 3600
}
```

Use o `accessToken` nas rotas administrativas:

```text
Authorization: Bearer <accessToken>
```

### 9. Criar e Consultar Cliente

Criar cliente:

```powershell
curl -X POST http://localhost:8080/api/admin/clientes `
  -H "Content-Type: application/json" `
  -H "Authorization: Bearer <accessToken>" `
  -d "{\"nome\":\"Ana Silva\",\"tipoDocumento\":\"PESSOA_FISICA\",\"documento\":\"52998224725\",\"email\":\"ana.silva@email.com\",\"telefone\":\"11999999999\",\"endereco\":\"Rua das Flores, 123\",\"ativo\":true}"
```

Consultar por ID:

```powershell
curl http://localhost:8080/api/admin/clientes/<id-do-cliente> `
  -H "Authorization: Bearer <accessToken>"
```

Listar clientes:

```powershell
curl http://localhost:8080/api/admin/clientes `
  -H "Authorization: Bearer <accessToken>"
```

## Execução AWS

### Ambiente AWS

Arquivo:

```text
.k8s/env/aws.env
```

Valores atuais:

```text
APP_IMAGE=949294083326.dkr.ecr.us-east-1.amazonaws.com/numberone-api:1.0.0
SPRING_PROFILES_ACTIVE=prod
DB_HOST=numberone-postgres.cktotuxmsm2b.us-east-1.rds.amazonaws.com
DB_URL=jdbc:postgresql://numberone-postgres.cktotuxmsm2b.us-east-1.rds.amazonaws.com:5432/numberone
DB_USERNAME=numberone
```

Service AWS:

```text
.k8s/app/service-aws.yaml
```

Tipo:

```text
LoadBalancer
```

### Deploy AWS

Fluxo validado:

```text
Docker Build
↓
Login no ECR
↓
Docker Push
↓
aws eks update-kubeconfig
↓
kubectl apply
↓
Pods
↓
Service
↓
Acesso público
```

1. Docker build

```bash
docker build -t 949294083326.dkr.ecr.us-east-1.amazonaws.com/numberone-api:1.0.0 .
```

2. Login no ECR

```bash
aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin 949294083326.dkr.ecr.us-east-1.amazonaws.com
```

3. Docker push

```bash
docker push 949294083326.dkr.ecr.us-east-1.amazonaws.com/numberone-api:1.0.0
```

4. Atualizar kubeconfig

```bash
aws eks update-kubeconfig --region us-east-1 --name numberone-eks
```

5. Aplicar manifests

Script AWS existente:

```bash
.k8s/scripts/deploy-aws.sh
```

Comandos executados pelo script:

```bash
kubectl apply -f .k8s/namespace.yaml
envsubst < .k8s/app/configmap.yaml | kubectl apply -f -
envsubst < .k8s/app/secret.yaml | kubectl apply -f -
kubectl apply -f .k8s/app/service-aws.yaml
envsubst < .k8s/app/deployment.yaml | kubectl apply -f -
kubectl apply -f .k8s/app/hpa.yaml
```

6. Verificar pods

```bash
kubectl get pods -n numberone -w
```

7. Verificar service

```bash
kubectl get service -n numberone
```

8. Acessar aplicação

```bash
curl http://<load-balancer>/api/public/health
```

## Logs e Diagnostico

Logs da API:

```powershell
kubectl logs -n numberone deploy/numberone-api --tail=100
```

Logs anteriores, quando houve restart:

```powershell
kubectl logs -n numberone deploy/numberone-api --previous --tail=100
```

Detalhes dos pods:

```powershell
kubectl describe pod -n numberone -l app=numberone-api
kubectl describe pod -n numberone -l app=numberone-postgres
```

Eventos do namespace:

```powershell
kubectl get events -n numberone --sort-by=.lastTimestamp
```

## HPA

O HPA esta configurado para escalar a API por CPU e memoria.

Habilite o metrics server no Minikube:

```powershell
minikube addons enable metrics-server
```

Verifique:

```powershell
kubectl get hpa -n numberone
kubectl top pods -n numberone
```

Se `kubectl top` ainda nao funcionar imediatamente, aguarde alguns segundos e tente novamente.

## Atualizar a Aplicacao Depois de Alterar Codigo

Buildar nova imagem LOCAL:

```powershell
& minikube -p minikube docker-env --shell powershell | Invoke-Expression
docker build -t numberone-api:latest .
```

Reiniciar os pods da API:

```powershell
kubectl rollout restart deployment/numberone-api -n numberone
kubectl rollout status deployment/numberone-api -n numberone
```

Acompanhar:

```powershell
kubectl get pods -n numberone -w
```

## Reaplicar Manifests

Validar sem alterar o cluster:

```powershell
kubectl apply -f .k8s --recursive --dry-run=client
```

Aplicar de verdade:

```powershell
kubectl apply -f .k8s --recursive
```

Aplicar somente a API:

```powershell
kubectl apply -f .k8s/app
```

## Acessar o Mailpit

Encaminhe a porta web do Mailpit:

```powershell
kubectl port-forward -n numberone svc/mailpit 8025:8025
```

Acesse:

```text
http://localhost:8025
```

## Parar e Remover Ambiente

Remover os recursos do projeto:

```powershell
kubectl delete namespace numberone
```

Parar o Minikube:

```powershell
minikube stop
```

Apagar o cluster local:

```powershell
minikube delete
```

## Problemas Comuns

### ImagePullBackOff

O cluster nao encontrou a imagem `numberone-api:latest`.

Refaca o build dentro do Docker do Minikube:

```powershell
& minikube -p minikube docker-env --shell powershell | Invoke-Expression
docker build -t numberone-api:latest .
kubectl rollout restart deployment/numberone-api -n numberone
```

### CrashLoopBackOff na API

Veja os logs:

```powershell
kubectl logs -n numberone deploy/numberone-api --previous --tail=120
```

As causas comuns sao:

- banco ainda nao pronto;
- variavel de ambiente faltando no ConfigMap ou Secret;
- erro de migration Flyway;
- imagem antiga rodando no Minikube.

### HPA com Metrics Unknown

Habilite o metrics server:

```powershell
minikube addons enable metrics-server
```

Depois aguarde e consulte:

```powershell
kubectl get hpa -n numberone
kubectl top pods -n numberone
```

### Porta 8080 em Uso

Use outra porta local no port-forward:

```powershell
kubectl port-forward -n numberone svc/numberone-api-service 8081:80
```

Depois acesse:

```text
http://localhost:8081
```
