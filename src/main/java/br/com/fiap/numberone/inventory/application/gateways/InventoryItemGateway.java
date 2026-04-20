package br.com.fiap.numberone.inventory.application.gateways;

import br.com.fiap.numberone.inventory.domain.entities.InventoryItem;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface InventoryItemGateway {
    InventoryItem save(InventoryItem item);
    Optional<InventoryItem> findById(UUID id);
    List<InventoryItem> findAll();
}
