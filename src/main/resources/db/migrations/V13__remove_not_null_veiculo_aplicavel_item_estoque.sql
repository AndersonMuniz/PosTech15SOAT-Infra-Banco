ALTER TABLE item_estoque
    ALTER COLUMN veiculo_aplicavel DROP NOT NULL;

ALTER TABLE item_estoque
    ALTER COLUMN veiculo_aplicavel DROP DEFAULT;