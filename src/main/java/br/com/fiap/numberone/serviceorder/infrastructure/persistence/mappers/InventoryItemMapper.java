package br.com.fiap.numberone.serviceorder.infrastructure.persistence.mappers;

import br.com.fiap.numberone.inventory.infrastructure.persistence.entities.InventoryItemEntity;
import br.com.fiap.numberone.serviceorder.domain.references.InventoryItem;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface InventoryItemMapper {

    default InventoryItemEntity toEntity(InventoryItem inventoryItem) {
        if (inventoryItem == null) {
            return null;
        }

        return new InventoryItemEntity(
                inventoryItem.getId(),
                inventoryItem.getCode(),
                inventoryItem.getName(),
                inventoryItem.getDescription(),
                inventoryItem.getItemType(),
                inventoryItem.getUnitOfMeasure(),
                inventoryItem.getCostPerUnit(),
                inventoryItem.getSalePrice(),
                inventoryItem.getInventoryQuantity(),
                inventoryItem.getMinimumInventoryQuantity(),
                inventoryItem.getBrand(),
                inventoryItem.getApplicableVehicle(),
                inventoryItem.getActive(),
                inventoryItem.getCreatedAt(),
                inventoryItem.getUpdatedAt()
        );
    }

    default InventoryItem toDomain(InventoryItemEntity entity) {
        if (entity == null) {
            return null;
        }

        return InventoryItem.restore(
                entity.getId(),
                entity.getCode(),
                entity.getName(),
                entity.getDescription(),
                entity.getItemType(),
                entity.getUnitOfMeasure(),
                entity.getCostPerUnit(),
                entity.getSalePrice(),
                entity.getInventoryQuantity(),
                entity.getMinimumInventoryQuantity(),
                entity.getBrand(),
                entity.getApplicableVehicle(),
                entity.getActive(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}
