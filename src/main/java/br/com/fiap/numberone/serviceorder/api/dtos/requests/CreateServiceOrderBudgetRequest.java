package br.com.fiap.numberone.serviceorder.api.dtos.requests;

import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record CreateServiceOrderBudgetRequest(
        @Positive(message = "quotedAmount must be positive")
        BigDecimal quotedAmount
) { }
