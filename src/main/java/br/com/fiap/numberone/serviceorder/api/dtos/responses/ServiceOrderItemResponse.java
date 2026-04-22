package br.com.fiap.numberone.serviceorder.api.dtos.responses;

import br.com.fiap.numberone.serviceorder.domain.enums.OrderItemStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record ServiceOrderItemResponse(
        UUID id,
        UUID serviceOrderId,
        AutomotiveServiceResponse automotiveService,
        BigDecimal value,
        OrderItemStatus status,
        Boolean optional,
        LocalDateTime startDateTime,
        LocalDateTime endDateTime,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public record AutomotiveServiceResponse (
            UUID id,
            String code,
            String name,
            String description,
            String serviceType,
            BigDecimal baseValue,
            Integer estimatedTimeMinutes,
            Boolean active
    ) { }
}


