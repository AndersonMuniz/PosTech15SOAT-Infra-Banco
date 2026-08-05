# Adapters de persistência

`adapters/java/` preserva as entidades JPA, repositories Spring Data, mappers e implementações de gateways dos domínios:

- serviço automotivo;
- clientes;
- estoque;
- ordem de serviço;
- autenticação administrativa;
- veículos.

Os namespaces Java originais foram mantidos. Esses arquivos são adapters da aplicação e dependem das portas e entidades de domínio do serviço que os consumir. Mudanças no schema devem ser acompanhadas por uma migration versionada e pela atualização dos adapters afetados.
