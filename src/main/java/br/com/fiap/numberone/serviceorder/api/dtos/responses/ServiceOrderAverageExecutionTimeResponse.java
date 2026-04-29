package br.com.fiap.numberone.serviceorder.api.dtos.responses;

import java.util.UUID;

public record ServiceOrderAverageExecutionTimeResponse(
        UUID serviceOrderId,
        Integer completedServices,
        Integer pendingServices,
        Integer inProgressServices,
        Integer cancelledServices,
        Integer waitingServices,
        Long averageExecutionMinutes
) {
}
