package br.com.fiap.numberone.inventory.api.mappers;

import br.com.fiap.numberone.inventory.api.dto.responses.InventoryMovementResponse;
import br.com.fiap.numberone.inventory.domain.entities.InventoryMovement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InventoryMovementMapper {

    @Mapping(target = "movementType", source = "movementType")
    @Mapping(target = "movementOrigin", source = "movementOrigin")
    InventoryMovementResponse toResponse(InventoryMovement domain);
}