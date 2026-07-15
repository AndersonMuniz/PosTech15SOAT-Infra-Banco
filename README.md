# PosTech15SOAT - NumberOne

API REST do Tech Challenge para gerenciamento de uma oficina mecanica. O projeto cobre cadastro de clientes e veiculos, catalogo de servicos automotivos, estoque de pecas e insumos, abertura e acompanhamento de ordens de servico, autenticacao JWT, Swagger, Docker, Kubernetes, CI/CD local e infraestrutura AWS com Terraform.

## Stack

- Java 25
- Spring Boot 4.0.5
- Spring Web MVC
- Spring Security com JWT
- Spring Data JPA
- Flyway
- PostgreSQL
- Mailpit
- Docker e Docker Compose
- Kubernetes com Kustomize
- Horizontal Pod Autoscaler
- Terraform
- Amazon EKS, ECR e RDS
- GitHub Actions
- H2 e Testcontainers para testes
- SonarQube para analise local de qualidade e seguranca

## Modulos da Aplicacao

- `customer`: cadastro de clientes, tipo de documento, documento e validacoes.
- `vehicle`: cadastro de veiculos, placa, marca, modelo, ano e vinculo com cliente.
- `automotiveservice`: catalogo de servicos automotivos, valor base e tempo estimado.
- `inventory`: cadastro de itens de estoque e movimentacoes de entrada, baixa e ajuste.
- `serviceorder`: abertura, diagnostico, orcamento, itens, insumos, status e acompanhamento de OS.
- `shared`: seguranca JWT, tratamento global de erros, Swagger, email e configuracoes comuns.

## Principais Fluxos de API da Fase 2

### Abertura de Ordem de Servico

```text
POST /api/admin/ordens-servico
```

A abertura de OS aceita dados completos ou referencias existentes. Caso a entidade nao exista, a aplicacao tenta cria-la usando a chave forte correspondente.

Exemplo resumido:

```json
{
  "descricaoInicial": "Cliente relatou barulho ao frear",
  "cliente": {
    "nome": "Joao da Silva",
    "tipoDocumento": "PESSOA_FISICA",
    "documento": "12345678901",
    "email": "joao.silva@email.com",
    "telefone": "11999999999",
    "endereco": "Rua das Oficinas, 100"
  },
  "veiculo": {
    "placa": "ABC1D23",
    "marca": "Toyota",
    "modelo": "Corolla",
    "ano": 2022
  },
  "servicos": [
    {
      "codigo": "REV-001",
      "nome": "Revisao basica",
      "descricao": "Troca de oleo, filtros e checklist preventivo",
      "tipoServico": "REVISAO",
      "valorBase": 250.00,
      "tempoEstimadoMinutos": 120,
      "pecas": [
        {
          "codigo": "OLEO-5W30",
          "nome": "Oleo sintetico 5W30",
          "tipoItem": "LUBRIFICANTE",
          "unidadeMedida": "LITRO",
          "custoUnitario": 38.50,
          "precoVenda": 55.00,
          "quantidadeEstoque": 40,
          "estoqueMinimo": 10,
          "quantidadeUsada": 4
        }
      ]
    }
  ],
  "dataHoraEntrada": "2026-04-28T10:30:00"
}
```

Resposta:

```json
{
  "id": "54e94616-70ad-4ce7-b6f7-41c6747d802e"
}
```

### Consulta de Status da OS

```text
GET /api/public/ordens-servico/{id}/status
```

Retorna o status atual da OS, por exemplo:

```json
{
  "id": "54e94616-70ad-4ce7-b6f7-41c6747d802e",
  "status": "AGUARDANDO_APROVACAO"
}
```

### Listagem de Ordens de Servico

```text
GET /api/admin/ordens-servico
```

A listagem:

- Exclui OS `FINALIZADA` e `ENTREGUE`.
- Ordena por prioridade: `EM_EXECUCAO`, `AGUARDANDO_APROVACAO`, `EM_DIAGNOSTICO`, `RECEBIDA`.
- Dentro da mesma prioridade, mostra as mais antigas primeiro.

## Swagger

Com a aplicacao rodando:

```text
http://localhost:8080/swagger-ui.html
```

Para rotas administrativas:

1. Fazer login em `POST /api/public/auth/login`.
2. Copiar o `accessToken`.
3. Clicar em `Authorize`.
4. Informar `Bearer <token>`.

Credenciais locais:

```text
usuario: admin
senha: admin123456
```

## Execucao Local com Docker Compose

Pre-requisitos:

- Docker
- Docker Compose

Subir aplicacao, PostgreSQL e Mailpit:

```bash
docker compose up --build
```

Servicos:

- API: `http://localhost:8080`
- Swagger: `http://localhost:8080/swagger-ui.html`
- PostgreSQL: `localhost:5432`
- Mailpit SMTP: `localhost:1025`
- Mailpit Web: `http://localhost:8025`

Parar:

```bash
docker compose down
```

Parar e remover volume do banco:

