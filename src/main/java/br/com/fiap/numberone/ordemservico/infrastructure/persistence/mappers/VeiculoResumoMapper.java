package br.com.fiap.numberone.ordemservico.infrastructure.persistence.mappers;

import br.com.fiap.numberone.ordemservico.domain.valueobjects.Veiculo;
import br.com.fiap.numberone.veiculo.domain.entities.VeiculoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface VeiculoResumoMapper {
    VeiculoEntity toEntity(Veiculo veiculo);

    Veiculo toDomain(VeiculoEntity entity);
}
