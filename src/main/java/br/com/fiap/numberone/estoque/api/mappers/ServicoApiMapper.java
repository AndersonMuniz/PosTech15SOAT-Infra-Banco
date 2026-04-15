package br.com.fiap.numberone.estoque.api.mappers;

import br.com.fiap.numberone.estoque.api.dto.requests.ServicoDTORequest;
import br.com.fiap.numberone.estoque.api.dto.responses.ServicoResponse;
import br.com.fiap.numberone.estoque.domain.entities.Servico;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ServicoApiMapper {

    Servico toDomain(ServicoDTORequest request);

    ServicoResponse toResponse(Servico servico);
}
