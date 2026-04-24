package br.com.fiap.numberone.serviceorder.api.dtos.requests;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.UUID;

public record CreateServiceOrderItemRequest(
        @NotNull(message = "serviceId is required")
        UUID serviceId,
        @NotNull(message = "serviceOrderId is required")
        UUID serviceOrderId,
        @Positive(message = "quantityUsed must be positive")
        @NotNull(message = "quantityUsed is required")
        Integer quantityUsed
) { }
