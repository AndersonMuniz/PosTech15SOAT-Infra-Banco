package br.com.fiap.numberone.serviceorder.api.dtos.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record ServiceOrderCreatedResponse(
        @JsonProperty("id")
        UUID id
) { }
