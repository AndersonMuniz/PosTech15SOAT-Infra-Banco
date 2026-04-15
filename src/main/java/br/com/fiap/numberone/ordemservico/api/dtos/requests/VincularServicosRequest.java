package br.com.fiap.numberone.ordemservico.api.dtos.requests;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;


public record VincularServicosRequest(
        @NotEmpty(message = "A lista de serviços não pode estar vazia")
        List<Long> servicosIds
) {}