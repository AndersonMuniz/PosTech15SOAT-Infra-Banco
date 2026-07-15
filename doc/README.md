# Documentacao Tecnica

Esta pasta centraliza a documentacao de apoio do projeto NumberOne para a entrega da Fase 2 do Tech Challenge.

## Indice

| Area | Documento | Objetivo |
| --- | --- | --- |
| Padroes tecnicos | [`padroes-java-25.md`](padroes-java-25.md) | Padroes de codigo, organizacao de pacotes e convencoes para Java 25 com Spring Boot. |
| Dependencias | [`dependencias.md`](dependencias.md) | Dependencias do projeto, escopos e motivacao de uso. |
| Execucao local | [`execucao-local.md`](execucao-local.md) | Como executar a aplicacao com Docker Compose ou localmente. |
| CI/CD local | [`cicd/execucao-local-github-actions.md`](cicd/execucao-local-github-actions.md) | Como configurar runner self-hosted e executar deploy local via GitHub Actions. |
| Testes | [`testes/README.md`](testes/README.md) | Estrategia, comandos e relatorios de testes automatizados. |
| Seguranca | [`security/README.md`](security/README.md) | Como executar a analise local com SonarQube. |
| Relatorio de vulnerabilidades | [`security/relatorio-vulnerabilidades.md`](security/relatorio-vulnerabilidades.md) | Resultado da analise de vulnerabilidades e plano de acao. |
| Linguagem ubiqua | [`linguagem_ubiqua/linguagem-ubiqua.md`](linguagem_ubiqua/linguagem-ubiqua.md) | Glossario dos termos de dominio utilizados no projeto. |
| Modulos de negocio | [`modulos/`](modulos/) | Documentacao dos modulos Customer, Vehicle, Service Order, Automotive Service e Inventory. |
| Diagramas tecnicos | [`diagrams/`](diagrams/) | Diagramas de componentes, CI/CD e infraestrutura AWS. |
| Equipe | [`equipe/`](equipe/) | Documentacao de escopo individual, modelagem aprovada e documento final de entrega. |

## Entregaveis Documentados

- APIs da Fase 2 para Ordem de Servico, incluindo abertura com dados de cliente, veiculo, servicos e pecas.
- Consulta de status e listagem ordenada de ordens de servico.
- Testes unitarios, integracao e E2E/Cucumber.
- Dockerfile e Docker Compose.
- Kubernetes com Deployments, Services, ConfigMaps, Secrets, PVC e HPA.
- Kustomize com bases e overlays locais/AWS.
- CI/CD local com GitHub Actions e runner self-hosted.
- Terraform para VPC, EKS, RDS e ECR.
- Desenhos tecnicos em Mermaid, C4 e Draw.io.

## Base Atual do Projeto

- Java configurado no Maven: `25`
- Parent do projeto: `org.springframework.boot:spring-boot-starter-parent:4.0.5`
- Build tool: Maven
