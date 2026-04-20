package br.com.fiap.numberone.ordemservico.infrastructure.persistence.mappers;

import br.com.fiap.numberone.ordemservico.domain.entities.ServiceOrder;
import br.com.fiap.numberone.ordemservico.infrastructure.persistence.entities.ServiceOrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ServiceOrderMapper {

    @Mapping(target = "vehicleEntity", source = "vehicle")
    ServiceOrderEntity toEntity(ServiceOrder domain);

    @Mapping(target = "vehicle", source = "vehicleEntity")
    ServiceOrder toDomain(ServiceOrderEntity entity);
}
