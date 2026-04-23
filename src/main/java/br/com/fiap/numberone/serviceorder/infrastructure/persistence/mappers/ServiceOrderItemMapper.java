package br.com.fiap.numberone.serviceorder.infrastructure.persistence.mappers;

import br.com.fiap.numberone.serviceorder.domain.entities.ServiceOrderItem;
import br.com.fiap.numberone.serviceorder.domain.entities.ServiceOrder;
import br.com.fiap.numberone.serviceorder.domain.references.AutomotiveService;
import br.com.fiap.numberone.automotiveservice.infrastructure.persistence.entities.AutomotiveServiceEntity;
import br.com.fiap.numberone.serviceorder.infrastructure.persistence.entities.ServiceOrderEntity;
import br.com.fiap.numberone.serviceorder.infrastructure.persistence.entities.ServiceOrderItemEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ServiceOrderItemMapper {

    @Mapping(target = "serviceOrder", source = "serviceOrder", qualifiedByName = "toServiceOrderEntityRef")
    @Mapping(target = "automotiveService", source = "automotiveService", qualifiedByName = "toAutomotiveServiceEntityRef")
    ServiceOrderItemEntity toEntity(ServiceOrderItem domain);

    @Mapping(target = "serviceOrder", source = "serviceOrder", qualifiedByName = "toServiceOrderRef")
    @Mapping(target = "automotiveService", source = "automotiveService", qualifiedByName = "toAutomotiveServiceRef")
    ServiceOrderItem toDomain(ServiceOrderItemEntity entity);

    @Named("toServiceOrderRef")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ServiceOrder toServiceOrderRef(ServiceOrderEntity entity);

    @Named("toServiceOrderEntityRef")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ServiceOrderEntity toServiceOrderEntityRef(ServiceOrder domain);

    @Named("toAutomotiveServiceRef")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    AutomotiveService toAutomotiveServiceRef(AutomotiveServiceEntity entity);

    @Named("toAutomotiveServiceEntityRef")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    AutomotiveServiceEntity toAutomotiveServiceEntityRef(AutomotiveService domain);
}
