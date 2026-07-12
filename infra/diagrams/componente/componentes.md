# Diagrama de Componentes

Este documento registra duas visoes do mesmo recorte arquitetural da aplicacao NumberOne:

- **Mermaid flowchart**: versao simples, estavel e facil de renderizar no GitHub.
- **C4 Component em Mermaid**: versao alinhada ao C4 Model, usando a API Spring Boot como container e os modulos internos como componentes.

O objetivo e documentar os componentes principais sem descer ao nivel de classes. Para detalhes de codigo, consulte os pacotes em `src/main/java/br/com/fiap/numberone`.

## Visao Mermaid

```mermaid
flowchart LR
    user[Usuario / Insomnia / Swagger] --> rest[Controllers REST]

    subgraph app[NumberOne API - Spring Boot]
        rest --> security[Shared Security / JWT]
        rest --> customer_api[Customer API]
        rest --> vehicle_api[Vehicle API]
        rest --> serviceorder_api[Service Order API]
        rest --> automotive_api[Automotive Service API]
        rest --> inventory_api[Inventory API]

        customer_api --> customer_app[Customer Application]
        vehicle_api --> vehicle_app[Vehicle Application]
        serviceorder_api --> serviceorder_app[Service Order Application]
        automotive_api --> automotive_app[Automotive Service Application]
        inventory_api --> inventory_app[Inventory Application]

        serviceorder_app --> customer_app
        serviceorder_app --> vehicle_app
        serviceorder_app --> automotive_app
        serviceorder_app --> inventory_app

        customer_app --> customer_domain[Customer Domain]
        vehicle_app --> vehicle_domain[Vehicle Domain]
        serviceorder_app --> serviceorder_domain[Service Order Domain]
        automotive_app --> automotive_domain[Automotive Service Domain]
        inventory_app --> inventory_domain[Inventory Domain]

        customer_app --> customer_infra[Customer Infrastructure]
        vehicle_app --> vehicle_infra[Vehicle Infrastructure]
        serviceorder_app --> serviceorder_infra[Service Order Infrastructure]
        automotive_app --> automotive_infra[Automotive Service Infrastructure]
        inventory_app --> inventory_infra[Inventory Infrastructure]

        security --> auth_infra[Security Infrastructure]
    end

    customer_infra --> postgres[(PostgreSQL)]
    vehicle_infra --> postgres
    serviceorder_infra --> postgres
    automotive_infra --> postgres
    inventory_infra --> postgres
    auth_infra --> postgres

    serviceorder_infra --> mailpit[Mailpit SMTP]
```

## Visao C4 Component - Geral

```mermaid
C4Component
title NumberOne API - Diagrama de Componentes Geral

Person(user, "Usuario administrativo", "Opera a oficina via Swagger, Insomnia ou outro cliente HTTP")

Container_Boundary(api, "NumberOne API - Spring Boot") {
  Component(rest, "Controllers REST", "Spring MVC", "Exponem endpoints publicos e administrativos")
  Component(security, "Shared Security", "Spring Security / JWT", "Autenticacao, autorizacao, sessao admin e token JWT")

  Component(core, "Modulos de Negocio", "Java / Clean Architecture", "Customer, Vehicle, Service Order, Automotive Service e Inventory")
  Component(adapters, "Adapters de Infraestrutura", "Spring Data JPA / SMTP", "Gateways, repositories, entities, mappers e notificacoes")
}

ContainerDb(postgres, "PostgreSQL", "Banco relacional", "Persistencia da API")
Container(mailpit, "Mailpit", "SMTP/Web", "Captura de emails em ambiente local")

Rel(user, rest, "Consome endpoints", "HTTP/JSON")
Rel(rest, security, "Valida autenticacao", "JWT")
Rel(rest, core, "Aciona casos de uso")
Rel(core, adapters, "Usa portas de saida")
Rel(security, adapters, "Consulta usuario admin")
Rel(adapters, postgres, "Le e grava dados", "JDBC/JPA")
Rel(adapters, mailpit, "Envia notificacoes locais", "SMTP")

UpdateLayoutConfig($c4ShapeInRow="2", $c4BoundaryInRow="1")
```

