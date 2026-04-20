package br.com.fiap.numberone.ordemservico.infrastructure.persistence.mappers;

import br.com.fiap.numberone.cliente.infrastructure.persistence.entities.ClienteEntity;
import br.com.fiap.numberone.ordemservico.domain.valueobjects.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ClienteResumoMapper {
    ClienteEntity toEntity(Cliente cliente);

    Cliente toDomain(ClienteEntity entity);
}
