package br.com.fiap.numberone.vehicle.api.dtos.responses;

import java.time.LocalDateTime;
import java.util.UUID;

public record VehicleResponse(
        UUID id,
        String placa,
        String marca,
        String modelo,
        Integer ano,
        UUID idClient,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
