CREATE TABLE ordem_servico_orcamento (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    id_ordem_servico UUID NOT NULL,
    valor_proposto NUMERIC(19,2),
    valor_aprovado NUMERIC(19,2),
    status VARCHAR(30) NOT NULL,
    enviado_em TIMESTAMP WITHOUT TIME ZONE,
    aprovado_em TIMESTAMP WITHOUT TIME ZONE,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT fk_orcamento_ordem_servico FOREIGN KEY (id_ordem_servico) REFERENCES ordem_servico (id)
);