## Visao C4 Component - Ordem de Servico

```mermaid
C4Component
title NumberOne API - Componentes da Ordem de Servico

Person(user, "Usuario administrativo", "Abre, consulta e acompanha ordens de servico")

Container_Boundary(api, "NumberOne API - Spring Boot") {
  Component(rest, "ServiceOrderController", "Spring MVC", "Endpoints de ordem de servico, status, itens, insumos e orcamentos")
  Component(serviceorder, "Service Order Application", "Services / Use Cases", "Orquestra abertura, listagem, status, orcamento, execucao e entrega")

  Component(customer, "Customer", "Modulo Java", "Localiza ou cria cliente por documento")
  Component(vehicle, "Vehicle", "Modulo Java", "Localiza ou cria veiculo por placa")
  Component(automotive, "Automotive Service", "Modulo Java", "Localiza ou cria servicos por codigo")
  Component(inventory, "Inventory", "Modulo Java", "Localiza ou cria pecas por codigo e controla estoque")

  Component(persistence, "Service Order Persistence", "Spring Data JPA", "Repositories, entities, mappers e gateways da OS")
  Component(notification, "Budget Notification", "SMTP Gateway", "Notifica aprovacao de orcamento")
}

ContainerDb(postgres, "PostgreSQL", "Banco relacional", "Dados de OS, cliente, veiculo, servicos e estoque")
Container(mailpit, "Mailpit", "SMTP/Web", "Captura local de emails")

Rel(user, rest, "Consome endpoints", "HTTP/JSON")
Rel(rest, serviceorder, "Executa casos de uso")
Rel(serviceorder, customer, "Cliente da OS")
Rel(serviceorder, vehicle, "Veiculo da OS")
Rel(serviceorder, automotive, "Servicos solicitados")
Rel(serviceorder, inventory, "Pecas e insumos")
Rel(serviceorder, persistence, "Persiste OS, itens e orcamentos")
Rel(serviceorder, notification, "Solicita notificacao")

Rel(customer, postgres, "Le e grava cliente", "JPA")
Rel(vehicle, postgres, "Le e grava veiculo", "JPA")
Rel(automotive, postgres, "Le e grava servicos", "JPA")
Rel(inventory, postgres, "Le e grava estoque", "JPA")
Rel(persistence, postgres, "Le e grava OS", "JPA")
Rel(notification, mailpit, "Envia email", "SMTP")

UpdateLayoutConfig($c4ShapeInRow="3", $c4BoundaryInRow="1")
```

## Como Ler

- `api`: camada de entrada HTTP. Contem controllers, DTOs, mappers e handlers de excecao.
- `application`: camada de casos de uso. Contem services, commands e gateways/ports.
- `domain`: regras de negocio, entidades, value objects, enums e excecoes do dominio.
- `infrastructure`: adapters de saida. Contem repositories, entities JPA, mappers, gateways e configuracoes de beans.
- `shared`: recursos transversais, como seguranca, configuracao, email, swagger e tratamento global.

## Observacoes

- O modulo `serviceorder` e o principal orquestrador de fluxo de negocio, pois depende de cliente, veiculo, servicos automotivos e estoque.
- O banco PostgreSQL e externo ao container da API no Kubernetes, com deployment e service proprios.
- O Mailpit tambem e externo a API e e usado para suporte local ao envio/captura de emails.
- A versao C4 geral reduz relacionamentos para privilegiar legibilidade.
- A versao C4 focada em Ordem de Servico mostra as dependencias do fluxo mais importante do sistema.
- Caso algum visualizador nao suporte a sintaxe C4 do Mermaid, use a versao Mermaid flowchart.
