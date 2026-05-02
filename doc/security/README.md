# Relatório de Análise de Vulnerabilidades

## 1. Identificação do projeto

- Projeto: NumberOne
- Fase: Tech Challenge - desenvolvimento seguro
- Grupo: number one
- Participantes: Anderson, Julio Cesar, Marcelo e Matheus
- Data da análise: 02/05/2026
- Branch analisada: develop

## 2. Objetivo

Este relatório registra a análise de segurança realizada no projeto NumberOne, backend monolítico para uma oficina mecânica automotiva desenvolvido em Java, Spring Boot e Maven.

A análise tem como objetivo apoiar o entregável de desenvolvimento seguro, identificando vulnerabilidades, bugs, security hotspots, code smells, duplicações, cobertura de testes e alertas encontrados na aplicação em execução.

## 3. Escopo da análise

O scan contemplou o código-fonte Java do backend e a estrutura atual do projeto, incluindo:

- código-fonte Java do backend;
- controllers;
- services;
- repositories;
- entidades e regras de domínio;
- configurações da aplicação;
- testes automatizados disponíveis;
- configuração Maven relacionada ao build e cobertura.

Escopo efetivamente analisado: projeto backend `numberone`, com aproximadamente 8k linhas de código analisadas pelo SonarQube.

Também foi realizada uma análise dinâmica complementar com OWASP ZAP sobre a aplicação em execução localmente.

## 4. Ferramentas utilizadas

| Ferramenta | Versão, se aplicável | Tipo de análise | Forma de execução | Observação |
|---|---|---|---|---|
| SonarQube Community | Community Build v26.4.0.121862 | SAST | Docker Compose local | Executado com `docker-compose.sonar.yml` |
| SonarScanner for Maven | - | SAST | Maven | Executado por `./scripts/sonar-scan.sh` |
| JaCoCo | 0.8.14 | Cobertura de testes | Maven | Relatório XML consolidado enviado ao SonarQube |
| OWASP ZAP | 2.17.0 | DAST | Aplicação local | Relatório gerado em `doc/security/evidencias/2026-05-02-ZAP-Report-.html` |

## 5. Tipo de análise

A análise com SonarQube é do tipo SAST, ou análise estática de código. O SonarQube avalia o código-fonte sem executar a aplicação como um usuário final faria.

Esse tipo de análise permite identificar problemas de segurança, confiabilidade, manutenibilidade, duplicação de código e cobertura de testes antes da entrega final do sistema.

A análise com OWASP ZAP é do tipo DAST, ou análise dinâmica. Nesse caso, a ferramenta avalia a aplicação em execução por meio de requisições HTTP, verificando possíveis alertas de segurança e comportamento dos endpoints expostos.

## 6. Critérios avaliados

| Critério | Descrição |
|---|---|
| Bugs | Possíveis erros de implementação que podem causar falhas. |
| Vulnerabilities | Falhas de segurança identificadas por regras do SonarQube. |
| Security Hotspots | Pontos sensíveis que exigem revisão humana para confirmar risco. |
| Code Smells | Problemas de manutenibilidade e legibilidade. |
| Coverage | Cobertura de testes automatizados enviada pelo JaCoCo. |
| Duplications | Código duplicado identificado pelo SonarQube. |
| Quality Gate | Resultado consolidado dos critérios de qualidade configurados. |
| Alertas OWASP ZAP | Alertas identificados durante a análise dinâmica da aplicação local. |

## 7. Resumo executivo

### 7.1 Resultado SonarQube

| Indicador | Resultado |
|---|---|
| Quality Gate | Passed |
| Bugs | 1 open issue |
| Vulnerabilities | 0 open issues |
| Security Hotspots | 1 hotspot |
| Code Smells | 72 open issues |
| Coverage | 60.1% |
| Duplications | 4.8% |

A análise apresentou resultado geral aprovado no Quality Gate. Não foram identificadas vulnerabilidades abertas no código analisado. Foram encontrados pontos de atenção relacionados a confiabilidade, manutenibilidade, cobertura de testes e revisão de security hotspot.

### Evidência: visão geral do SonarQube

![Visão geral do SonarQube](evidencias/sonar-dashboard.png)

### 7.2 Resultado OWASP ZAP

