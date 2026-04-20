package br.com.fiap.numberone.inventory.application.services;

import br.com.fiap.numberone.inventory.application.gateways.InventoryItemGateway;
import br.com.fiap.numberone.inventory.domain.entities.InventoryItem;
import br.com.fiap.numberone.inventory.domain.exceptions.InventoryItemNotFoundException;

import java.util.List;
import java.util.UUID;

public class InventoryItemService {

    private final InventoryItemGateway inventoryItemGateway;

    public InventoryItemService(InventoryItemGateway inventoryItemGateway) {
        this.inventoryItemGateway = inventoryItemGateway;
    }

    public InventoryItem create(InventoryItem inventoryItem) {
        return inventoryItemGateway.save(inventoryItem);
    }

    public InventoryItem update(UUID id, InventoryItem inventoryItem) {
        InventoryItem currentInventoryItem = inventoryItemGateway.findById(id)
                .orElseThrow(() -> new InventoryItemNotFoundException("Item de estoque não encontrado"));

        currentInventoryItem.updateFrom(inventoryItem);

        return inventoryItemGateway.save(currentInventoryItem);
    }

    public List<InventoryItem> findAll() {
        return inventoryItemGateway.findAll();
    }

    public InventoryItem findById(UUID id) {
        return inventoryItemGateway.findById(id)
                .orElseThrow(() -> new InventoryItemNotFoundException("InventoryItem não encontrado"));
    }

}
