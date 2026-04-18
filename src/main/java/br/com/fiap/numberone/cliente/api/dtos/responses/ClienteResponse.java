package br.com.fiap.numberone.cliente.api.dtos.responses;

import br.com.fiap.numberone.cliente.domain.enums.TipoDocumento;

import java.time.LocalDateTime;

public record ClienteResponse(
        Long id,
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
