package br.com.fiap.numberone.veiculo.domain.entities;

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

        VeiculoEntity entity = new VeiculoEntity(id, "ABC1D23", "Fiat", "Argo", 2023,
                "cliente-1", createdAt, updatedAt);

        assertEquals(id, entity.getId());
        assertEquals("ABC1D23", entity.getPlaca());
        assertEquals("Fiat", entity.getMarca());

        entity.setModelo("Pulse");
        entity.setAno(2024);

        assertEquals("Pulse", entity.getModelo());
        assertEquals(2024, entity.getAno());
    }
}
