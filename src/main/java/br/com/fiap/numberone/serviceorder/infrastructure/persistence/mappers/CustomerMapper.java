package br.com.fiap.numberone.serviceorder.infrastructure.persistence.mappers;

import br.com.fiap.numberone.cliente.infrastructure.persistence.entities.ClienteEntity;
import br.com.fiap.numberone.serviceorder.domain.valueobjects.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CustomerMapper {

    @Mapping(target = "nome", source = "name")
    @Mapping(target = "tipoDocumento", source = "documentType")
    @Mapping(target = "documento", source = "document")
    @Mapping(target = "telefone", source = "phone")
    @Mapping(target = "endereco", source = "address")
    @Mapping(target = "ativo", source = "active")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    ClienteEntity toEntity(Customer customer);

    @Mapping(target = "name", source = "nome")
    @Mapping(target = "documentType", source = "tipoDocumento")
    @Mapping(target = "document", source = "documento")
    @Mapping(target = "phone", source = "telefone")
    @Mapping(target = "address", source = "endereco")
    @Mapping(target = "active", source = "ativo")
    Customer toDomain(ClienteEntity entity);
}