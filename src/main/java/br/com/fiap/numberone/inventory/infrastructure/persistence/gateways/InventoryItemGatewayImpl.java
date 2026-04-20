package br.com.fiap.numberone.inventory.infrastructure.persistence.gateways;

import br.com.fiap.numberone.inventory.application.gateways.InventoryItemGateway;
import br.com.fiap.numberone.inventory.domain.entities.InventoryItem;
import br.com.fiap.numberone.inventory.infrastructure.persistence.entities.InventoryItemEntity;
import br.com.fiap.numberone.inventory.infrastructure.persistence.mappers.InventoryItemEntityMapper;
import br.com.fiap.numberone.inventory.infrastructure.persistence.repositories.InventoryItemRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class InventoryItemGatewayImpl implements InventoryItemGateway {

    private final InventoryItemRepository inventoryItemRepository;
    private final InventoryItemEntityMapper inventoryItemEntityMapper;

    public InventoryItemGatewayImpl(InventoryItemRepository inventoryItemRepository, InventoryItemEntityMapper inventoryItemEntityMapper) {
        this.inventoryItemRepository = inventoryItemRepository;
        this.inventoryItemEntityMapper = inventoryItemEntityMapper;
    }

    @Override
    public InventoryItem save(InventoryItem inventoryItem) {
        InventoryItemEntity inventoryItemEntity = inventoryItemEntityMapper.toEntity(inventoryItem);
        inventoryItemRepository.save(inventoryItemEntity);
        return inventoryItemEntityMapper.toDomain(inventoryItemEntity);
    }

    @Override
    public Optional<InventoryItem> findById(UUID id) {
        return inventoryItemRepository.findById(id)
                .map(inventoryItemEntityMapper::toDomain);
    }

    @Override
    public List<InventoryItem> findAll() {
        return inventoryItemRepository.findAll()
                .stream()
                .map(inventoryItemEntityMapper::toDomain)
                .toList();
    }
}
