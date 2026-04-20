package br.com.fiap.numberone.ordemservico.application.gateways;

import br.com.fiap.numberone.ordemservico.domain.valueobjects.Vehicle;

import java.util.Optional;
import java.util.UUID;

public interface VehicleGateway {
    Optional<Vehicle> findById(UUID id);
}
