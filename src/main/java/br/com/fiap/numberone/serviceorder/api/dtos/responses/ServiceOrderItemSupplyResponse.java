package br.com.fiap.numberone.serviceorder.api.dtos.responses;


import java.util.UUID;

public record ServiceOrderItemSupplyResponse(
        UUID id,
        UUID serviceOrderItemId,
        InventoryItemResponse inventoryItem,
        Integer quantityUsed
) {
    public record InventoryItemResponse (
            UUID id,
            String code,
            String name,
            String description,
            String itemType,
            String unitOfMeasure
    ) { }
}


