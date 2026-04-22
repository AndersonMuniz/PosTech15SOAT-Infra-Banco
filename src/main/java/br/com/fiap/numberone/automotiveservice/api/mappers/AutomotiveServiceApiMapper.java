package br.com.fiap.numberone.automotiveservice.api.mappers;

import br.com.fiap.numberone.automotiveservice.api.dto.requests.AutomotiveServiceRequest;
import br.com.fiap.numberone.automotiveservice.api.dto.responses.AutomotiveServiceResponse;
import br.com.fiap.numberone.automotiveservice.domain.entities.AutomotiveService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AutomotiveServiceApiMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", constant = "true")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    AutomotiveService toDomain(AutomotiveServiceRequest request);

    AutomotiveServiceResponse toResponse(AutomotiveService domain);
}