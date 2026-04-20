package br.com.fiap.numberone.inventory.infrastructure.persistence.mappers;

import org.mapstruct.Mapper;

import br.com.fiap.numberone.inventory.domain.entities.AutomotiveService;
import br.com.fiap.numberone.inventory.infrastructure.persistence.entities.AutomotiveServiceEntity;
@Mapper(componentModel = "spring")
public interface AutomotiveServicePersistenceMapper {

    AutomotiveService toDomain(AutomotiveServiceEntity entity);

    AutomotiveServiceEntity toEntity(AutomotiveService domain);
}
