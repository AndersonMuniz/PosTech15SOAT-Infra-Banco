package br.com.fiap.numberone.serviceorder.api.dtos.requests;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.UUID;

public record CreateServiceOrderBudgetRequest(
        @NotNull(message = "serviceOrderId is required")
        UUID serviceOrderId,
        @Positive(message = "quotedAmount must be positive")
        BigDecimal quotedAmount
) { }
