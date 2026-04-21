package br.com.fiap.numberone.serviceorder.api.mappers;

import br.com.fiap.numberone.serviceorder.api.dtos.requests.CreateOrderAutoserviceRequest;

import br.com.fiap.numberone.serviceorder.domain.entities.ServiceOrderAutoservice;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ServiceOrderAutoserviceApiMapper {
    ServiceOrderAutoservice toDomain(CreateOrderAutoserviceRequest dto);
}
