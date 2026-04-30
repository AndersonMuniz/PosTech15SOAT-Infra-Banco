package br.com.fiap.numberone.client.infrastructure.persistence.mappers;

import br.com.fiap.numberone.client.domain.entities.Client;
import br.com.fiap.numberone.client.domain.enums.TipoDocumento;
import br.com.fiap.numberone.client.infrastructure.persistence.entities.ClientEntity;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClienteEntityMapperTest {

    private final ClientEntityMapper mapper = new ClientEntityMapper();

    @Test
    void deveMapearDomainParaEntity() {
        UUID id = UUID.randomUUID();
        LocalDateTime createdAt = LocalDateTime.now().minusDays(2);
        LocalDateTime updatedAt = LocalDateTime.now().minusDays(1);

        Client domain = Client.builder()
                .id(id)
                .nome("Nome")
                .tipoDocumento(TipoDocumento.PESSOA_FISICA)
                .documento("52998224725")
                .telefone("11999999999")
                .endereco("Rua")
                .ativo(true)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();

        ClientEntity entity = mapper.toEntity(domain);

        assertEquals(id, entity.getId());
        assertEquals("Nome", entity.getNome());
        assertEquals(createdAt, entity.getCreatedAt());
        assertEquals(updatedAt, entity.getUpdatedAt());
    }

    @Test
    void deveMapearEntityParaDomain() {
        UUID id = UUID.randomUUID();
        LocalDateTime createdAt = LocalDateTime.now().minusDays(2);
        LocalDateTime updatedAt = LocalDateTime.now().minusDays(1);

        ClientEntity entity = ClientEntity.builder()
                .id(id)
                .nome("Nome")
                .tipoDocumento(TipoDocumento.PESSOA_JURIDICA)
                .documento("11444777000161")
                .telefone("1133333333")
                .endereco("Av")
                .ativo(false)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();

        Client domain = mapper.toDomain(entity);

        assertEquals(id, domain.getId());
        assertEquals("Nome", domain.getNome());
        assertEquals(TipoDocumento.PESSOA_JURIDICA, domain.getTipoDocumento());
        assertEquals(false, domain.getAtivo());
    }
}
