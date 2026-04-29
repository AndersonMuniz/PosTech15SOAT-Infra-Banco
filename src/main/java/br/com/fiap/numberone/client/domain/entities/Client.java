package br.com.fiap.numberone.client.domain.entities;

import br.com.fiap.numberone.client.domain.enums.TipoDocumento;
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
public class Client {

    private UUID id;
    private String nome;
    private TipoDocumento tipoDocumento;
    private String documento;
    private String email;
    private String telefone;
    private String endereco;
    private Boolean ativo;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Client updateFrom(Client novoClient) {
        return Client.builder()
                .id(this.id)
                .nome(novoClient.nome)
                .tipoDocumento(novoClient.tipoDocumento)
                .documento(novoClient.documento)
                .email(novoClient.email)
                .telefone(novoClient.telefone)
                .endereco(novoClient.endereco)
                .ativo(novoClient.ativo)
                .createdAt(this.createdAt)
                .updatedAt(LocalDateTime.now())
                .build();
    }
}
