package br.com.fiap.numberone.vehicle.infrastructure.persistence.mappers;

import br.com.fiap.numberone.vehicle.domain.entities.Vehicle;
import org.springframework.stereotype.Component;

@Component
public class VehicleEntityMapper {

    public Vehicle toEntity(Vehicle domain) {
        return domain;
    }

    public Vehicle toDomain(Vehicle entity) {
        return entity;
    }
}
