package br.com.fiap.numberone.serviceorder.infrastructure.persistence.mappers;

import br.com.fiap.numberone.serviceorder.domain.entities.ServiceOrder;
import br.com.fiap.numberone.serviceorder.infrastructure.persistence.entities.ServiceOrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ServiceOrderMapper {

    @Mapping(target = "vehicleEntity.id", source = "vehicle.id")
    ServiceOrderEntity toEntity(ServiceOrder domain);

    @Mapping(target = "vehicle.id", source = "vehicleEntity.id")
    ServiceOrder toDomain(ServiceOrderEntity entity);
}
