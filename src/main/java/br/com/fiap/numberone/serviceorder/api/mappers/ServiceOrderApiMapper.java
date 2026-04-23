package br.com.fiap.numberone.serviceorder.api.mappers;

import br.com.fiap.numberone.serviceorder.api.dtos.requests.CreateServiceOrderRequest;
import br.com.fiap.numberone.serviceorder.api.dtos.requests.FinalDiagnosisRequest;
import br.com.fiap.numberone.serviceorder.api.dtos.responses.ServiceOrderBudgetResponse;
import br.com.fiap.numberone.serviceorder.api.dtos.responses.ServiceOrderItemResponse;
import br.com.fiap.numberone.serviceorder.api.dtos.responses.ServiceOrderResponse;
import br.com.fiap.numberone.serviceorder.api.dtos.responses.ServiceOrderValueResponse;
import br.com.fiap.numberone.serviceorder.domain.entities.ServiceOrderBudget;
import br.com.fiap.numberone.serviceorder.domain.entities.ServiceOrderItem;
import br.com.fiap.numberone.serviceorder.domain.valueobjects.Diagnosis;
import br.com.fiap.numberone.serviceorder.domain.entities.ServiceOrder;
import br.com.fiap.numberone.serviceorder.domain.references.Customer;
import br.com.fiap.numberone.serviceorder.domain.references.Vehicle;
import br.com.fiap.numberone.serviceorder.domain.valueobjects.ServiceOrderValue;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ServiceOrderApiMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "expectedDateTime", ignore = true)
    @Mapping(target = "deliveryDateTime", ignore = true)
    @Mapping(target = "finalDiagnosisDescription", ignore = true)
    @Mapping(target = "customer.id", source = "customerId")
    @Mapping(target = "vehicle.id", source = "vehicleId")
    ServiceOrder toDomain(CreateServiceOrderRequest dto);

    Diagnosis toDomain(FinalDiagnosisRequest dto);

    ServiceOrderValueResponse toResponse(ServiceOrderValue valueObject);

    ServiceOrderResponse toResponse(ServiceOrder entity);

    ServiceOrderResponse.CustomerResponse toResponse(Customer customer);

    ServiceOrderResponse.VehicleResponse toResponse(Vehicle vehicle);

    @Mapping(target = "serviceOrderId", source = "serviceOrder.id")
    ServiceOrderItemResponse toResponse(ServiceOrderItem serviceOrderItem);

    @Mapping(target = "serviceOrderId", source = "serviceOrder.id")
    ServiceOrderBudgetResponse toResponse(ServiceOrderBudget serviceOrderBudget);
}
