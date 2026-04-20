package br.com.fiap.numberone.cliente.domain.entities;

import br.com.fiap.numberone.cliente.domain.enums.TipoDocumento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    private UUID id;
    private String nome;
    private TipoDocumento tipoDocumento;
    private String documento;
    private String telefone;
    private String endereco;
    private Boolean ativo;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Cliente updateFrom(Cliente novoCliente) {
        return Cliente.builder()
                .id(this.id)
                .nome(novoCliente.nome)
                .tipoDocumento(novoCliente.tipoDocumento)
                .documento(novoCliente.documento)
                .telefone(novoCliente.telefone)
                .endereco(novoCliente.endereco)
                .ativo(novoCliente.ativo)
                .createdAt(this.createdAt)
                .updatedAt(LocalDateTime.now())
                .build();
    }
}
