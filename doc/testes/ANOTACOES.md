# Anotações pessoais - Estratégia de Testes

Este documento é um apoio pessoal para lembrar as decisões da estratégia de testes. O documento oficial para o time é o `doc/testes/README.md`.

## Decisões tomadas

O padrão começou pelo módulo `automotiveservice` porque ele tem domínio, application service, API, persistência e agora um fluxo E2E com Cucumber. Isso torna o módulo uma boa referência para replicar a organização nos demais módulos.

A separação ficou assim:

- Unitários em `unit`: rápidos, sem Spring e sem banco.
- Integração em `integration`: validam controller/API ou persistence/gateway.
- Cucumber em `e2e`: valida fluxo de negócio em Gherkin e roda isolado.

O Cucumber foi isolado porque ele tem custo maior, sobe contexto Spring e valida comportamento em nível mais alto. Ele é útil como documentação viva, mas não deve testar tudo.

## Diferença entre unitário, integração e Cucumber/E2E

Teste unitário valida uma classe ou regra em isolamento. Exemplo: domínio e application service com mocks.

Teste de integração valida se camadas ou componentes conversam corretamente. Exemplo: controller com request/response/validação ou gateway com persistence.

Cucumber/E2E valida uma jornada de negócio descrita em linguagem mais próxima da regra. Exemplo atual: cadastrar serviço automotivo e consultar por id.

## Por que Cucumber roda isolado

`mvn test` precisa continuar rápido para feedback diário.

`mvn verify` valida unitários e integração técnica.

`mvn verify -Pcucumber` valida fluxos E2E/BDD quando esse tipo de evidência for necessário.

Essa separação evita que cenários BDD fiquem misturados com testes técnicos e evita duplicar tudo que já é coberto por unitários e integração.

## H2 e Testcontainers

O profile `test` usa H2 em memória com modo PostgreSQL. Ele é simples, rápido e não depende de Docker.

Testcontainers PostgreSQL é mais fiel ao banco real, mas exige Docker e tende a deixar a execução mais pesada. É uma boa evolução para fluxos onde comportamento específico do PostgreSQL for relevante.

Para explicar:

- H2: melhor para simplicidade e velocidade.
- Testcontainers: melhor para fidelidade com produção.

## O que observar no JaCoCo

JaCoCo mede cobertura. Ele não diz sozinho se o teste é bom, mas ajuda a enxergar áreas sem exercício.

O foco deve ser cobertura de classes com comportamento:

- entidades de domínio com regra;
- services de application;
- controllers;
- mappers relevantes;
- gateways de persistência.

Classes simples, como DTOs, exceptions, JPA entities simples e configurações, foram excluídas da cobrança direta. Elas ainda podem ser executadas indiretamente.

O relatório mais importante para cobertura geral é:

```text
target/site/jacoco-merged/index.html
```

## Surefire, Failsafe e JaCoCo

Surefire executa os testes unitários no ciclo `test`.

Failsafe executa testes de integração no ciclo `verify`.

JaCoCo coleta e apresenta cobertura dos testes executados.

Forma simples de explicar:

- Surefire/Failsafe mostram execução: passou, falhou, tempo e erro.
- JaCoCo mostra cobertura: quais linhas, branches e classes foram exercitadas.

## Comandos para lembrar

```bash
./mvnw clean test
```

Valida unitários.

```bash
./mvnw clean verify
```

Valida unitários + integração.

```bash
./mvnw clean verify -DskipUnitTests=true -DskipMergedReport=true
```

Valida somente integração.

```bash
./mvnw clean verify -Pcucumber
```

Valida Cucumber/E2E.

## Relatórios para apresentação

Cobertura geral:

```text
target/site/jacoco-merged/index.html
```

Cucumber:

```text
target/cucumber-reports/automotiveservice/index.html
```

Relatórios técnicos:

```text
target/surefire-reports/unit
target/failsafe-reports/integration
target/failsafe-reports/cucumber
```

## Pontos que podem evoluir depois

- Replicar Cucumber apenas para fluxos críticos de outros módulos.
- Avaliar Testcontainers PostgreSQL para E2E.
- Criar `integration/flow` quando houver fluxo integrado que agregue valor.
- Revisar as exclusões do JaCoCo conforme o projeto evoluir.
- Separar contextos Spring menores se o tempo de execução crescer.

## Observações antes de apresentar

- Mostrar que a arquitetura de testes acompanha a Clean Architecture.
- Reforçar que unitário não sobe Spring.
- Reforçar que Cucumber não substitui unitário nem integração.
- Abrir primeiro o JaCoCo merged.
- Abrir o relatório Cucumber só depois de explicar o fluxo.
- Lembrar que a pasta real de migrations é `src/main/resources/db/migrations`.
