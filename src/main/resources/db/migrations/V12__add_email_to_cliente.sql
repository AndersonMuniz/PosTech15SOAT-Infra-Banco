ALTER TABLE cliente ADD COLUMN email VARCHAR(120);

UPDATE cliente
SET email = documento || '@pending.local'
WHERE email IS NULL;

ALTER TABLE cliente
ALTER COLUMN email SET NOT NULL;
