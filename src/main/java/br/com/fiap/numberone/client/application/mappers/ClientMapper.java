package br.com.fiap.numberone.client.application.mappers;

import br.com.fiap.numberone.client.api.dtos.requests.ClientRequest;
import br.com.fiap.numberone.client.api.dtos.responses.ClientResponse;
import br.com.fiap.numberone.client.domain.entities.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {


    public Client toEntity(ClientRequest request) {
        return Client.builder()
                .nome(request.nome())
                .tipoDocumento(request.tipoDocumento())
                .documento(request.documento())
                .telefone(request.telefone())
                .endereco(request.endereco())
                .ativo(request.ativo() != null ? request.ativo() : Boolean.TRUE)
                .build();
    }

    public ClientResponse toResponse(Client client) {
        return new ClientResponse(
                client.getId(),
                client.getNome(),
                client.getTipoDocumento(),
                client.getDocumento(),
                client.getTelefone(),
                client.getEndereco(),
                client.getAtivo(),
                client.getCreatedAt(),
                client.getUpdatedAt()
        );
    }
}
