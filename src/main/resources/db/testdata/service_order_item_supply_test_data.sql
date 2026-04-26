-- Test data for ServiceOrderItemSupplyController
-- Execute manually in a local/dev database.
--
-- This script creates:
-- 1. two inventory items that can be referenced by the supply endpoints
-- 2. one stock-entry movement for each item
--
-- Suggested API flow after running this script:
-- POST /api/service-order-items/{serviceOrderItemId}/supplies
-- {
--   "inventoryItemId": "11111111-1111-1111-1111-111111111111",
--   "quantityUsed": 2
-- }
--
-- PUT /api/service-order-items/{serviceOrderItemId}/supplies/{supplyId}
-- {
--   "inventoryItemId": "22222222-2222-2222-2222-222222222222",
--   "quantityUsed": 1
-- }

INSERT INTO item_estoque (
    id,
    codigo,
    nome,
    descricao,
    tipo_item,
    unidade_medida,
    custo_unitario,
    preco_venda,
    quantidade_estoque,
    estoque_minimo,
    marca,
    veiculo_aplicavel,
    ativo,
    created_at,
    updated_at
) VALUES
(
    '11111111-1111-1111-1111-111111111111',
    'EST-SUP-001',
    'Filtro de Oleo',
    'Filtro de oleo para testes de supply',
    'PECA',
    'UNIDADE',
    18.50,
    35.90,
    15,
    2,
    'Bosch',
    'UNIVERSAL',
    TRUE,
    NOW(),
    NOW()
),
(
    '22222222-2222-2222-2222-222222222222',
    'EST-SUP-002',
    'Oleo 5W30 1L',
    'Lubrificante para testes de supply',
    'LUBRIFICANTE',
    'LITRO',
    24.00,
    44.90,
    20,
    4,
    'Mobil',
    'UNIVERSAL',
    TRUE,
    NOW(),
    NOW()
);

INSERT INTO movimentacao_estoque (
    id,
    id_item_estoque,
    tipo_movimentacao,
    origem_movimentacao,
    referencia_origem_id,
    quantidade_antes,
    quantidade_depois,
    observacao,
    usuario_responsavel_id,
    created_at
) VALUES
(
    '33333333-3333-3333-3333-333333333333',
    '11111111-1111-1111-1111-111111111111',
    'ENTRADA',
    'COMPRA',
    NULL,
    0,
    15,
    'Carga inicial para testar supply de ordem de servico',
    'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa',
    NOW()
),
(
    '44444444-4444-4444-4444-444444444444',
    '22222222-2222-2222-2222-222222222222',
    'ENTRADA',
    'COMPRA',
    NULL,
    0,
    20,
    'Carga inicial para testar troca de item no update do supply',
    'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa',
    NOW()
);
