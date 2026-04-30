package br.com.fiap.numberone.vehicle.infrastructure.persistence.mappers;

import br.com.fiap.numberone.vehicle.domain.entities.Vehicle;
import br.com.fiap.numberone.vehicle.infrastructure.persistence.entities.VehicleEntity;
import org.springframework.stereotype.Component;

@Component
public class VehicleEntityMapper {

    public VehicleEntity toEntity(Vehicle domain) {
        return VehicleEntity.builder()
                .id(domain.getId())
                .licensePlate(domain.getPlaca())
                .brand(domain.getMarca())
                .model(domain.getModelo())
                .year(domain.getAno())
                .customerId(domain.getIdClient())
                .createdAt(domain.getCreatedAt())
                .updatedAt(domain.getUpdatedAt())
                .build();
    }

    public Vehicle toDomain(VehicleEntity entity) {
        return Vehicle.builder()
                .id(entity.getId())
                .placa(entity.getLicensePlate())
                .marca(entity.getBrand())
                .modelo(entity.getModel())
                .ano(entity.getYear())
                .idClient(entity.getCustomerId())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
