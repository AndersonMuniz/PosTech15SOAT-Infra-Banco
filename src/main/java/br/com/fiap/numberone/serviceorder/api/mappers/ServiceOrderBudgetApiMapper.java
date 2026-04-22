package br.com.fiap.numberone.serviceorder.api.mappers;

import br.com.fiap.numberone.serviceorder.api.dtos.requests.CreateServiceOrderBudgetRequest;
import br.com.fiap.numberone.serviceorder.api.dtos.responses.ServiceOrderBudgetResponse;
import br.com.fiap.numberone.serviceorder.api.dtos.responses.ServiceOrderValueResponse;
import br.com.fiap.numberone.serviceorder.domain.entities.ServiceOrder;
import br.com.fiap.numberone.serviceorder.domain.entities.ServiceOrderBudget;
import br.com.fiap.numberone.serviceorder.domain.valueobjects.ServiceOrderValue;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ServiceOrderBudgetApiMapper {

    ServiceOrderBudget toDomain(CreateServiceOrderBudgetRequest dto);

    ServiceOrderBudgetResponse toResponse(ServiceOrderBudget entity);
}
