package br.com.fiap.numberone.serviceorder.infrastructure.persistence.mappers;

import br.com.fiap.numberone.serviceorder.domain.references.Vehicle;
import br.com.fiap.numberone.vehicle.infrastructure.persistence.entities.VehicleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface VehicleMapper {
    @Mapping(target = "idClient", source = "customerId")
    VehicleEntity toEntity(Vehicle vehicle);

    @Mapping(target = "customerId", source = "idClient")
    Vehicle toDomain(VehicleEntity entity);
}
