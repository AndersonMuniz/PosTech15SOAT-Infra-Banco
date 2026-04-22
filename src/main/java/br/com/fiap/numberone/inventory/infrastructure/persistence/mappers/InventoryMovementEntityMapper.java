package br.com.fiap.numberone.inventory.infrastructure.persistence.mappers;

import br.com.fiap.numberone.inventory.domain.entities.InventoryMovement;
import br.com.fiap.numberone.inventory.infrastructure.persistence.entities.InventoryMovementEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InventoryMovementEntityMapper {

    InventoryMovement toDomain(InventoryMovementEntity entity);

    InventoryMovementEntity toEntity(InventoryMovement domain);
}