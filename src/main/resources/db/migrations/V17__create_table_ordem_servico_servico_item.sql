CREATE TABLE ordem_servico_servico_item (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    id_ordem_servico_servico UUID NOT NULL,
    id_item_estoque UUID,
    quantidade_usada BIGINT,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP WITHOUT TIME ZONE,

    CONSTRAINT fk_ordem_servico_servico_item_ordem_servico_servico
        FOREIGN KEY (id_ordem_servico_servico) REFERENCES ordem_servico_servico (id),

    CONSTRAINT fk_ordem_servico_servico_item_item_estoque
        FOREIGN KEY (id_item_estoque) REFERENCES item_estoque (id)
);
