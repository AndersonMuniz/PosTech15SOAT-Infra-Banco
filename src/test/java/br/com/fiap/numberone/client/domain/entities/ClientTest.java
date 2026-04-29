package br.com.fiap.numberone.client.domain.entities;

import br.com.fiap.numberone.client.domain.enums.TipoDocumento;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    @Test
    void deveCriarClientComConstrutorCompletoEGetters() {
        UUID id = UUID.randomUUID();
        LocalDateTime createdAt = LocalDateTime.now().minusDays(3);
        LocalDateTime updatedAt = LocalDateTime.now().minusHours(6);

        Client client = new Client(id, "João da Silva", TipoDocumento.PESSOA_FISICA, "52998224725", "joao@email.com", "11999998888", "Rua 1, 123", true, createdAt, updatedAt);

        assertEquals(id, client.getId());
        assertEquals("João da Silva", client.getNome());
        assertEquals(TipoDocumento.PESSOA_FISICA, client.getTipoDocumento());
        assertEquals("52998224725", client.getDocumento());
        assertEquals("joao@email.com", client.getEmail());
        assertEquals("11999998888", client.getTelefone());
        assertEquals("Rua 1, 123", client.getEndereco());
        assertTrue(client.getAtivo());
        assertEquals(createdAt, client.getCreatedAt());
        assertEquals(updatedAt, client.getUpdatedAt());
    }

    @Test
    void deveAtualizarCamposMantendoIdECreatedAtEAtualizarUpdatedAt() {
        UUID id = UUID.randomUUID();
        LocalDateTime createdAt = LocalDateTime.now().minusDays(7);

        Client atual = Client.builder().id(id).nome("Cliente Antigo").tipoDocumento(TipoDocumento.PESSOA_FISICA)
                .documento("52998224725").email("antigo@email.com").telefone("11900000000").endereco("Rua A")
                .ativo(true).createdAt(createdAt).updatedAt(LocalDateTime.now().minusDays(1)).build();

        Client novo = Client.builder().nome("Cliente Novo").tipoDocumento(TipoDocumento.PESSOA_JURIDICA)
                .documento("11444777000161").email("novo@email.com").telefone("11911111111")
                .endereco("Rua B").ativo(false).build();

        LocalDateTime antes = LocalDateTime.now().minusSeconds(1);
        Client atualizado = atual.updateFrom(novo);
        LocalDateTime depois = LocalDateTime.now().plusSeconds(1);

        assertEquals(id, atualizado.getId());
        assertEquals(createdAt, atualizado.getCreatedAt());
        assertEquals("Cliente Novo", atualizado.getNome());
        assertEquals(TipoDocumento.PESSOA_JURIDICA, atualizado.getTipoDocumento());
        assertEquals("11444777000161", atualizado.getDocumento());
        assertEquals("novo@email.com", atualizado.getEmail());
        assertEquals("11911111111", atualizado.getTelefone());
        assertEquals("Rua B", atualizado.getEndereco());
        assertFalse(atualizado.getAtivo());
        assertNotNull(atualizado.getUpdatedAt());
        assertTrue(!atualizado.getUpdatedAt().isBefore(antes) && !atualizado.getUpdatedAt().isAfter(depois));
    }

    @Test
    void deveCriarClientComConstrutorVazio() {
        Client client = new Client();

        assertNull(client.getId());
        assertNull(client.getNome());
        assertNull(client.getTipoDocumento());
        assertNull(client.getDocumento());
        assertNull(client.getEmail());
        assertNull(client.getTelefone());
        assertNull(client.getEndereco());
        assertNull(client.getAtivo());
        assertNull(client.getCreatedAt());
        assertNull(client.getUpdatedAt());
    }
}
