package br.com.fiap.numberone.serviceorder.api.dtos.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record ServiceOrderStatusConsultResponse(
        @JsonProperty("id")
        UUID id,
        @JsonProperty("status")
        ServiceOrderStatusResponse status
) { }
