package br.com.fiap.numberone.serviceorder.domain.valueobjects;

import br.com.fiap.numberone.vehicle.domain.entities.Vehicle;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VehicleTest {

    @Test
    void deveCriarVehicleComBuilder() {
        UUID id = UUID.randomUUID();
        LocalDateTime createdAt = LocalDateTime.now().minusDays(1);
        LocalDateTime updatedAt = LocalDateTime.now();

        UUID customerId = UUID.randomUUID();

        Vehicle vehicle = Vehicle.builder()
                .id(id)
                .placa("ABC1D23")
                .marca("Toyota")
                .modelo("Corolla")
                .ano(2020)
                .idClient(customerId)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();

        assertEquals(id, vehicle.getId());
        assertEquals("Toyota", vehicle.getMarca());
        assertEquals("Corolla", vehicle.getModelo());
        assertEquals(customerId, vehicle.getIdClient());
        assertEquals(createdAt, vehicle.getCreatedAt());
        assertEquals(updatedAt, vehicle.getUpdatedAt());
    }
}
