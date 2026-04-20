package br.com.fiap.numberone.ordemservico.infrastructure.persistence.mappers;

import br.com.fiap.numberone.ordemservico.domain.entities.OrdemServico;
import br.com.fiap.numberone.ordemservico.infrastructure.persistence.entities.OrdemServicoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrdemServicoMapper {

    OrdemServicoEntity toEntity(OrdemServico domain);

    OrdemServico toDomain(OrdemServicoEntity entity);
}
