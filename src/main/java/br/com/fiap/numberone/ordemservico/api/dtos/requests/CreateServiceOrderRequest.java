package br.com.fiap.numberone.ordemservico.api.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record CreateServiceOrderRequest(
        @NotBlank(message = "Initial description is required") String initialDescription,
        @NotBlank(message = "Diagnosis description is required") String diagnosisDescription,
        String notes,
        @NotNull(message = "Customer is required") UUID customerId,
        @NotNull(message = "Vehicle is required") UUID vehicleId,
        @NotNull(message = "Entry date and time is required") LocalDateTime entryDateTime
) { }
