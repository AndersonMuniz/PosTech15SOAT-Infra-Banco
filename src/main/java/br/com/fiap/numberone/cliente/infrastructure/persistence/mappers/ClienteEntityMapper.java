package br.com.fiap.numberone.cliente.infrastructure.persistence.mappers;

import br.com.fiap.numberone.cliente.domain.entities.Cliente;
import br.com.fiap.numberone.cliente.infrastructure.persistence.entities.ClienteEntity;
import org.springframework.stereotype.Component;

@Component
public class ClienteEntityMapper {

    public ClienteEntity toEntity(Cliente domain) {
        return ClienteEntity.builder()
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

    public Cliente toDomain(ClienteEntity entity) {
        return Cliente.builder()
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
