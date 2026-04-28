package br.com.fiap.numberone.serviceorder.api.dtos.responses;

import br.com.fiap.numberone.serviceorder.domain.enums.OrderItemStatus;
import br.com.fiap.numberone.serviceorder.domain.enums.ServiceOrderBudgetStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record ServiceOrderTrackingResponse(
        UUID id,
        String initialDescription,
        String finalDiagnosisDescription,
        VehicleResponse vehicle,
        ServiceOrderStatusResponse status,
        LocalDateTime entryDateTime,
        LocalDateTime expectedDateTime,
        LocalDateTime deliveryDateTime,
        BudgetResponse budget,
        List<ServiceItemResponse> serviceItems
) {

    public record VehicleResponse(
            String licensePlate,
            String brand,
            String model,
            Integer year
    ) {
    }

    public record BudgetResponse(
            BigDecimal quotedAmount,
            BigDecimal approvedAmount,
            ServiceOrderBudgetStatus status,
            LocalDateTime sentAt,
            LocalDateTime approvedAt
    ) {
    }

    public record ServiceItemResponse(
            UUID id,
            String serviceName,
            String serviceType,
            OrderItemStatus status,
            Boolean optional,
            LocalDateTime startDateTime,
            LocalDateTime endDateTime
    ) {
    }
}
