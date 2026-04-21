CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE ordem_servico_servico (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    id_ordem_servico UUID NOT NULL,
    id_servico UUID,
    valor NUMERIC(10,2),
    status VARCHAR(50),
    opcional BOOLEAN,
    data_hora_inicio TIMESTAMP WITHOUT TIME ZONE,
    data_hora_fim TIMESTAMP WITHOUT TIME ZONE,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP WITHOUT TIME ZONE,

    CONSTRAINT fk_ordem_servico_servico_ordem_servico
        FOREIGN KEY (id_ordem_servico) REFERENCES ordem_servico (id),

    CONSTRAINT fk_ordem_servico_servico_servico
        FOREIGN KEY (id_servico) REFERENCES servico_automotivo (id)
);
