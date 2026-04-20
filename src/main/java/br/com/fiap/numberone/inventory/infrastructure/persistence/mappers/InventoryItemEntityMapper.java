package br.com.fiap.numberone.inventory.infrastructure.persistence.mappers;

import br.com.fiap.numberone.inventory.domain.entities.InventoryItem;
import br.com.fiap.numberone.inventory.infrastructure.persistence.entities.InventoryItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InventoryItemEntityMapper {

    InventoryItem toDomain(InventoryItemEntity entity);

    InventoryItemEntity toEntity(InventoryItem domain);
}
