CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- Criação da tabela Client
CREATE TABLE client (
     id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
     created_at TIMESTAMP WITHOUT TIME ZONE,
     updated_at TIMESTAMP WITHOUT TIME ZONE
);

-- Criação da tabela Vehicle
CREATE TABLE vehicle (
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
    id_client UUID,
    id_vehicle UUID,
    status VARCHAR(50),
    data_hora_entrada TIMESTAMP WITHOUT TIME ZONE,
    data_hora_prevista TIMESTAMP WITHOUT TIME ZONE,
    data_hora_entrega TIMESTAMP WITHOUT TIME ZONE,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE,

    -- Constraints de Chave Estrangeira
   CONSTRAINT fk_os_client FOREIGN KEY (id_client) REFERENCES client (id),
   CONSTRAINT fk_os_vehicle FOREIGN KEY (id_vehicle) REFERENCES vehicle (id)
);