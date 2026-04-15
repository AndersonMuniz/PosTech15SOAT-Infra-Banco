package br.com.fiap.numberone.estoque.infrastructure.persistence.mappers;

import br.com.fiap.numberone.estoque.domain.entities.Servico;
import br.com.fiap.numberone.estoque.infrastructure.persistence.entities.ServicoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ServicoMapper {

    ServicoEntity toEntity(Servico domain);

    Servico toDomain(ServicoEntity entity);
}
