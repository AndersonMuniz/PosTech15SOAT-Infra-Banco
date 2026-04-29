package br.com.fiap.numberone.vehicle.application.services;

import br.com.fiap.numberone.client.infrastructure.repositories.ClientRepository;
import br.com.fiap.numberone.shared.api.exception.ResourceNotFoundException;
import br.com.fiap.numberone.vehicle.api.dtos.requests.VehicleRequest;
import br.com.fiap.numberone.vehicle.application.mappers.VehicleMapper;
import br.com.fiap.numberone.vehicle.domain.entities.Vehicle;
import br.com.fiap.numberone.vehicle.infrastructure.persistence.mappers.VehicleEntityMapper;
import br.com.fiap.numberone.vehicle.infrastructure.repositories.VehicleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VehicleServiceTest {

    @Mock
    private VehicleRepository vehicleRepository;

    @Mock
    private ClientRepository clientRepository;

    private VehicleService vehicleService;

    @BeforeEach
    void setup() {
        vehicleService = new VehicleService(vehicleRepository, clientRepository, new VehicleMapper(), new VehicleEntityMapper());
    }

    @Test
    void deveLancarExcecaoQuandoClienteNaoExisteNoCreate() {
        UUID idClient = UUID.randomUUID();
        VehicleRequest request = new VehicleRequest("abc1d23", "Fiat", "Argo", 2023, idClient);

        when(clientRepository.existsById(idClient)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> vehicleService.create(request));
        verify(vehicleRepository, never()).save(any());
    }

    @Test
    void deveLancarExcecaoQuandoPlacaJaExisteNoCreate() {
        UUID idClient = UUID.randomUUID();
        VehicleRequest request = new VehicleRequest("ABC1D23", "Fiat", "Argo", 2023, idClient);

        when(clientRepository.existsById(idClient)).thenReturn(true);
        when(vehicleRepository.existsByPlacaIgnoreCase("ABC1D23")).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> vehicleService.create(request));
        verify(vehicleRepository, never()).save(any());
    }

    @Test
    void deveLancarExcecaoQuandoPlacaJaExisteEmOutroRegistroNoUpdate() {
        UUID vehicleId = UUID.randomUUID();
        UUID idClient = UUID.randomUUID();

        VehicleRequest request = new VehicleRequest("ABC1D23", "Fiat", "Pulse", 2024, idClient);
        Vehicle entity = Vehicle.builder()
                .id(vehicleId)
                .placa("OLD1A11")
                .marca("Fiat")
                .modelo("Argo")
                .ano(2023)
                .idClient(idClient)
                .build();

        when(clientRepository.existsById(idClient)).thenReturn(true);
        when(vehicleRepository.findById(vehicleId)).thenReturn(Optional.of(entity));
        when(vehicleRepository.existsByPlacaIgnoreCaseAndIdNot("ABC1D23", vehicleId)).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> vehicleService.update(vehicleId, request));
    }
}
