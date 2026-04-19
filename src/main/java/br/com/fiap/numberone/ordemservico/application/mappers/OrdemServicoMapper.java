package br.com.fiap.numberone.ordemservico.application.mappers;

import br.com.fiap.numberone.cliente.infrastructure.persistence.entities.ClienteEntity;
import br.com.fiap.numberone.ordemservico.api.dtos.requests.CriarOrdemServicoRequest;
import br.com.fiap.numberone.ordemservico.api.dtos.responses.OrdemServicoResponse;
import br.com.fiap.numberone.ordemservico.domain.entities.OrdemServico;
import br.com.fiap.numberone.veiculo.domain.entities.Veiculo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrdemServicoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cliente", source = "cliente")
    @Mapping(target = "veiculo", source = "veiculo")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    OrdemServico toEntity(CriarOrdemServicoRequest dto, ClienteEntity cliente, Veiculo veiculo);

    OrdemServicoResponse toResponse(OrdemServico entity);
}
