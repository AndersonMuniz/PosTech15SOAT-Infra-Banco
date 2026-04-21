package br.com.fiap.numberone.serviceorder.api.dtos.requests;

import jakarta.validation.constraints.NotBlank;

public record FinalDiagnosisRequest(
        @NotBlank(message = "Final diagnosis description is required") String finalDiagnosisDescription,
        String notes
) { }