| Indicador | Resultado |
|---|---|
| Ferramenta | OWASP ZAP |
| Versão | 2.17.0 |
| Tipo de análise | DAST |
| Alvo | `http://localhost:8080` |
| Endpoints identificados | 57 |
| Alertas High | 0 |
| Alertas Medium | 0 |
| Alertas Low | 0 |
| Alertas Informational | 2 |
| Relatório HTML | `doc/security/evidencias/2026-05-02-ZAP-Report-.html` |

A análise dinâmica com OWASP ZAP não identificou alertas de severidade alta, média ou baixa. Foram encontrados apenas 2 alertas informativos, relacionados à identificação da requisição de autenticação e ao teste de variação do header `User-Agent`.

## 8. Resultado do Quality Gate

O Quality Gate apresentou o resultado **Passed**.

Isso indica que, conforme os critérios configurados no SonarQube local, o projeto atende aos requisitos mínimos definidos para aprovação da análise.

Apesar da aprovação, o dashboard apresentou alertas relevantes:

- 1 issue de confiabilidade;
- 72 issues de manutenibilidade;
- 1 security hotspot pendente de revisão;
- cobertura de testes em 60.1%;
- duplicação de código em 4.8%.

Portanto, o projeto passou no Quality Gate, mas ainda possui pontos de melhoria a serem tratados ou justificados antes da entrega final.

### Evidência: Quality Gate

![Quality Gate do projeto](evidencias/sonar-quality-gate.png)

## 9. Vulnerabilidades encontradas

Não foram identificadas vulnerabilidades abertas no scan realizado pelo SonarQube.

| ID ou título da issue | Severidade | Arquivo | Descrição | Impacto | Recomendação | Status |
|---|---|---|---|---|---|---|
| Não aplicável | Não aplicável | Não aplicável | Não foram identificadas vulnerabilidades abertas no scan realizado. | Não aplicável | Manter acompanhamento em novos scans. | Finalizado |

## 10. Bugs encontrados

O SonarQube identificou 1 issue aberta relacionada à confiabilidade.

| ID ou título da issue | Severidade | Arquivo | Descrição | Impacto | Recomendação | Status |
|---|---|---|---|---|---|---|
| Issue de Reliability identificada pelo SonarQube | Medium | `src/.../security/api/controllers/AdminSessionController.java` | O SonarQube identificou uma issue relacionada à confiabilidade do código. | Pode indicar comportamento inesperado ou risco de falha em determinado fluxo. | Avaliar a issue diretamente no SonarQube e corrigir o código caso seja confirmada como problema real. | Pendente de análise |

Observação: a evidência enviada mostra a existência de 1 issue de confiabilidade, porém o detalhe textual da issue não está legível no print. O item deve ser complementado após abrir a issue no SonarQube.

## 11. Security Hotspots

O SonarQube identificou 1 security hotspot pendente de revisão.

| Hotspot | Arquivo | Risco analisado | Decisão | Justificativa |
|---|---|---|---|---|
| Make sure disabling Spring Security's CSRF protection is safe here. | `src/.../security/infrastructure/config/SecurityConfiguration.java` | Desabilitação da proteção CSRF no Spring Security. | Pendente de revisão técnica. | O projeto utiliza autenticação baseada em JWT para APIs. Em APIs stateless, a desabilitação de CSRF pode ser aceitável quando não há autenticação baseada em sessão/cookie. Mesmo assim, o ponto deve ser revisado e documentado para confirmar que não há risco no contexto da aplicação. |

Análise do hotspot:

O alerta está relacionado à configuração:

```java
.csrf(AbstractHttpConfigurer::disable)
```

Esse tipo de configuração é comum em APIs REST stateless que utilizam JWT no header `Authorization`. No entanto, como o SonarQube classificou o ponto como security hotspot, é necessário registrar a justificativa técnica ou ajustar a configuração caso o projeto passe a utilizar sessão ou cookies de autenticação.

Recomendação:

- confirmar se a aplicação não utiliza sessão/cookie para autenticação;
- confirmar se o JWT é enviado via header `Authorization`;
- manter endpoints administrativos protegidos por autenticação;
- documentar a decisão no relatório;
- marcar o hotspot no SonarQube como revisado somente após validação do grupo.

### Evidência: Security Hotspot

![Security Hotspot identificado no SonarQube](evidencias/sonar-security-hotspots.png)

## 12. Code Smells relevantes

O SonarQube identificou 72 issues de manutenibilidade.

