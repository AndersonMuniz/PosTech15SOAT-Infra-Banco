package br.com.fiap.numberone.serviceorder.infrastructure.persistence.mappers;

import br.com.fiap.numberone.serviceorder.domain.entities.ServiceOrderBudget;
import br.com.fiap.numberone.serviceorder.infrastructure.persistence.entities.ServiceOrderBudgetEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ServiceOrderBudgetMapper {

    @Mapping(target = "serviceOrder.id", source = "serviceOrder.id")
    ServiceOrderBudgetEntity toEntity(ServiceOrderBudget domain);

    @Mapping(target = "serviceOrder.id", source = "serviceOrder.id")
    ServiceOrderBudget toDomain(ServiceOrderBudgetEntity entity);
}
