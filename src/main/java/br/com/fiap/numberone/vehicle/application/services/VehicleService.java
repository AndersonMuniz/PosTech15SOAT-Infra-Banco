package br.com.fiap.numberone.vehicle.application.services;

import br.com.fiap.numberone.client.infrastructure.repositories.ClientRepository;
import br.com.fiap.numberone.shared.api.exception.ResourceNotFoundException;
import br.com.fiap.numberone.vehicle.api.dtos.requests.VehicleRequest;
import br.com.fiap.numberone.vehicle.api.dtos.responses.VehicleResponse;
import br.com.fiap.numberone.vehicle.application.mappers.VehicleMapper;
import br.com.fiap.numberone.vehicle.domain.entities.Vehicle;
import br.com.fiap.numberone.vehicle.infrastructure.persistence.mappers.VehicleEntityMapper;
import br.com.fiap.numberone.vehicle.infrastructure.repositories.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final ClientRepository clientRepository;
    private final VehicleMapper vehicleMapper;
    private final VehicleEntityMapper vehicleEntityMapper;

    public VehicleService(
            VehicleRepository vehicleRepository,
            ClientRepository clientRepository,
            VehicleMapper vehicleMapper,
            VehicleEntityMapper vehicleEntityMapper
    ) {
        this.vehicleRepository = vehicleRepository;
        this.clientRepository = clientRepository;
        this.vehicleMapper = vehicleMapper;
        this.vehicleEntityMapper = vehicleEntityMapper;
    }

    public VehicleResponse create(VehicleRequest request) {
        validateClientExists(request.idClient());

        String placaNormalizada = normalizePlate(request.placa());
        validatePlateDoesNotExist(placaNormalizada);

        VehicleRequest normalizedRequest = new VehicleRequest(
                placaNormalizada,
                request.marca(),
                request.modelo(),
                request.ano(),
                request.idClient()
        );

        Vehicle vehicle = vehicleMapper.toEntity(normalizedRequest);
        var saved = vehicleRepository.save(vehicleEntityMapper.toEntity(vehicle));

        return vehicleMapper.toResponse(vehicleEntityMapper.toDomain(saved));
    }

    public VehicleResponse update(UUID id, VehicleRequest request) {
        validateClientExists(request.idClient());

        Vehicle existing = vehicleRepository.findById(id)
                .map(vehicleEntityMapper::toDomain)
                .orElseThrow(() -> new ResourceNotFoundException("Veículo não encontrado com id: " + id));

        String placaNormalizada = normalizePlate(request.placa());
        validatePlateDoesNotExistForOtherVehicle(placaNormalizada, id);

        VehicleRequest normalizedRequest = new VehicleRequest(
                placaNormalizada,
                request.marca(),
                request.modelo(),
                request.ano(),
                request.idClient()
        );

        Vehicle atualizado = existing.updateFrom(vehicleMapper.toEntity(normalizedRequest));
        var saved = vehicleRepository.save(vehicleEntityMapper.toEntity(atualizado));

        return vehicleMapper.toResponse(vehicleEntityMapper.toDomain(saved));
    }

    public VehicleResponse findById(UUID id) {
        return vehicleRepository.findById(id)
                .map(vehicleEntityMapper::toDomain)
                .map(vehicleMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Veículo não encontrado com id: " + id));
    }

    public List<VehicleResponse> findAll() {
        return vehicleRepository.findAll().stream()
                .map(vehicleEntityMapper::toDomain)
                .map(vehicleMapper::toResponse)
                .toList();
    }

    public void delete(UUID id) {
        var vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Veículo não encontrado com id: " + id));

        vehicleRepository.delete(vehicle);
    }

    private String normalizePlate(String placa) {
        return placa == null ? null : placa.trim().toUpperCase();
    }

    private void validateClientExists(UUID idClient) {
        if (!clientRepository.existsById(idClient)) {
            throw new ResourceNotFoundException("Cliente não encontrado com id: " + idClient);
        }
    }

    private void validatePlateDoesNotExist(String placa) {
        if (vehicleRepository.existsByPlacaIgnoreCase(placa)) {
            throw new IllegalArgumentException("Já existe um veículo com a placa informada");
        }
    }

    private void validatePlateDoesNotExistForOtherVehicle(String placa, UUID vehicleId) {
        if (vehicleRepository.existsByPlacaIgnoreCaseAndIdNot(placa, vehicleId)) {
            throw new IllegalArgumentException("Já existe outro veículo com a placa informada");
        }
    }
}
