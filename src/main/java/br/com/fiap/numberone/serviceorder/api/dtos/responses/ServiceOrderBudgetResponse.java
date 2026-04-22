package br.com.fiap.numberone.serviceorder.api.dtos.responses;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record ServiceOrderBudgetResponse(
        UUID id,
        UUID serviceOrderId,
        BigDecimal quotedAmount,
        BigDecimal approvedAmount,
        LocalDateTime sentAt,
        LocalDateTime approvedAt,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

}


