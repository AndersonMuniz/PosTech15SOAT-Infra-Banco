# Infraestrutura e CI/CD

Esta pasta documenta a infraestrutura de apoio do projeto.

## GitHub Actions local

A esteira local usa tres workflows:

1. `.github/workflows/ci.yml`
   - Roda em pull requests para `develop`.
   - Roda em qualquer push.
   - Valida a estrutura Maven do projeto.
   - Executa build da aplicacao.
   - Executa testes unitarios.
   - Valida os manifestos YAML do Kubernetes em modo dry-run.
   - Nao faz deploy.

2. `.github/workflows/cd-local-database.yml`
   - Roda em push para `develop`.
   - Tambem pode ser executado manualmente.
   - Faz deploy do PostgreSQL local no Kubernetes.
   - Faz deploy do Mailpit local.
   - Ao finalizar com sucesso, dispara o workflow da API.

3. `.github/workflows/cd-local-api.yml`
   - Roda quando o workflow `CD Local - Banco de Dados` dispara sua execucao.
   - Tambem pode ser executado manualmente.
   - Faz build da imagem Docker da API.
   - Carrega a imagem no Minikube quando ele esta disponivel.
   - Aplica os manifestos YAML da API.
   - Atualiza o deployment com a imagem gerada.
   - Aguarda o rollout da API.

Fluxos da esteira:

```text
Pull request para develop
  -> CI

Push em qualquer branch
  -> CI

Push em develop
  -> CI
  -> CD Local - Banco de Dados
  -> CD Local - API
```

Para garantir que o deploy local so aconteca com codigo validado, configure a branch `develop` no GitHub como branch protegida exigindo sucesso do workflow `CI` antes do merge do pull request.

O deploy local nao usa Amazon EKS, ECR, RDS, Terraform ou credenciais AWS. A imagem e construida no runner self-hosted e referenciada diretamente pelo Kubernetes local.

## Pre-requisitos do runner `runner-windows-0002`

Configure o runner self-hosted com:

- Docker disponivel no PATH.
- `kubectl` disponivel no PATH.
- Acesso configurado ao cluster Kubernetes local no contexto padrao do usuario que executa o servico do runner.
- Opcionalmente, Minikube disponivel no PATH. Quando encontrado, o workflow carrega no Minikube a imagem criada pelo Docker do runner.
- Label customizada `runner-windows-0002` no runner, usada em `runs-on: [self-hosted, runner-windows-0002]`.

## Manifests Kubernetes locais

Os workflows locais usam os manifests separados em `.k8s/`:

- `.k8s/namespace.yaml`
- `.k8s/db`
- `.k8s/mailpit`
- `.k8s/app`

O manifesto legado `infraestrutura/kubernetes/application.yaml` foi mantido apenas como referencia historica da primeira versao da esteira local.
