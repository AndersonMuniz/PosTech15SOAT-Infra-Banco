# Dependencias do Projeto

Fonte: `pom.xml`

## Stack Base

- Java: `25`
- Spring Boot Parent: `org.springframework.boot:spring-boot-starter-parent:4.0.5`
- Build: Maven

## Dependencias de Aplicacao

| Dependencia | Escopo | Finalidade |
| --- | --- | --- |
| `org.springframework.boot:spring-boot-starter-data-jpa` | compile | Persistencia com JPA e integracao com Spring Data. |
| `org.springframework.boot:spring-boot-starter-flyway` | compile | Versionamento e execucao de migracoes de banco. |
| `org.springframework.boot:spring-boot-starter-security` | compile | Autenticacao e autorizacao dos endpoints administrativos com JWT. |
| `org.springframework.boot:spring-boot-starter-validation` | compile | Validacao de entrada com Bean Validation. |
| `org.springframework.boot:spring-boot-starter-webmvc` | compile | API REST e camada web baseada em Spring MVC. |
| `org.springframework.boot:spring-boot-starter-mail` | compile | Envio de notificacoes por e-mail, usado nos fluxos de aprovacao de orcamento. |
| `org.springdoc:springdoc-openapi-starter-webmvc-ui:3.0.2` | compile | Geracao de documentacao OpenAPI e interface Swagger UI. |
| `org.projectlombok:lombok` | optional | Reducao de boilerplate em classes Java. |
| `org.flywaydb:flyway-database-postgresql` | compile | Suporte do Flyway para banco PostgreSQL. |
| `org.postgresql:postgresql` | runtime | Driver JDBC PostgreSQL. |
| `org.mapstruct:mapstruct:1.5.5.Final` | compile | Geracao de mappers entre DTOs, dominio e entidades. |
| `io.jsonwebtoken:jjwt-api:0.12.7` | compile | API para geracao e validacao de tokens JWT. |
| `io.jsonwebtoken:jjwt-impl:0.12.7` | runtime | Implementacao da biblioteca JWT. |
| `io.jsonwebtoken:jjwt-jackson:0.12.7` | runtime | Serializacao JSON usada pela biblioteca JWT. |

## Dependencias de Teste

| Dependencia | Escopo | Finalidade |
| --- | --- | --- |
| `org.springframework.boot:spring-boot-starter-data-jpa-test` | test | Suporte a testes de persistencia e JPA. |
| `org.springframework.boot:spring-boot-starter-flyway-test` | test | Apoio a testes envolvendo migracoes Flyway. |
| `org.springframework.boot:spring-boot-starter-restdocs` | test | Geracao de documentacao de API a partir de testes. |
| `org.springframework.boot:spring-boot-starter-test` | test | Base de testes Spring Boot, JUnit, AssertJ e Mockito. |
| `org.springframework.boot:spring-boot-starter-validation-test` | test | Utilitarios para testar validacoes. |
| `org.springframework.boot:spring-boot-starter-webmvc-test` | test | Testes da camada web com suporte Spring MVC. |
| `org.springframework.restdocs:spring-restdocs-mockmvc` | test | Integracao entre Spring REST Docs e MockMvc. |
| `org.springframework.security:spring-security-test` | test | Suporte para testes de seguranca e requisicoes autenticadas. |
| `com.h2database:h2` | test | Banco em memoria para testes e Cucumber/E2E. |
| `io.cucumber:cucumber-java:7.20.1` | test | Definicao dos steps BDD/E2E em Java. |
| `io.cucumber:cucumber-junit-platform-engine:7.20.1` | test | Execucao do Cucumber pela plataforma JUnit. |
| `io.cucumber:cucumber-spring:7.20.1` | test | Integracao entre Cucumber e contexto Spring. |
| `org.junit.platform:junit-platform-suite` | test | Suites de teste para execucao organizada. |
| `org.testcontainers:junit-jupiter:1.21.4` | test | Integracao do Testcontainers com JUnit Jupiter. |
| `org.testcontainers:postgresql:1.21.4` | test | PostgreSQL descartavel para testes de persistencia. |

## Plugins de Build Relevantes

| Plugin | Finalidade |
| --- | --- |
| `org.asciidoctor:asciidoctor-maven-plugin:2.2.1` | Gera documentacao AsciiDoc em HTML no ciclo de build. |
| `org.springframework.boot:spring-boot-maven-plugin` | Empacotamento e suporte ao ciclo de build do Spring Boot. |
| `org.apache.maven.plugins:maven-surefire-plugin` | Executa testes unitarios `*Test.java`. |
| `org.apache.maven.plugins:maven-failsafe-plugin` | Executa testes de integracao `*IT.java` e Cucumber no profile especifico. |
| `org.jacoco:jacoco-maven-plugin:0.8.14` | Gera cobertura de testes unitarios, integracao e relatorio consolidado. |
| `org.apache.maven.plugins:maven-compiler-plugin` | Compilacao do projeto com annotation processing do Lombok. |

## Observacoes

- O projeto ja esta configurado para Java 25.
- Lombok tambem esta configurado como annotation processor no `maven-compiler-plugin`.
- MapStruct tambem esta configurado como annotation processor no `maven-compiler-plugin`.
- A documentacao REST pode ser evoluida com Spring REST Docs e Asciidoctor conforme os testes forem crescendo.