| Item | Arquivo | Descrição | Recomendação | Prioridade |
|---|---|---|---|---|
| Code Smells identificados pelo SonarQube | Diversos arquivos do projeto | Foram encontrados pontos de melhoria relacionados à manutenibilidade do código. | Avaliar os itens de maior severidade e priorizar correções que reduzam complexidade, duplicação ou baixa legibilidade. | Média |
| Duplicação de código | Diversos arquivos do projeto | O SonarQube identificou 4.8% de duplicação no código analisado. | Avaliar se há trechos repetidos que podem ser extraídos para métodos, componentes, mappers ou classes reutilizáveis. | Média |
| Baixa cobertura em trechos do projeto | Diversos arquivos do projeto | A cobertura geral ficou em 60.1%. | Aumentar testes unitários e de integração nos fluxos críticos. | Alta |

Observação: a lista detalhada de code smells deve ser consultada na tela de Issues do SonarQube. Para a entrega, os principais pontos foram consolidados neste relatório com foco em impacto e plano de ação.

### Evidência: Issues identificadas

![Issues identificadas no SonarQube](evidencias/sonar-issues.png)

## 13. Análise de cobertura de testes

Percentual de cobertura: **60.1%**

A cobertura de testes foi importada corretamente pelo SonarQube após a configuração do relatório JaCoCo. O resultado mostra uma evolução em relação ao primeiro scan, que havia apresentado 0.0% por ausência do relatório XML de cobertura.

A cobertura atual ainda está abaixo da meta de 80% definida para domínios críticos do projeto. Portanto, o principal ponto de melhoria é ampliar os testes automatizados, principalmente em regras de negócio e fluxos principais.

Pontos recomendados para aumento de cobertura:

- regras de domínio dos módulos principais;
- services/use cases da aplicação;
- fluxos de criação e acompanhamento de ordem de serviço;
- fluxos de estoque;
- validações de CPF/CNPJ e placa;
- autenticação e autorização em rotas administrativas;
- cenários de erro e exceções de negócio.

### Evidência: cobertura de testes

![Cobertura de testes no SonarQube](evidencias/sonar-coverage.png)

## 14. Análise de duplicações

Percentual de duplicação: **4.8%**

O percentual de duplicação identificado está em um nível relativamente controlado para um MVP acadêmico, mas ainda merece acompanhamento.

A duplicação pode estar relacionada a estruturas repetidas entre módulos, DTOs, mappers, testes ou padrões semelhantes entre entidades e serviços.

Recomendações:

- revisar duplicações indicadas pelo SonarQube;
- evitar abstrações prematuras em regras de negócio;
- extrair métodos utilitários apenas quando houver repetição clara e estável;
- manter duplicações aceitáveis quando forem intencionais para preservar separação entre módulos;
- priorizar refatorações que reduzam risco de inconsistência futura.

## 15. Análise dinâmica com OWASP ZAP

Além da análise estática com SonarQube, foi realizada uma análise dinâmica complementar com OWASP ZAP 2.17.0 sobre a aplicação em execução localmente.

A análise teve como objetivo identificar possíveis alertas de segurança em tempo de execução, problemas de configuração HTTP, exposição indevida de informações e comportamento dos endpoints durante requisições automatizadas.

| Indicador | Resultado |
|---|---|
| Ferramenta | OWASP ZAP |
| Versão | 2.17.0 |
| Tipo de análise | DAST |
| Alvo analisado | `http://localhost:8080` |
| Endpoints identificados | 57 |
| Alertas High | 0 |
| Alertas Medium | 0 |
| Alertas Low | 0 |
| Alertas Informational | 2 |
| Relatório gerado | `doc/security/evidencias/2026-05-02-ZAP-Report-.html` |

Não foram identificados alertas de severidade alta, média ou baixa na análise dinâmica. Os alertas encontrados foram apenas informativos:

- `Authentication Request Identified`: identificação do endpoint de autenticação `POST /api/public/auth/login`;
- `User Agent Fuzzer`: teste de variação do header `User-Agent` em endpoints da API.

Durante a execução, o ZAP realizou requisições exploratórias e algumas chamadas retornaram status `4xx`, principalmente em endpoints administrativos com parâmetros ausentes ou sem contexto autenticado. Esses retornos foram considerados comportamento esperado para a análise automatizada e não foram classificados como vulnerabilidades.

O relatório HTML completo foi salvo em:

