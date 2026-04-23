CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- Criação da tabela Cliente
CREATE TABLE cliente (
     id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
     created_at TIMESTAMP WITHOUT TIME ZONE,
     updated_at TIMESTAMP WITHOUT TIME ZONE
);

-- Criação da tabela Veiculo
CREATE TABLE veiculo (
     id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
     created_at TIMESTAMP WITHOUT TIME ZONE,
     updated_at TIMESTAMP WITHOUT TIME ZONE
);

-- Criação da tabela Ordem de Serviço
CREATE TABLE ordem_servico (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    descricao_inicial VARCHAR(255),
    descricao_diagnostico VARCHAR(255),
    descricao_diagnostico_final VARCHAR(255),
    observacao VARCHAR(255),
    id_cliente UUID,
    id_veiculo UUID,
    status VARCHAR(50),
    data_hora_entrada TIMESTAMP WITHOUT TIME ZONE,
    data_hora_prevista TIMESTAMP WITHOUT TIME ZONE,
    data_hora_entrega TIMESTAMP WITHOUT TIME ZONE,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE,

    -- Constraints de Chave Estrangeira
   CONSTRAINT fk_os_cliente FOREIGN KEY (id_cliente) REFERENCES cliente (id),
   CONSTRAINT fk_os_veiculo FOREIGN KEY (id_veiculo) REFERENCES veiculo (id)
);