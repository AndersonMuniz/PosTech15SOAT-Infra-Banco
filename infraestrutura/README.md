# Infraestrutura e CI/CD

Esta pasta contém a automação da esteira de CI/CD para AWS EKS e os artefatos de infraestrutura usados pelo pipeline.

## GitHub Actions

O workflow `.github/workflows/ci-cd-aws-eks.yml` executa:

1. Build da aplicação Java com `./mvnw clean verify`.
2. Execução automática dos testes no build Maven.
3. Build da imagem Docker.
4. Push da imagem para o Amazon ECR.
5. Deploy da infraestrutura do banco PostgreSQL no Amazon RDS via Terraform.
6. Aplicação dos manifests Kubernetes no cluster EKS.

## Secrets e variáveis esperados

Configure no GitHub:

- `AWS_ROLE_TO_ASSUME`: role IAM usada pelo GitHub OIDC.
- `RDS_USERNAME` e `RDS_PASSWORD`: credenciais do PostgreSQL.
- `AWS_VPC_ID`: VPC compartilhada entre EKS e RDS.
- `AWS_PRIVATE_SUBNET_IDS_JSON`: lista JSON de subnets privadas, por exemplo `["subnet-1","subnet-2"]`.
- `EKS_NODE_SECURITY_GROUP_IDS_JSON`: lista JSON dos security groups autorizados no RDS.
- `SPRING_DATASOURCE_URL`: URL JDBC do RDS, por exemplo `jdbc:postgresql://host:5432/numberone`.

Variáveis opcionais:

- `AWS_REGION` (padrão `us-east-1`).
- `ECR_REPOSITORY` (padrão `numberone`).
- `EKS_CLUSTER_NAME` (padrão `numberone-eks`).
- `K8S_NAMESPACE` (padrão `numberone`).
