package br.com.fiap.numberone.client.infrastructure.persistence.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClienteEntityTest {

    @Test
    void prePersistDeveDefinirCreatedAtEAtivoTrueQuandoNulo() {
        ClienteEntity entity = ClienteEntity.builder().build();

        entity.prePersist();

        assertNotNull(entity.getCreatedAt());
        assertTrue(entity.getAtivo());
    }

    @Test
    void prePersistNaoDeveSobrescreverAtivoQuandoJaDefinido() {
        ClienteEntity entity = ClienteEntity.builder().ativo(false).build();

        entity.prePersist();

        assertFalse(entity.getAtivo());
    }

    @Test
    void preUpdateDeveDefinirUpdatedAt() {
        ClienteEntity entity = ClienteEntity.builder().build();

        entity.preUpdate();

        assertNotNull(entity.getUpdatedAt());
    }
}
