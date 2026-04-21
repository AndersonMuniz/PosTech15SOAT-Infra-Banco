package br.com.fiap.numberone.cliente.api.dtos.requests;

import br.com.fiap.numberone.cliente.domain.enums.TipoDocumento;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ClienteRequest(
        @NotBlank(message = "Nome e obrigatorio")
        @Size(max = 90, message = "Nome deve ter no maximo 90 caracteres")
        String nome,

        @NotNull(message = "Tipo de documento e obrigatorio")
        TipoDocumento tipoDocumento,

        @NotBlank(message = "Documento e obrigatorio")
        @Size(max = 50, message = "Documento deve ter no maximo 50 caracteres")
        String documento,

        @NotBlank(message = "Email e obrigatorio")
        @Email(message = "Email deve ser valido")
        @Size(max = 120, message = "Email deve ter no maximo 120 caracteres")
        String email,

        @NotBlank(message = "Telefone e obrigatorio")
        @Size(max = 15, message = "Telefone deve ter no maximo 15 caracteres")
        String telefone,

        @NotBlank(message = "Endereco e obrigatorio")
        @Size(max = 90, message = "Endereco deve ter no maximo 90 caracteres")
        String endereco,

        Boolean ativo
) {
}
