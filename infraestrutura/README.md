# Infraestrutura e CI/CD

Esta pasta documenta a infraestrutura de apoio do projeto.

## GitHub Actions local

A esteira local usa tres workflows:

1. `.github/workflows/ci-local-validation.yml`
   - E disparado em commits para `main`, `develop` e `feature/automacao`.
   - Tambem roda em pull requests para `develop`.
   - Valida a estrutura Maven do projeto.
   - Executa build da aplicacao.
   - Executa testes unitarios.
   - Valida os manifestos YAML do Kubernetes em modo dry-run.

2. `.github/workflows/ci-cd-local-database.yml`
   - E disparado automaticamente quando o workflow de validacao termina com sucesso.
   - Aplica o namespace.
   - Faz deploy do PostgreSQL no Kubernetes local.
   - Faz deploy do Mailpit.
   - Aguarda os rollouts e evidencia os recursos criados.

3. `.github/workflows/ci-cd-local-api.yml`
   - E disparado automaticamente quando o workflow do banco termina com sucesso.
   - Executa build da aplicacao.
   - Executa testes automatizados completos.
   - Faz build da imagem Docker.
   - Aplica os manifestos YAML da API.
   - Atualiza o deployment com a imagem gerada.
   - Aguarda o rollout da API.

O deploy local nao usa Amazon EKS, ECR, RDS, Terraform ou credenciais AWS. A imagem e construida no runner self-hosted e referenciada diretamente pelo Kubernetes local.

## Pre-requisitos do runner `runner-windows-0002`

Configure o runner self-hosted com:

- Docker disponivel no PATH.
- `kubectl` disponivel no PATH.
- Acesso configurado ao cluster Kubernetes local no contexto padrao do usuario que executa o servico do runner.
- Opcionalmente, Minikube disponivel no PATH. Quando encontrado, o workflow configura o Docker do Minikube antes do build da imagem.
- Label customizada `runner-windows-0002` no runner, usada em `runs-on: [self-hosted, runner-windows-0002]`.

## Manifests Kubernetes locais

Os workflows locais usam os manifests separados em `.k8s/`:

- `.k8s/namespace.yaml`
- `.k8s/db`
- `.k8s/mailpit`
- `.k8s/app`

O manifesto legado `infraestrutura/kubernetes/application.yaml` foi mantido apenas como referencia historica da primeira versao da esteira local.
