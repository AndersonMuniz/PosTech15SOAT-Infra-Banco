# Kubernetes - NumberOne

Este guia mostra como subir a aplicacao NumberOne em Kubernetes usando Minikube.

Os manifests ficam em:

```text
.k8s/
  namespace.yaml
  app/
  db/
  mailpit/
```

## O que Sobe no Cluster

- `numberone-api`: API Spring Boot.
- `numberone-postgres`: banco PostgreSQL.
- `mailpit`: servidor SMTP/Web para testes de e-mail.
- `numberone-api-service`: Service interno da API.
- `numberone-postgres-service`: Service interno do banco.
- `numberone-api-hpa`: autoscaling horizontal da API.
- `numberone-postgres-data`: volume persistente do PostgreSQL.

## Pre-requisitos

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

## 1. Iniciar o Minikube

```powershell
minikube start --driver=docker
kubectl config use-context minikube
```

Confirme:

```powershell
minikube status
kubectl get nodes
```

## 2. Buildar a Imagem da API

Como o manifest da API usa a imagem local:

```yaml
image: numberone-api:latest
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

## 3. Aplicar os Manifests

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

## 4. Acompanhar a Subida dos Pods

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

## 5. Entender o Init Container da API

A API possui um `initContainer` chamado `wait-for-postgres`.

Ele espera o Postgres responder na porta `5432` antes de iniciar o Spring Boot:

```text
numberone-api   0/1   Init:0/1
numberone-api   0/1   PodInitializing
numberone-api   1/1   Running
```

Para ver o log do init container:

```powershell
kubectl logs -n numberone <nome-do-pod-api> -c wait-for-postgres
```

## 6. Validar ConfigMap e Secret

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

## 7. Testar a API Localmente

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

## 8. Autenticacao

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

## 9. Criar e Consultar Cliente

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

## 10. Logs e Diagnostico

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

## 11. HPA

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

## 12. Atualizar a Aplicacao Depois de Alterar Codigo

Buildar nova imagem:

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

## 13. Reaplicar Manifests

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

## 14. Acessar o Mailpit

Encaminhe a porta web do Mailpit:

```powershell
kubectl port-forward -n numberone svc/mailpit 8025:8025
```

Acesse:

```text
http://localhost:8025
```

## 15. Parar e Remover Ambiente

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
