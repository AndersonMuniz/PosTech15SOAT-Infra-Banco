CREATE TABLE item (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    nome VARCHAR(100) NOT NULL,
    descricao VARCHAR(255),
    valor_base NUMERIC(10,2) NOT NULL,
    tipo_item VARCHAR(50) NOT NULL,
    unidade_medida VARCHAR(20) NOT NULL,
    quantidade_estoque INT NOT NULL DEFAULT 0
);