```bash
docker compose down -v
```

Mais detalhes: [doc/execucao-local.md](doc/execucao-local.md).

## Execucao Local com Kubernetes

Os manifests ficam em `.k8s/` e usam Kustomize.

Estrutura principal:

```text
.k8s/
|-- base/
|   |-- namespace/
|   |-- app/
|   |-- database/
|   `-- mailpit/
`-- overlays/
    |-- local/
    |   |-- database/
    |   |-- api/
    |   `-- full/
    `-- aws/
        `-- api/
```

### Como o Kustomize e usado

O Kustomize permite montar manifests Kubernetes a partir de uma base comum e aplicar variacoes por ambiente sem duplicar todos os arquivos YAML.

Neste projeto, a pasta `.k8s/base` guarda os recursos compartilhados:

- `namespace`: namespace `numberone`.
- `app`: Deployment, Service, ConfigMap, Secret e HPA da API.
- `database`: Deployment, Service, ConfigMap, Secret e PVC do PostgreSQL.
- `mailpit`: Deployment e Service do Mailpit.

As pastas `.k8s/overlays` representam os ambientes de execucao:

- `local/database`: sobe PostgreSQL e Mailpit no Minikube.
- `local/api`: aplica somente a API local, usando a imagem carregada no Minikube.
- `local/full`: aplica banco, Mailpit e API juntos.
- `aws/api`: adapta a API para o ambiente AWS, usando os valores gerados/fornecidos pela infraestrutura.

Quando usamos `kubectl apply -k`, o Kubernetes executa o Kustomize antes de aplicar os manifests. Na pratica, ele le os arquivos `kustomization.yaml`, junta os recursos da base e aplica os patches do overlay escolhido.

Exemplo:

```bash
kubectl apply -k .k8s/overlays/local/api
```

Para apenas visualizar o YAML final gerado pelo Kustomize, sem aplicar no cluster:

```bash
kubectl kustomize .k8s/overlays/local/api
```

Deploy local do banco e Mailpit:

```bash
kubectl apply -k .k8s/overlays/local/database
kubectl rollout status deployment/numberone-postgres -n numberone
kubectl rollout status deployment/mailpit -n numberone
```

Deploy local da API:

```bash
kubectl apply -k .k8s/overlays/local/api
kubectl rollout status deployment/numberone-api -n numberone
```

Deploy local completo:

```bash
kubectl apply -k .k8s/overlays/local/full
```

Expor Swagger local via port-forward:

```bash
kubectl port-forward service/numberone-api-service 8080:80 -n numberone
```

Depois acessar:

```text
http://localhost:8080/swagger-ui.html
```

Documentacao completa: [.k8s/README.md](.k8s/README.md).

## CI/CD

Os workflows atuais foram separados para preservar o fluxo local funcionando.

| Workflow | Gatilho | Objetivo |
| --- | --- | --- |
| `ci.yml` | `push`, PR para `develop`, manual | Build, testes unitarios e validacao dos overlays Kustomize |
| `cd-local-database.yml` | `push` em `develop`, manual | Deploy do PostgreSQL local e Mailpit no Minikube |
| `cd-local-api.yml` | `push` em `develop`, manual | Build Docker, carga da imagem no Minikube e deploy da API |

Fluxo local esperado:

```text
PR ou push
  -> CI

merge/push na develop
  -> CD Local - Banco de Dados
  -> CD Local - API
