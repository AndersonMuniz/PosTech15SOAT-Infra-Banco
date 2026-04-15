CREATE TABLE movimentacao_estoque (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    id_item UUID NOT NULL,
    tipo_movimentacao VARCHAR(20) NOT NULL,
    quantidade INT NOT NULL,
    motivo VARCHAR(255),
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),

    CONSTRAINT fk_item FOREIGN KEY (id_item) REFERENCES item(id)
);