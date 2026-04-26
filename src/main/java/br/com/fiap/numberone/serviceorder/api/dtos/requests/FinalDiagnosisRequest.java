package br.com.fiap.numberone.serviceorder.api.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record FinalDiagnosisRequest(
        @NotBlank(message = "Final diagnosis description is required") String finalDiagnosisDescription,
        @NotNull(message = "Expected date time is required") LocalDateTime expectedDateTime,
        String notes
) { }