```

Diagrama do fluxo: [doc/diagrams/workflow/fluxo-github-actions.md](doc/diagrams/workflow/fluxo-github-actions.md).

### Execucao local do deploy via GitHub Actions

O deploy local usa um runner self-hosted no Windows para que o GitHub Actions consiga executar comandos no Docker Desktop e no Minikube da maquina do desenvolvedor.

Resumo da configuracao:

- Criar o runner em `Repository -> Settings -> Actions -> Runners`.
- Configurar o runner com o label `runner-windows-0002`.
- Manter Docker Desktop e Minikube em execucao.
- Iniciar o runner com `run.cmd` ou como servico do Windows.
- Executar primeiro `CD Local - Banco de Dados` e depois `CD Local - API`.

Guia completo: [doc/cicd/execucao-local-github-actions.md](doc/cicd/execucao-local-github-actions.md).

## Desenhos Tecnicos

Os desenhos tecnicos solicitados no Tech Challenge estao centralizados em `doc/diagrams`.

| Desenho | Objetivo | Link |
| --- | --- | --- |
| Diagrama de componentes - visao geral | Mostra os principais componentes da API, modulos de dominio, infraestrutura de persistencia e integracoes. | [Visao C4 Component - Geral.png](doc/diagrams/componente/Visao%20C4%20Component%20-%20Geral.png) |
| Diagrama de componentes - ordem de servico | Detalha o fluxo e as dependencias do modulo de Ordem de Servico. | [Visao C4 Component - Ordem de Servico.png](doc/diagrams/componente/Visao%20C4%20Component%20-%20Ordem%20de%20Servico.png) |
| Diagrama Mermaid de componentes | Versao textual/editavel dos componentes para revisao e manutencao. | [componentes.md](doc/diagrams/componente/componentes.md) |
| Diagrama Mermaid renderizado | Export visual do diagrama Mermaid. | [Visao Mermaid.png](doc/diagrams/componente/Visao%20Mermaid.png) |
| Arquitetura AWS geral | Visao geral da infraestrutura AWS usada pelo projeto. | [numberone-geral.drawio.png](doc/diagrams/terraform/numberone-geral.drawio.png) |
| Arquitetura EKS | Visao tecnica do cluster EKS e componentes relacionados. | [numberone-eks.drawio.png](doc/diagrams/terraform/numberone-eks.drawio.png) |
| Arquitetura VPC | Visao da rede AWS, subnets e conectividade. | [numberone-vpc.drawio.png](doc/diagrams/terraform/numberone-vpc.drawio.png) |
| Fonte editavel dos diagramas AWS | Arquivo Draw.io para manutencao dos desenhos de infraestrutura. | [numberone.drawio](doc/diagrams/terraform/numberone.drawio) |
| Fluxo de GitHub Actions | Desenho/documentacao do fluxo de CI/CD local. | [fluxo-github-actions.md](doc/diagrams/workflow/fluxo-github-actions.md) |
| Workflow GitHub Actions renderizado | Export visual do fluxo de CI/CD. | [Workflow Github Actions.png](doc/diagrams/workflow/Workflow%20Github%20Actions.png) |

## Infraestrutura AWS com Terraform

A infraestrutura AWS fica em [infra](infra/README.md).

Recursos provisionados:

- Amazon VPC
- Public Subnets
- Private Subnets
- Internet Gateway
- Route Tables
- Security Groups
- Amazon EKS
- Amazon EKS Managed Node Group
- Amazon RDS PostgreSQL
- Amazon ECR
- Backend remoto S3 para Terraform state

Comandos principais:

```bash
cd infra
terraform init
terraform validate
terraform plan
terraform apply
```

O deploy AWS manual usa:

```bash
infra/scripts/deploy-aws.sh
```

Esse script executa Terraform, obtem outputs de RDS/EKS/ECR, faz build e push da imagem para o ECR e aplica o overlay AWS:

```bash
kubectl kustomize .k8s/overlays/aws/api | envsubst | kubectl apply -f -
```

Observacao: os workflows AWS separados ainda devem ser criados quando a conta AWS, IAM/OIDC e o acesso ao EKS estiverem prontos. Isso evita afetar os workflows locais atuais.

## Testes

Documentacao completa: [doc/testes/README.md](doc/testes/README.md).

Unitarios:

```bash
./mvnw clean test
```

Unitarios + integracao:

```bash
./mvnw clean verify
```

Testes focados nos endpoints de Ordem de Servico:

```bash
mvn -Dtest=ServiceOrderServiceTest,ServiceOrderTrackingServiceTest -Dit.test=ServiceOrderControllerIT test failsafe:integration-test failsafe:verify
```

## Seguranca

A documentacao de analise local com SonarQube esta em [doc/security/README.md](doc/security/README.md).

Fluxo resumido:

```bash
./scripts/sonar-up.sh
export SONAR_TOKEN=seu_token_aqui
./scripts/sonar-scan.sh
./scripts/security-evidence.sh
```

Evidencias e relatorio:

- [doc/security/evidencias](doc/security/evidencias)
- [doc/security/relatorio-vulnerabilidades.md](doc/security/relatorio-vulnerabilidades.md)

## Documentacao do Projeto

- [doc/README.md](doc/README.md): indice geral de documentacao.
- [doc/equipe/documentacao_final_grupo_numbeone_fase_2.docx](doc/equipe/documentacao_final_grupo_numbeone_fase_2.docx): documento final da Fase 2.
- [doc/diagrams](doc/diagrams): desenhos tecnicos de componentes, infraestrutura AWS e CI/CD.
- [doc/diagrams/componente/componentes.md](doc/diagrams/componente/componentes.md): diagramas de componentes em Mermaid e C4.
- [doc/diagrams/workflow/fluxo-github-actions.md](doc/diagrams/workflow/fluxo-github-actions.md): fluxo de GitHub Actions.
- [doc/diagrams/terraform](doc/diagrams/terraform): diagramas da infraestrutura AWS.
- [doc/cicd/execucao-local-github-actions.md](doc/cicd/execucao-local-github-actions.md): guia de execucao local do deploy via GitHub Actions e runner self-hosted.
- [doc/linguagem_ubiqua/linguagem-ubiqua.md](doc/linguagem_ubiqua/linguagem-ubiqua.md): linguagem ubiqua do dominio.
- [doc/modulos](doc/modulos): documentacao dos modulos de negocio.
- [infra/README.md](infra/README.md): infraestrutura AWS com Terraform.
- [.k8s/README.md](.k8s/README.md): Kubernetes e Kustomize.