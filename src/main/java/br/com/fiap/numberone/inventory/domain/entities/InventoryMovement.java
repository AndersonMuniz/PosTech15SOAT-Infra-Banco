package br.com.fiap.numberone.inventory.domain.entities;

import br.com.fiap.numberone.inventory.domain.enums.InventoryMovementType;

import java.time.LocalDateTime;
import java.util.UUID;

public class InventoryMovement {
    private UUID id;
    private UUID inventoryItemId;
    private InventoryMovementType type;
    private int quantity;
    private String reason;
    private LocalDateTime createdAt;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getInventoryItemId() {
        return inventoryItemId;
    }

    public void setInventoryItemId(UUID inventoryItemId) {
        this.inventoryItemId = inventoryItemId;
    }

    public InventoryMovementType getType() {
        return type;
    }

    public void setType(InventoryMovementType type) {
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
