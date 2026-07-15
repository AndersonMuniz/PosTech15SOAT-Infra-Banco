# Módulo ECR

Responsável por provisionar o repositório Docker utilizado pela aplicação.

## Recursos Criados

O módulo cria os seguintes recursos:

- Amazon ECR Repository
- Lifecycle Policy

---

## Estrutura

```text
modules/ecr/
├── main.tf
├── variables.tf
├── outputs.tf
└── README.md
```

---

## Variáveis

| Variável | Descrição |
|----------|-----------|
| `project_name` | Nome do projeto |

---

## Outputs

| Output | Descrição |
|----------|-----------|
| `repository_name` | Nome do repositório |
| `repository_url` | URL do repositório |

---

## Fluxo

```text
Project Name
      │
Amazon ECR
      │
Repository URL
```

---

## Exemplo de Utilização

```hcl
module "ecr" {
  source = "./modules/ecr"

  project_name = var.project_name
}
```

---

## Observações

- O repositório é utilizado pelo pipeline de deploy da aplicação.
- As imagens são enviadas utilizando tags baseadas em timestamp.
- Uma Lifecycle Policy remove imagens antigas automaticamente conforme configurado no módulo.