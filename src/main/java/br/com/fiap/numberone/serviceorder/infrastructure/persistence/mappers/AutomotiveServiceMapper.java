package br.com.fiap.numberone.serviceorder.infrastructure.persistence.mappers;

import br.com.fiap.numberone.inventory.infrastructure.persistence.entities.AutomotiveServiceEntity;
import br.com.fiap.numberone.serviceorder.domain.valueobjects.AutomotiveService;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AutomotiveServiceMapper {
    AutomotiveServiceEntity toEntity(AutomotiveService automotiveService);

    AutomotiveService toDomain(AutomotiveServiceEntity entity);
}
