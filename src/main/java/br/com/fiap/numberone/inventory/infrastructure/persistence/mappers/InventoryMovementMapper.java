//package br.com.fiap.numberone.inventory.infrastructure.persistence.mappers;
//
//import br.com.fiap.numberone.inventory.domain.entities.InventoryMovement;
//import br.com.fiap.numberone.inventory.domain.enums.InventoryMovementType;
//import br.com.fiap.numberone.inventory.infrastructure.persistence.entities.InventoryMovementEntity;
//
//public class InventoryMovementMapper {
//
//    public static InventoryMovementEntity toEntity(InventoryMovement domain) {
//        InventoryMovementEntity e = new InventoryMovementEntity();
//        e.setId(domain.getId());
//        e.setInventoryItemId(domain.getInventoryItemId());
//        e.setQuantity(domain.getQuantity());
//        e.setReason(domain.getReason());
//        e.setMovementType(domain.getType().name());
//        e.setCreatedAt(domain.getCreatedAt());
//        return e;
//    }
//
//    public static InventoryMovement toDomain(InventoryMovementEntity entity) {
//        InventoryMovement m = new InventoryMovement();
//        m.setId(entity.getId());
//        m.setInventoryItemId(entity.getInventoryItemId());
//        m.setQuantity(entity.getQuantity());
//        m.setReason(entity.getReason());
//        m.setType(InventoryMovementType.valueOf(entity.getMovementType()));
//        m.setCreatedAt(entity.getCreatedAt());
//        return m;
//    }
//}
