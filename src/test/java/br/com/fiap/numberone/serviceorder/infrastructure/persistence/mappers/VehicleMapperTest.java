package br.com.fiap.numberone.serviceorder.infrastructure.persistence.mappers;

import br.com.fiap.numberone.serviceorder.domain.valueobjects.Vehicle;
import br.com.fiap.numberone.veiculo.domain.entities.VeiculoEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VehicleMapperTest {

    private final VehicleMapper mapper = Mappers.getMapper(VehicleMapper.class);

    @Test
    void deveMapearVehicleParaVeiculoEntity() {
        UUID id = UUID.randomUUID();
        Vehicle vehicle = Vehicle.builder()
                .id(id)
                .licensePlate("ABC1D23")
                .brand("Fiat")
                .model("Argo")
                .year(2024)
                .customerId("cliente-1")
                .createdAt(LocalDateTime.now().minusDays(1))
                .updatedAt(LocalDateTime.now())
                .build();

        VeiculoEntity entity = mapper.toEntity(vehicle);

        assertEquals(id, entity.getId());
        assertEquals("ABC1D23", entity.getPlaca());
        assertEquals("Fiat", entity.getMarca());
        assertEquals("Argo", entity.getModelo());
        assertEquals(2024, entity.getAno());
        assertEquals("cliente-1", entity.getIdCliente());
    }

    @Test
    void deveMapearVeiculoEntityParaVehicle() {
        UUID id = UUID.randomUUID();
        VeiculoEntity entity = new VeiculoEntity(id, "ABC1D23", "Fiat", "Argo", 2024,
                "cliente-1", LocalDateTime.now().minusDays(1), LocalDateTime.now());

        Vehicle vehicle = mapper.toDomain(entity);

        assertEquals(id, vehicle.getId());
        assertEquals("ABC1D23", vehicle.getLicensePlate());
        assertEquals("Fiat", vehicle.getBrand());
        assertEquals("Argo", vehicle.getModel());
        assertEquals(2024, vehicle.getYear());
        assertEquals("cliente-1", vehicle.getCustomerId());
    }
}
