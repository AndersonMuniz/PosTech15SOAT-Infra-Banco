ALTER TABLE vehicle
    ADD COLUMN IF NOT EXISTS placa VARCHAR(10),
    ADD COLUMN IF NOT EXISTS marca VARCHAR(100),
    ADD COLUMN IF NOT EXISTS modelo VARCHAR(100),
    ADD COLUMN IF NOT EXISTS ano INTEGER,
    ADD COLUMN IF NOT EXISTS id_client UUID;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1 FROM pg_constraint WHERE conname = 'fk_vehicle_client'
    ) THEN
        ALTER TABLE vehicle
            ADD CONSTRAINT fk_vehicle_client
                FOREIGN KEY (id_client) REFERENCES client (id);
    END IF;
END $$;

CREATE UNIQUE INDEX IF NOT EXISTS uk_vehicle_placa_ci
    ON vehicle (LOWER(placa));
