ALTER TABLE servico RENAME TO servico_automotivo;

ALTER TABLE servico_automotivo
ADD COLUMN nome VARCHAR(150) NOT NULL DEFAULT '',
ADD COLUMN tipo_servico VARCHAR(50);

ALTER TABLE servico_automotivo
RENAME COLUMN tempo_estimado_minuto TO tempo_estimado_minutos;
