package br.com.fiap.numberone.serviceorder.domain.valueobjects;

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

        Vehicle vehicle = Vehicle.builder()
                .id(id)
                .licensePlate("ABC1D23")
                .brand("Toyota")
                .model("Corolla")
                .year(2020)
                .customerId("cliente-10")
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();

        assertEquals(id, vehicle.getId());
        assertEquals("Toyota", vehicle.getBrand());
        assertEquals("Corolla", vehicle.getModel());
        assertEquals("cliente-10", vehicle.getCustomerId());
        assertEquals(createdAt, vehicle.getCreatedAt());
        assertEquals(updatedAt, vehicle.getUpdatedAt());
    }
}
