package br.com.fiap.numberone.client.domain.entities;

import br.com.fiap.numberone.client.domain.enums.TipoDocumento;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {

    @Test
    void deveAtualizarCamposMantendoIdECreatedAt() {
        UUID id = UUID.randomUUID();
        LocalDateTime createdAt = LocalDateTime.now().minusDays(2);

        Client atual = Client.builder()
                .id(id)
                .nome("Cliente Antigo")
                .tipoDocumento(TipoDocumento.PESSOA_FISICA)
                .documento("52998224725")
                .telefone("11900000000")
                .endereco("Rua A")
                .ativo(true)
                .createdAt(createdAt)
                .build();

        Client novo = Client.builder()
                .nome("Cliente Novo")
                .tipoDocumento(TipoDocumento.PESSOA_JURIDICA)
                .documento("11444777000161")
                .telefone("11911111111")
                .endereco("Rua B")
                .ativo(false)
                .build();

        Client atualizado = atual.updateFrom(novo);

        assertEquals(id, atualizado.getId());
        assertEquals(createdAt, atualizado.getCreatedAt());
        assertEquals("Cliente Novo", atualizado.getNome());
        assertEquals(TipoDocumento.PESSOA_JURIDICA, atualizado.getTipoDocumento());
        assertEquals("11444777000161", atualizado.getDocumento());
        assertEquals("11911111111", atualizado.getTelefone());
        assertEquals("Rua B", atualizado.getEndereco());
        assertFalse(atualizado.getAtivo());
        assertNotNull(atualizado.getUpdatedAt());
    }
}
