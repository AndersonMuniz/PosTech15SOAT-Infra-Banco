package br.com.fiap.numberone.vehicle.api.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record VehicleRequest(
        @NotBlank(message = "Placa é obrigatória")
        String placa,

        @NotBlank(message = "Marca é obrigatória")
        String marca,

        @NotBlank(message = "Modelo é obrigatório")
        String modelo,

        @NotNull(message = "Ano é obrigatório")
        Integer ano,

        @NotNull(message = "idClient é obrigatório")
        UUID idClient
) {
}
