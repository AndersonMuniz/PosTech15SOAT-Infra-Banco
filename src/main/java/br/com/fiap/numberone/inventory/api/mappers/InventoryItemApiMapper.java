package br.com.fiap.numberone.inventory.api.mappers;

import br.com.fiap.numberone.inventory.api.dto.requests.InventoryItemRequest;
import br.com.fiap.numberone.inventory.api.dto.responses.InventoryItemResponse;
import br.com.fiap.numberone.inventory.domain.entities.InventoryItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InventoryItemApiMapper {

    InventoryItem toDomain(InventoryItemRequest request);

    InventoryItemResponse toResponse(InventoryItem domain);
}