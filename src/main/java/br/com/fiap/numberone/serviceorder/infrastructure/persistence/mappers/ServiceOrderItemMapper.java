package br.com.fiap.numberone.serviceorder.infrastructure.persistence.mappers;

import br.com.fiap.numberone.serviceorder.domain.entities.ServiceOrderItem;
import br.com.fiap.numberone.serviceorder.infrastructure.persistence.entities.ServiceOrderItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ServiceOrderItemMapper {

    @Mapping(target = "serviceOrder.id", source = "serviceOrder.id")
    @Mapping(target = "automotiveService.id", source = "automotiveService.id")
    ServiceOrderItemEntity toEntity(ServiceOrderItem domain);

    @Mapping(target = "serviceOrder.id", source = "serviceOrder.id")
    @Mapping(target = "automotiveService.id", source = "automotiveService.id")
    ServiceOrderItem toDomain(ServiceOrderItemEntity entity);
}
