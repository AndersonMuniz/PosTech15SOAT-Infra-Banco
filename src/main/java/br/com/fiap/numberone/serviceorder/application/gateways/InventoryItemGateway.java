package br.com.fiap.numberone.serviceorder.application.gateways;

import br.com.fiap.numberone.serviceorder.domain.references.InventoryItem;

import java.util.Optional;
import java.util.UUID;

public interface InventoryItemGateway {

    InventoryItem save(InventoryItem inventoryItem);
    Optional<InventoryItem> findById(UUID id);
    Optional<InventoryItem> findByCode(String code);
}
