package br.com.fiap.numberone.serviceorder.infrastructure.persistence.mappers;

import br.com.fiap.numberone.serviceorder.domain.references.Vehicle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface VehicleMapper {
    @Mapping(target = "idClient", source = "customerId")
    br.com.fiap.numberone.vehicle.domain.entities.Vehicle toEntity(Vehicle vehicle);

    @Mapping(target = "customerId", source = "idClient")
    Vehicle toDomain(br.com.fiap.numberone.vehicle.domain.entities.Vehicle entity);
}
