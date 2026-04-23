ALTER TABLE veiculo
    ADD COLUMN IF NOT EXISTS placa VARCHAR(10),
    ADD COLUMN IF NOT EXISTS marca VARCHAR(100),
    ADD COLUMN IF NOT EXISTS modelo VARCHAR(100),
    ADD COLUMN IF NOT EXISTS ano INTEGER,
    ADD COLUMN IF NOT EXISTS id_cliente UUID;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1 FROM pg_constraint WHERE conname = 'fk_veiculo_cliente'
    ) THEN
        ALTER TABLE veiculo
            ADD CONSTRAINT fk_veiculo_cliente
                FOREIGN KEY (id_cliente) REFERENCES cliente (id);
    END IF;
END $$;

CREATE UNIQUE INDEX IF NOT EXISTS uk_veiculo_placa_ci
    ON veiculo (LOWER(placa));
