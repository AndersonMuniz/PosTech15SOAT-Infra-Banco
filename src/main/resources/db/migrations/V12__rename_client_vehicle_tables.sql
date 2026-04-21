-- Backward-compatible rename for previous Portuguese names
DO $$
BEGIN
    IF to_regclass('public.cliente') IS NOT NULL AND to_regclass('public.client') IS NULL THEN
        ALTER TABLE cliente RENAME TO client;
    END IF;

    IF to_regclass('public.veiculo') IS NOT NULL AND to_regclass('public.vehicle') IS NULL THEN
        ALTER TABLE veiculo RENAME TO vehicle;
    END IF;

    IF EXISTS (
        SELECT 1
        FROM information_schema.columns
        WHERE table_schema = 'public' AND table_name = 'ordem_servico' AND column_name = 'id_cliente'
    ) THEN
        ALTER TABLE ordem_servico RENAME COLUMN id_cliente TO id_client;
    END IF;

    IF EXISTS (
        SELECT 1
        FROM information_schema.columns
        WHERE table_schema = 'public' AND table_name = 'ordem_servico' AND column_name = 'id_veiculo'
    ) THEN
        ALTER TABLE ordem_servico RENAME COLUMN id_veiculo TO id_vehicle;
    END IF;

    IF to_regclass('public.ordem_servico') IS NOT NULL THEN
        IF EXISTS (
            SELECT 1 FROM pg_constraint
            WHERE conname = 'fk_os_cliente'
        ) THEN
            ALTER TABLE ordem_servico RENAME CONSTRAINT fk_os_cliente TO fk_os_client;
        END IF;

        IF EXISTS (
            SELECT 1 FROM pg_constraint
            WHERE conname = 'fk_os_veiculo'
        ) THEN
            ALTER TABLE ordem_servico RENAME CONSTRAINT fk_os_veiculo TO fk_os_vehicle;
        END IF;
    END IF;
END $$;
