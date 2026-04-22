package br.com.fiap.numberone.client.infrastructure.persistence.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClienteEntityTest {

    @Test
    void prePersistDeveDefinirCreatedAtEAtivoTrueQuandoNulo() {
        ClientEntity entity = ClientEntity.builder().build();

        entity.prePersist();

        assertNotNull(entity.getCreatedAt());
        assertTrue(entity.getAtivo());
    }

    @Test
    void prePersistNaoDeveSobrescreverAtivoQuandoJaDefinido() {
        ClientEntity entity = ClientEntity.builder().ativo(false).build();

        entity.prePersist();

        assertFalse(entity.getAtivo());
    }

    @Test
    void preUpdateDeveDefinirUpdatedAt() {
        ClientEntity entity = ClientEntity.builder().build();

        entity.preUpdate();

        assertNotNull(entity.getUpdatedAt());
    }
}
