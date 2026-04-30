package br.com.fiap.numberone.vehicle.infrastructure.persistence.mappers;

import br.com.fiap.numberone.vehicle.domain.entities.Vehicle;
import br.com.fiap.numberone.vehicle.infrastructure.persistence.entities.VehicleEntity;
import org.springframework.stereotype.Component;

@Component
public class VehicleEntityMapper {

    public VehicleEntity toEntity(Vehicle domain) {
        return VehicleEntity.builder()
                .id(domain.getId())
                .placa(domain.getPlaca())
                .marca(domain.getMarca())
                .modelo(domain.getModelo())
                .ano(domain.getAno())
                .idClient(domain.getIdClient())
                .createdAt(domain.getCreatedAt())
                .updatedAt(domain.getUpdatedAt())
                .build();
    }

    public Vehicle toDomain(VehicleEntity entity) {
        return Vehicle.builder()
                .id(entity.getId())
                .placa(entity.getPlaca())
                .marca(entity.getMarca())
                .modelo(entity.getModelo())
                .ano(entity.getAno())
                .idClient(entity.getIdClient())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
