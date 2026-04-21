package br.com.fiap.numberone.serviceorder.application.gateways;

import br.com.fiap.numberone.serviceorder.domain.valueobjects.Vehicle;

import java.util.Optional;
import java.util.UUID;

public interface VehicleGateway {
    Optional<Vehicle> findById(UUID id);
}
