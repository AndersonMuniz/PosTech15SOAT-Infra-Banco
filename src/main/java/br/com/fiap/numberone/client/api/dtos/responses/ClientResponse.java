package br.com.fiap.numberone.client.api.dtos.responses;

import br.com.fiap.numberone.client.domain.enums.TipoDocumento;

import java.time.LocalDateTime;
import java.util.UUID;

public record ClientResponse(
        UUID id,
        String nome,
        TipoDocumento tipoDocumento,
        String documento,
        String email,
        String telefone,
        String endereco,
        Boolean ativo,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
