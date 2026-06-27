package br.com.fiap.numberone.serviceorder.application.gateways;

import br.com.fiap.numberone.serviceorder.domain.references.Vehicle;

import java.util.Optional;
import java.util.UUID;

public interface VehicleGateway {
    Vehicle save(Vehicle vehicle);
    Optional<Vehicle> findById(UUID id);
    Optional<Vehicle> findByLicensePlate(String licensePlate);
}
