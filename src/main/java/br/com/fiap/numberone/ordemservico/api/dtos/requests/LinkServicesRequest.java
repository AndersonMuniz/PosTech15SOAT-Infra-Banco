package br.com.fiap.numberone.ordemservico.api.dtos.requests;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record LinkServicesRequest(
        @NotEmpty(message = "Service list cannot be empty")
        List<Long> serviceIds
) { }
