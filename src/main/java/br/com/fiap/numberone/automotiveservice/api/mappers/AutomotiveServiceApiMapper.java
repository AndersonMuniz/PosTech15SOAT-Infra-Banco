package br.com.fiap.numberone.automotiveservice.api.mappers;

import br.com.fiap.numberone.automotiveservice.api.dto.requests.AutomotiveServiceRequest;
import br.com.fiap.numberone.inventory.api.dto.responses.AutomotiveServiceResponse;
import br.com.fiap.numberone.automotiveservice.domain.entities.AutomotiveService;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AutomotiveServiceApiMapper {

    AutomotiveService toDomain(AutomotiveServiceRequest request);

    AutomotiveServiceResponse toResponse(AutomotiveService domain);
}