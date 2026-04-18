ALTER TABLE cliente ADD COLUMN tipo_documento VARCHAR(30);

UPDATE cliente
SET tipo_documento = 'PESSOA_FISICA'
WHERE tipo_documento IS NULL;

ALTER TABLE cliente
ALTER COLUMN tipo_documento SET NOT NULL;
