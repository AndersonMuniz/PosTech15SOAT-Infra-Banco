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
        @Positive(message = "value must be positive")
        @NotNull(message = "value is required")
        BigDecimal value,
        Boolean optional
) { }
