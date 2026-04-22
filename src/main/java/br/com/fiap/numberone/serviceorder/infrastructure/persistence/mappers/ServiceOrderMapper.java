package br.com.fiap.numberone.serviceorder.infrastructure.persistence.mappers;

import br.com.fiap.numberone.serviceorder.domain.entities.ServiceOrder;
import br.com.fiap.numberone.serviceorder.infrastructure.persistence.entities.ServiceOrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = { CustomerMapper.class, VehicleMapper.class, ServiceOrderItemMapper.class }
)
public interface ServiceOrderMapper {

    @Mapping(target = "vehicleEntity", source = "vehicle")
    @Mapping(target = "items", source = "serviceItems")
    ServiceOrderEntity toEntity(ServiceOrder domain);

    @Mapping(target = "vehicle", source = "vehicleEntity")
    @Mapping(target = "serviceItems", source = "items")
    ServiceOrder toDomain(ServiceOrderEntity entity);
}
