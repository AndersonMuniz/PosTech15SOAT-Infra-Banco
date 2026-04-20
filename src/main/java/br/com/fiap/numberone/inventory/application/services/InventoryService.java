package br.com.fiap.numberone.inventory.application.services;

import br.com.fiap.numberone.inventory.application.gateways.InventoryItemGateway;
import br.com.fiap.numberone.inventory.application.gateways.InventoryMovementGateway;

public class InventoryService {

    private final InventoryItemGateway itemGateway;
    private final InventoryMovementGateway movimentacaoGateway;

    public InventoryService(InventoryItemGateway itemGateway,
                           InventoryMovementGateway movimentacaoGateway) {
        this.itemGateway = itemGateway;
        this.movimentacaoGateway = movimentacaoGateway;
    }
}
