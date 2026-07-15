# Execucao Local de Deploy com GitHub Actions

Este guia descreve como executar localmente os workflows de deploy do projeto usando GitHub Actions com runner self-hosted no Windows.

## Visao Geral

O deploy local foi pensado para rodar em um runner self-hosted no Windows, porque o cluster Minikube e o Docker Desktop ficam na maquina do desenvolvedor.

Um runner hospedado pelo GitHub nao conseguiria acessar diretamente esse ambiente local.

Fluxo esperado:

```text
GitHub Actions
  -> runner self-hosted Windows
  -> Docker Desktop
  -> Minikube
  -> Kubernetes local
```

## Pre-requisitos

Na maquina onde o runner sera executado, instale e configure:

- Docker Desktop instalado e em execucao.
- Minikube instalado.
- `kubectl` instalado.
- Git instalado.
- Acesso ao repositorio no GitHub.

Antes de executar os workflows de deploy, valide o cluster local:

```cmd
minikube start
kubectl config current-context
kubectl get nodes
```

O contexto esperado para o deploy local e:

```text
minikube
```

## Configuracao do Runner Self-hosted

No GitHub, acesse:

```text
Repository -> Settings -> Actions -> Runners -> New self-hosted runner
```

Selecione:

```text
Runner image: Windows
Architecture: x64
```

O GitHub vai exibir os comandos oficiais de download e configuracao do runner.

Uma estrutura local sugerida para o runner e:

```cmd
mkdir D:\actions-runner\numberone
cd /d D:\actions-runner\numberone
```

Depois execute os comandos de download e extracao informados pelo proprio GitHub.

Na configuracao, informe a URL do repositorio e o token gerado pelo GitHub. Um exemplo do formato do comando e:

```cmd
config.cmd --url https://github.com/<owner>/<repo> --token <token-gerado-pelo-github> --name runner-windows-0002 --labels runner-windows-0002
```

## Labels do Runner

Os workflows deste projeto usam:

```yaml
runs-on: [self-hosted, runner-windows-0002]
```

Isso significa que o GitHub so vai enviar os jobs para runners que tenham os dois labels:

- `self-hosted`: criado automaticamente pelo GitHub.
- `runner-windows-0002`: definido na configuracao do runner.

Se o runner estiver online, mas sem esse label, o workflow ficara aguardando com a mensagem de que esta esperando um runner disponivel.

## Execucao Manual do Runner

Para executar manualmente durante os estudos:

```cmd
cd /d D:\actions-runner\numberone
run.cmd
```

Quando aparecer a mensagem abaixo, o runner esta conectado e aguardando jobs:

```text
Connected to GitHub
Listening for Jobs
```

Nesse modo, o terminal precisa ficar aberto.

Quando um workflow compativel for disparado, o GitHub envia o job para esse runner. O runner baixa o codigo na pasta `_work` e executa as steps definidas no arquivo YAML do workflow.

## Execucao Como Servico do Windows

Tambem e possivel instalar o runner como servico do Windows.

Abra o terminal como Administrador na pasta do runner e execute:

```cmd
svc.cmd install
svc.cmd start
```

Com o runner como servico, nao e necessario manter o terminal aberto.

## Como Disparar os Workflows Locais

O workflow `CI` roda em `push`, pull request para `develop` e tambem manualmente. Ele valida build, testes unitarios e manifests Kubernetes sem fazer deploy.

Para deploy local, existem dois workflows separados:

- `CD Local - Banco de Dados`
- `CD Local - API`

Ambos rodam automaticamente em `push` na branch `develop` e tambem podem ser executados manualmente em:

```text
GitHub -> Actions -> selecionar workflow -> Run workflow
```

## Ordem Recomendada Para Teste Local

Para testar o fluxo local de forma controlada, execute nesta ordem:

1. Inicie Docker Desktop e Minikube.
2. Inicie o runner com `run.cmd` ou pelo servico do Windows.
3. Execute `CD Local - Banco de Dados`.
4. Aguarde PostgreSQL e Mailpit ficarem com rollout concluido.
5. Execute `CD Local - API`.
6. Aguarde o rollout do deployment `numberone-api`.

Depois valide os pods:

```cmd
kubectl get pods -n numberone
```

Exponha a API localmente:

```cmd
kubectl port-forward service/numberone-api-service 8080:80 -n numberone
```

Acesse o Swagger:

```text
http://localhost:8080/swagger-ui.html
```

## Problemas Comuns

### Workflow aguardando runner

Verifique se:

- O runner esta em execucao.
- O runner aparece como `Idle` ou `Active` no GitHub.
- O runner possui o label `runner-windows-0002`.
- O workflow usa o mesmo label em `runs-on`.

### Erro de contexto Kubernetes

Valide:

```cmd
kubectl config current-context
kubectl get nodes
```

Se o contexto nao estiver configurado, inicie o Minikube novamente:

```cmd
minikube start
```

### Rollout travado

Verifique os pods e eventos:

```cmd
kubectl get pods -n numberone
kubectl describe pod <nome-do-pod> -n numberone
kubectl logs <nome-do-pod> -n numberone
```

Se o erro for `ImagePullBackOff`, normalmente a imagem nao foi carregada corretamente no Minikube ou o deployment esta apontando para uma tag que o Minikube nao conhece.