```text
doc/security/evidencias/2026-05-02-ZAP-Report-.html
```

## 16. Plano de ação

| Item | Problema | Ação recomendada | Prioridade | Status |
|---|---|---|---|---|
| 1 | Security hotspot relacionado a CSRF desabilitado | Revisar a configuração de segurança, confirmar uso stateless com JWT e documentar a decisão técnica. | Alta | Pendente |
| 2 | Cobertura de testes em 60.1% | Aumentar cobertura dos domínios críticos, priorizando regras de negócio e fluxos obrigatórios do Tech Challenge. | Alta | Pendente |
| 3 | 1 issue de confiabilidade | Abrir a issue no SonarQube, analisar causa e corrigir se aplicável. | Média | Pendente |
| 4 | 72 issues de manutenibilidade | Priorizar code smells de maior severidade e corrigir os que impactam legibilidade, complexidade ou evolução do projeto. | Média | Pendente |
| 5 | Duplicação de 4.8% | Avaliar trechos duplicados indicados pelo SonarQube e refatorar quando houver ganho real de manutenção. | Baixa/Média | Pendente |
| 6 | Alertas informativos do OWASP ZAP | Manter o relatório como evidência complementar e revisar novamente em futuras execuções autenticadas. | Baixa | Finalizado |

## 17. Conclusão

A análise estática de código realizada com SonarQube Community apresentou resultado **Passed** no Quality Gate.

Não foram identificadas vulnerabilidades abertas no scan realizado, o que indica que, dentro das regras avaliadas pelo SonarQube, não há falhas de segurança classificadas como vulnerabilidades no momento da análise.

Também foi realizada análise dinâmica com OWASP ZAP sobre a aplicação em execução localmente. O ZAP identificou 57 endpoints e não encontrou alertas de severidade alta, média ou baixa. Foram registrados apenas 2 alertas informativos, que não representam vulnerabilidades confirmadas.

Apesar do resultado positivo, o projeto possui pontos de atenção relevantes:

- 1 security hotspot relacionado à desabilitação de CSRF no Spring Security;
- 1 issue de confiabilidade;
- 72 issues de manutenibilidade;
- cobertura de testes de 60.1%;
- duplicação de código de 4.8%;
- necessidade de ampliar testes e revisões em endpoints protegidos por autenticação.

O principal risco de segurança identificado pelo SonarQube é o hotspot de CSRF. A configuração pode ser aceitável para uma API REST stateless baseada em JWT, mas deve ser revisada e justificada tecnicamente pelo grupo.

O principal ponto de melhoria para qualidade é o aumento da cobertura de testes, especialmente nos domínios críticos e fluxos obrigatórios do Tech Challenge.

Com base nas análises realizadas, o projeto atende ao Quality Gate configurado e não apresentou vulnerabilidades críticas, altas ou médias nas ferramentas utilizadas. Recomenda-se tratar os pontos listados no plano de ação para fortalecer a segurança, a confiabilidade e a manutenibilidade antes da entrega final.

## 18. Evidências

As evidências da análise devem estar salvas em:

```text
doc/security/evidencias/
```

Como este relatório fica em `doc/security/relatorio-vulnerabilidades.md`, as imagens são referenciadas de forma relativa pela pasta:

```text
evidencias/
```

Evidências utilizadas:

- `doc/security/evidencias/sonar-dashboard.png`
- `doc/security/evidencias/sonar-quality-gate.png`
- `doc/security/evidencias/sonar-issues.png`
- `doc/security/evidencias/sonar-security-hotspots.png`
- `doc/security/evidencias/sonar-coverage.png`
- `doc/security/evidencias/2026-05-02-ZAP-Report-.html`

### 18.1 Dashboard

![Dashboard do SonarQube](evidencias/sonar-dashboard.png)

### 18.2 Quality Gate

![Quality Gate do SonarQube](evidencias/sonar-quality-gate.png)

### 18.3 Issues

![Issues do SonarQube](evidencias/sonar-issues.png)

### 18.4 Security Hotspots

![Security Hotspots do SonarQube](evidencias/sonar-security-hotspots.png)

### 18.5 Coverage

![Coverage do SonarQube](evidencias/sonar-coverage.png)

### 18.6 Relatório OWASP ZAP

O relatório HTML gerado pelo OWASP ZAP está disponível em:

[Relatório OWASP ZAP](evidencias/2026-05-02-ZAP-Report-.html)