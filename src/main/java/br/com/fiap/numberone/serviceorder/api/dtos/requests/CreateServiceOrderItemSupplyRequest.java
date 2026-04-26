package br.com.fiap.numberone.serviceorder.api.dtos.requests;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public record CreateServiceOrderItemSupplyRequest(
        @NotNull(message = "inventoryItemId is required")
        UUID inventoryItemId,
        @NotNull(message = "quantityUsed is required")
        @Positive(message = "quantityUsed must be positive")
        Integer quantityUsed
) { }
