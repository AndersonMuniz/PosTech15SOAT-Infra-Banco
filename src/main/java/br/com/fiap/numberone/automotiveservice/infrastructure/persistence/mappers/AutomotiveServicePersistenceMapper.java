package br.com.fiap.numberone.automotiveservice.infrastructure.persistence.mappers;

import org.mapstruct.Mapper;

import br.com.fiap.numberone.automotiveservice.domain.entities.AutomotiveService;
import br.com.fiap.numberone.automotiveservice.infrastructure.persistence.entities.AutomotiveServiceEntity;
@Mapper(componentModel = "spring")
public interface AutomotiveServicePersistenceMapper {

    AutomotiveService toDomain(AutomotiveServiceEntity entity);

    AutomotiveServiceEntity toEntity(AutomotiveService domain);
}
