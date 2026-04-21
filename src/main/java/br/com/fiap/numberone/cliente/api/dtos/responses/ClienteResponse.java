package br.com.fiap.numberone.cliente.api.dtos.responses;

import br.com.fiap.numberone.cliente.domain.enums.TipoDocumento;

import java.time.LocalDateTime;
import java.util.UUID;

public record ClienteResponse(
        UUID id,
        String nome,
        TipoDocumento tipoDocumento,
        String documento,
        String telefone,
        String endereco,
        Boolean ativo,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
