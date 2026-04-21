package br.com.fiap.numberone.client.infrastructure.persistence.mappers;

import br.com.fiap.numberone.client.domain.entities.Client;
import br.com.fiap.numberone.client.infrastructure.persistence.entities.ClientEntity;
import org.springframework.stereotype.Component;

@Component
public class ClientEntityMapper {

    public ClientEntity toEntity(Client domain) {
        return ClientEntity.builder()
                .id(domain.getId())
                .nome(domain.getNome())
                .tipoDocumento(domain.getTipoDocumento())
                .documento(domain.getDocumento())
                .telefone(domain.getTelefone())
                .endereco(domain.getEndereco())
                .ativo(domain.getAtivo())
                .createdAt(domain.getCreatedAt())
                .updatedAt(domain.getUpdatedAt())
                .build();
    }

    public Client toDomain(ClientEntity entity) {
        return Client.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .tipoDocumento(entity.getTipoDocumento())
                .documento(entity.getDocumento())
                .telefone(entity.getTelefone())
                .endereco(entity.getEndereco())
                .ativo(entity.getAtivo())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
