package br.com.fiap.numberone.inventory.domain.entities;

import br.com.fiap.numberone.inventory.domain.enums.InventoryMovementOrigin;
import br.com.fiap.numberone.inventory.domain.enums.InventoryMovementType;

import java.time.LocalDateTime;
import java.util.UUID;

public class InventoryMovement {

    private UUID id;
    private UUID inventoryItemId;
    private InventoryMovementType movementType;
    private InventoryMovementOrigin movementOrigin;
    private UUID originReferenceId;
    private int quantityBefore;
    private int quantityAfter;
    private String observation;
    private UUID responsibleUserId;
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

    public InventoryMovementType getMovementType() {
        return movementType;
    }

    public void setMovementType(InventoryMovementType movementType) {
        this.movementType = movementType;
    }

    public InventoryMovementOrigin getMovementOrigin() {
        return movementOrigin;
    }

    public void setMovementOrigin(InventoryMovementOrigin movementOrigin) {
        this.movementOrigin = movementOrigin;
    }

    public UUID getOriginReferenceId() {
        return originReferenceId;
    }

    public void setOriginReferenceId(UUID originReferenceId) {
        this.originReferenceId = originReferenceId;
    }

    public int getQuantityBefore() {
        return quantityBefore;
    }

    public void setQuantityBefore(int quantityBefore) {
        this.quantityBefore = quantityBefore;
    }

    public int getQuantityAfter() {
        return quantityAfter;
    }

    public void setQuantityAfter(int quantityAfter) {
        this.quantityAfter = quantityAfter;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public UUID getResponsibleUserId() {
        return responsibleUserId;
    }

    public void setResponsibleUserId(UUID responsibleUserId) {
        this.responsibleUserId = responsibleUserId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}


