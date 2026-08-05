# Variáveis de ambiente

## Execução local

| Variável | Finalidade | Exemplo |
|---|---|---|
| `POSTGRES_DB` | Nome do banco criado pelo container | `numberone` |
| `POSTGRES_USER` | Usuário proprietário | `numberone` |
| `POSTGRES_PASSWORD` | Senha do usuário | sem padrão seguro |
| `POSTGRES_PORT` | Porta publicada no host | `5432` |

## Aplicação cliente

| Variável | Finalidade |
|---|---|
| `DB_URL` | URL JDBC completa |
| `DB_USERNAME` | Usuário da conexão |
| `DB_PASSWORD` | Senha da conexão |

Nunca versione `.env`, `terraform.tfvars`, state do Terraform ou senhas reais. Em CI/CD, injete segredos pelo cofre de segredos da plataforma.
