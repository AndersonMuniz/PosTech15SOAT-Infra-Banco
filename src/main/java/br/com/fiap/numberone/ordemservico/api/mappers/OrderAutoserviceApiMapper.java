package br.com.fiap.numberone.ordemservico.api.mappers;

import br.com.fiap.numberone.ordemservico.api.dtos.requests.CreateOrderAutoserviceRequest;

import br.com.fiap.numberone.ordemservico.domain.entities.ServiceOrderAutoservice;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderAutoserviceApiMapper {
    ServiceOrderAutoservice toDomain(CreateOrderAutoserviceRequest dto);
}
