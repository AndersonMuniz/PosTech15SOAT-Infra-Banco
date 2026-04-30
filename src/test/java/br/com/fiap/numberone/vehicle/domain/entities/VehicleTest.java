package br.com.fiap.numberone.vehicle.domain.entities;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class VehicleTest {

    @Test
    void deveCriarVehicleComConstrutorCompletoEGetters() {
        UUID id = UUID.randomUUID();
        UUID clientId = UUID.randomUUID();
        LocalDateTime createdAt = LocalDateTime.now().minusDays(5);
        LocalDateTime updatedAt = LocalDateTime.now().minusDays(1);

        Vehicle vehicle = new Vehicle(id, "ABC1D23", "Toyota", "Corolla", 2021, clientId, createdAt, updatedAt);

        assertEquals(id, vehicle.getId());
        assertEquals("ABC1D23", vehicle.getPlaca());
        assertEquals("Toyota", vehicle.getMarca());
        assertEquals("Corolla", vehicle.getModelo());
        assertEquals(2021, vehicle.getAno());
        assertEquals(clientId, vehicle.getIdClient());
        assertEquals(createdAt, vehicle.getCreatedAt());
        assertEquals(updatedAt, vehicle.getUpdatedAt());
    }

    @Test
    void deveAtualizarCamposMantendoIdECreatedAtEAtualizarUpdatedAt() {
        UUID id = UUID.randomUUID();
        UUID clientNovo = UUID.randomUUID();
        LocalDateTime createdAt = LocalDateTime.now().minusDays(10);

        Vehicle atual = Vehicle.builder().id(id).placa("AAA0A00").marca("Ford").modelo("Ka").ano(2018)
                .idClient(UUID.randomUUID()).createdAt(createdAt).updatedAt(LocalDateTime.now().minusDays(1)).build();

        Vehicle novo = Vehicle.builder().placa("DEF2G34").marca("Honda").modelo("Civic").ano(2023).idClient(clientNovo).build();

        LocalDateTime antes = LocalDateTime.now().minusSeconds(1);
        Vehicle atualizado = atual.updateFrom(novo);
        LocalDateTime depois = LocalDateTime.now().plusSeconds(1);

        assertEquals(id, atualizado.getId());
        assertEquals(createdAt, atualizado.getCreatedAt());
        assertEquals("DEF2G34", atualizado.getPlaca());
        assertEquals("Honda", atualizado.getMarca());
        assertEquals("Civic", atualizado.getModelo());
        assertEquals(2023, atualizado.getAno());
        assertEquals(clientNovo, atualizado.getIdClient());
        assertNotNull(atualizado.getUpdatedAt());
        assertTrue(!atualizado.getUpdatedAt().isBefore(antes) && !atualizado.getUpdatedAt().isAfter(depois));
    }

    @Test
    void deveAtualizarPermitindoCamposNulosNoNovoVeiculo() {
        UUID id = UUID.randomUUID();
        LocalDateTime createdAt = LocalDateTime.now().minusDays(7);
        LocalDateTime oldUpdatedAt = LocalDateTime.now().minusDays(2);

        Vehicle atual = Vehicle.builder()
                .id(id)
                .placa("GHI3J45")
                .marca("Volkswagen")
                .modelo("Polo")
                .ano(2020)
                .idClient(UUID.randomUUID())
                .createdAt(createdAt)
                .updatedAt(oldUpdatedAt)
                .build();

        Vehicle novo = Vehicle.builder().build();

        Vehicle atualizado = atual.updateFrom(novo);

        assertEquals(id, atualizado.getId());
        assertNull(atualizado.getPlaca());
        assertNull(atualizado.getMarca());
        assertNull(atualizado.getModelo());
        assertNull(atualizado.getAno());
        assertNull(atualizado.getIdClient());
        assertEquals(createdAt, atualizado.getCreatedAt());
        assertNotNull(atualizado.getUpdatedAt());
        assertTrue(atualizado.getUpdatedAt().isAfter(oldUpdatedAt));
    }

    @Test
    void deveCriarVehicleComConstrutorVazio() {
        Vehicle vehicle = new Vehicle();

        assertNull(vehicle.getId());
        assertNull(vehicle.getPlaca());
        assertNull(vehicle.getMarca());
        assertNull(vehicle.getModelo());
        assertNull(vehicle.getAno());
        assertNull(vehicle.getIdClient());
        assertNull(vehicle.getCreatedAt());
        assertNull(vehicle.getUpdatedAt());
    }
}
