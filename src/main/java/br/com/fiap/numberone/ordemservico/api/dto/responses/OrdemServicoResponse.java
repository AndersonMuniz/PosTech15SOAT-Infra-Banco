package br.com.fiap.numberone.ordemservico.api.dto.responses;

import jakarta.validation.constraints.NotNull;

public record OrdemServicoResponse(
        @NotNull(message = "Descicao inicial é obrigatoria") Long id
) {}