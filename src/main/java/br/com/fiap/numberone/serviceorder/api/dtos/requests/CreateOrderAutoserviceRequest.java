package br.com.fiap.numberone.serviceorder.api.dtos.requests;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record CreateOrderAutoserviceRequest(
        UUID serviceId,
        UUID serviceOrderId,
        BigDecimal value,
        Boolean optional,
        LocalDateTime startDateTime,
        LocalDateTime endDateTime
) { }
