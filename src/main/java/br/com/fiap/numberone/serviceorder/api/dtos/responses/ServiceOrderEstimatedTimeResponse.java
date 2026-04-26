package br.com.fiap.numberone.serviceorder.api.dtos.responses;

import java.time.LocalDateTime;
import java.util.UUID;

public record ServiceOrderEstimatedTimeResponse(
        UUID serviceOrderId,
        Integer totalEstimatedMinutes,
        LocalDateTime suggestedExpectedDateTime
) {
}
