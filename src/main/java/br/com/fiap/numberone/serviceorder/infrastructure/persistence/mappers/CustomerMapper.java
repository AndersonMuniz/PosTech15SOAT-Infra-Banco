package br.com.fiap.numberone.serviceorder.infrastructure.persistence.mappers;

import br.com.fiap.numberone.client.infrastructure.persistence.entities.ClientEntity;
import br.com.fiap.numberone.serviceorder.domain.references.Customer;
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
    @Mapping(target = "email", source = "email")
    ClientEntity toEntity(Customer customer);

    @Mapping(target = "name", source = "nome")
    @Mapping(target = "documentType", source = "tipoDocumento")
    @Mapping(target = "document", source = "documento")
    @Mapping(target = "phone", source = "telefone")
    @Mapping(target = "address", source = "endereco")
    @Mapping(target = "active", source = "ativo")
    @Mapping(target = "email", source = "email")
    Customer toDomain(ClientEntity entity);
}
