package br.com.fiap.numberone.client.application.mappers;

import br.com.fiap.numberone.client.api.dtos.requests.ClientRequest;
import br.com.fiap.numberone.client.api.dtos.responses.ClientResponse;
import br.com.fiap.numberone.client.domain.entities.Cliente;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    public Cliente toEntity(ClientRequest request) {
        return Cliente.builder()
                .nome(request.nome())
                .tipoDocumento(request.tipoDocumento())
                .documento(request.documento())
                .telefone(request.telefone())
                .endereco(request.endereco())
                .ativo(request.ativo() != null ? request.ativo() : Boolean.TRUE)
                .build();
    }

    public ClientResponse toResponse(Cliente cliente) {
        return new ClientResponse(
                cliente.getId(),
                cliente.getNome(),
                cliente.getTipoDocumento(),
                cliente.getDocumento(),
                cliente.getTelefone(),
                cliente.getEndereco(),
                cliente.getAtivo(),
                cliente.getCreatedAt(),
                cliente.getUpdatedAt()
        );
    }
}
