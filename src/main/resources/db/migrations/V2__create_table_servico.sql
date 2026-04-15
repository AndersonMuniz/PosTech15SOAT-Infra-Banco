CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE servico (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    codigo VARCHAR(50) NOT NULL,
    descricao VARCHAR(255),
    valor_base NUMERIC(10,2) NOT NULL,
    tempo_estimado_minuto INT NOT NULL,
    ativo BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP
);