package br.com.fiap.numberone.serviceorder.api.dtos.responses;

import br.com.fiap.numberone.client.domain.enums.TipoDocumento;
import br.com.fiap.numberone.serviceorder.domain.enums.ServiceOrderStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record ServiceOrderResponse(
        UUID id,
        String initialDescription,
        String diagnosisDescription,
        String finalDiagnosisDescription,
        String notes,
        CustomerResponse customer,
        VehicleResponse vehicle,
        List<ServiceOrderItemResponse> serviceItems,
        ServiceOrderStatus status,
        LocalDateTime entryDateTime,
        LocalDateTime expectedDateTime,
        LocalDateTime deliveryDateTime,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public record CustomerResponse(
            UUID id,
            String name,
            TipoDocumento documentType,
            String document,
            String email,
            String phone,
            String address,
            Boolean active
    ) { }

    public record VehicleResponse(
            UUID id,
            String licensePlate,
            String brand,
            String model,
            Integer year,
            String customerId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) { }
}
