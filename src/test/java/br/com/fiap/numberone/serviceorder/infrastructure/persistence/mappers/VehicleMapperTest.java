package br.com.fiap.numberone.serviceorder.infrastructure.persistence.mappers;

import br.com.fiap.numberone.serviceorder.domain.references.Vehicle;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class VehicleMapperTest {

    private final VehicleMapper mapper = Mappers.getMapper(VehicleMapper.class);

    @Test
    void deveMapearVehicleParaEntity() {
        UUID id = UUID.randomUUID();
        UUID customerId = UUID.randomUUID();
        LocalDateTime createdAt = LocalDateTime.now().minusDays(1);
        LocalDateTime updatedAt = LocalDateTime.now();

        Vehicle vehicle = Vehicle.builder()
                .id(id)
                .licensePlate("ABC1D23")
                .brand("Fiat")
                .model("Argo")
                .year(2024)
                .customerId(customerId)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();

        br.com.fiap.numberone.vehicle.domain.entities.Vehicle entity = mapper.toEntity(vehicle);

        assertNotNull(entity);
        assertEquals(id, entity.getId());
        assertEquals("ABC1D23", entity.getPlaca());
        assertEquals("Fiat", entity.getMarca());
        assertEquals("Argo", entity.getModelo());
        assertEquals(2024, entity.getAno());
        assertEquals(customerId, entity.getIdClient());
        assertEquals(createdAt, entity.getCreatedAt());
        assertEquals(updatedAt, entity.getUpdatedAt());
    }

    @Test
    void deveMapearEntityParaVehicle() {
        UUID id = UUID.randomUUID();
        UUID customerId = UUID.randomUUID();
        LocalDateTime createdAt = LocalDateTime.now().minusDays(2);
        LocalDateTime updatedAt = LocalDateTime.now().minusHours(3);

        br.com.fiap.numberone.vehicle.domain.entities.Vehicle entity =
                br.com.fiap.numberone.vehicle.domain.entities.Vehicle.builder()
                        .id(id)
                        .placa("DEF2G34")
                        .marca("Honda")
                        .modelo("Civic")
                        .ano(2023)
                        .idClient(customerId)
                        .createdAt(createdAt)
                        .updatedAt(updatedAt)
                        .build();

        Vehicle vehicle = mapper.toDomain(entity);

        assertNotNull(vehicle);
        assertEquals(id, vehicle.getId());
        assertEquals("DEF2G34", vehicle.getLicensePlate());
        assertEquals("Honda", vehicle.getBrand());
        assertEquals("Civic", vehicle.getModel());
        assertEquals(2023, vehicle.getYear());
        assertEquals(customerId, vehicle.getCustomerId());
        assertEquals(createdAt, vehicle.getCreatedAt());
        assertEquals(updatedAt, vehicle.getUpdatedAt());
    }

    @Test
    void deveRetornarNuloQuandoOrigemForNula() {
        assertNull(mapper.toEntity(null));
        assertNull(mapper.toDomain(null));
    }
}
