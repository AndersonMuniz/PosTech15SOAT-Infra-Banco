package br.com.fiap.numberone.cliente.application.mappers;

import br.com.fiap.numberone.cliente.api.dtos.requests.ClienteRequest;
import br.com.fiap.numberone.cliente.api.dtos.responses.ClienteResponse;
import br.com.fiap.numberone.cliente.domain.entities.Cliente;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {

    public Cliente toEntity(ClienteRequest request) {
        return Cliente.builder()
                .nome(request.nome())
                .tipoDocumento(request.tipoDocumento())
                .documento(request.documento())
                .email(request.email())
                .telefone(request.telefone())
                .endereco(request.endereco())
                .ativo(request.ativo() != null ? request.ativo() : Boolean.TRUE)
                .build();
    }

    public ClienteResponse toResponse(Cliente cliente) {
        return new ClienteResponse(
                cliente.getId(),
                cliente.getNome(),
                cliente.getTipoDocumento(),
                cliente.getDocumento(),
                cliente.getEmail(),
                cliente.getTelefone(),
                cliente.getEndereco(),
                cliente.getAtivo(),
                cliente.getCreatedAt(),
                cliente.getUpdatedAt()
        );
    }
}
