package br.com.fiap.numberone.ordemservico.api.mappers;

import br.com.fiap.numberone.ordemservico.api.dtos.requests.CriarOrdemServicoRequest;
import br.com.fiap.numberone.ordemservico.api.dtos.requests.DiagnosticoFinalRequest;
import br.com.fiap.numberone.ordemservico.api.dtos.responses.OrdemServicoResponse;
import br.com.fiap.numberone.ordemservico.domain.entities.Diagnostico;
import br.com.fiap.numberone.ordemservico.domain.entities.OrdemServico;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrdemServicoApiMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "cliente.id", source = "clienteId")
    @Mapping(target = "veiculo.id", source = "veiculoId")
    OrdemServico toDomain(CriarOrdemServicoRequest dto);

    Diagnostico toDomain(DiagnosticoFinalRequest dto);

    OrdemServicoResponse toResponse(OrdemServico entity);
}
