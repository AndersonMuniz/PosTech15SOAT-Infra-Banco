package br.com.fiap.numberone.serviceorder.api.dtos.responses;

import br.com.fiap.numberone.serviceorder.domain.enums.ServiceOrderBudgetStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record ServiceOrderBudgetResponse(
        UUID id,
        UUID serviceOrderId,
        BigDecimal quotedAmount,
        BigDecimal approvedAmount,
        ServiceOrderBudgetStatus status,
        LocalDateTime sentAt,
        LocalDateTime approvedAt,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
