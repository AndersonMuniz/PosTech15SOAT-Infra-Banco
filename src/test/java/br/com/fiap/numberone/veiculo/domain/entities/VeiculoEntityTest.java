package br.com.fiap.numberone.veiculo.domain.entities;

import br.com.fiap.numberone.vehicle.domain.entities.VehicleEntity;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VeiculoEntityTest {

    @Test
    void deveCriarEAlterarVeiculoEntity() {
        UUID id = UUID.randomUUID();
        LocalDateTime createdAt = LocalDateTime.now().minusDays(1);
        LocalDateTime updatedAt = LocalDateTime.now();

        VehicleEntity entity = new VehicleEntity(id, "ABC1D23", "Fiat", "Argo", 2023,
                UUID.randomUUID(), createdAt, updatedAt);

        assertEquals(id, entity.getId());
        assertEquals("ABC1D23", entity.getPlaca());
        assertEquals("Fiat", entity.getMarca());

        entity.setModelo("Pulse");
        entity.setAno(2024);

        assertEquals("Pulse", entity.getModelo());
        assertEquals(2024, entity.getAno());
    }
}
