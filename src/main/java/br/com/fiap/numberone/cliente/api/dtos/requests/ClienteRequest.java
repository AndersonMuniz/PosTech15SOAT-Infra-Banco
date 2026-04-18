package br.com.fiap.numberone.cliente.api.dtos.requests;

import br.com.fiap.numberone.cliente.domain.enums.TipoDocumento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ClienteRequest(
        @NotBlank(message = "Nome é obrigatório")
        @Size(max = 90, message = "Nome deve ter no máximo 90 caracteres")
        String nome,

        @NotNull(message = "Tipo de documento é obrigatório")
        TipoDocumento tipoDocumento,

        @NotBlank(message = "Documento é obrigatório")
        @Size(max = 50, message = "Documento deve ter no máximo 50 caracteres")
        String documento,

        @NotBlank(message = "Telefone é obrigatório")
        @Size(max = 15, message = "Telefone deve ter no máximo 15 caracteres")
        String telefone,

        @NotBlank(message = "Endereço é obrigatório")
        @Size(max = 90, message = "Endereço deve ter no máximo 90 caracteres")
        String endereco,

        Boolean ativo
) {
}
