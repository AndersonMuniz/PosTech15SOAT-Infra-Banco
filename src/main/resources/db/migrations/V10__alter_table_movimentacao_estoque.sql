ALTER TABLE movimentacao_estoque
RENAME COLUMN id_item TO id_item_estoque;

ALTER TABLE movimentacao_estoque
ADD COLUMN origem_movimentacao VARCHAR(50),
ADD COLUMN referencia_origem_id UUID,
ADD COLUMN quantidade_antes INTEGER,
ADD COLUMN quantidade_depois INTEGER,
ADD COLUMN observacao TEXT,
ADD COLUMN usuario_responsavel_id UUID;

ALTER TABLE movimentacao_estoque
DROP COLUMN quantidade;

ALTER TABLE movimentacao_estoque
ADD CONSTRAINT chk_movimentacao_estoque_tipo_movimentacao
CHECK (tipo_movimentacao IN (
    'ENTRADA',
    'BAIXA',
    'AJUSTE'
));

ALTER TABLE movimentacao_estoque
ADD CONSTRAINT chk_movimentacao_estoque_origem_movimentacao
CHECK (origem_movimentacao IN (
    'COMPRA',
    'ORDEM_SERVICO',
    'AJUSTE_MANUAL',
    'DEVOLUCAO',
    'PERDA',
    'INVENTARIO'
));