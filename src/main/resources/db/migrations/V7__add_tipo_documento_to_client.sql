ALTER TABLE client ADD COLUMN tipo_documento VARCHAR(30);

UPDATE client
SET tipo_documento = 'PESSOA_FISICA'
WHERE tipo_documento IS NULL;

ALTER TABLE client
ALTER COLUMN tipo_documento SET NOT NULL;
