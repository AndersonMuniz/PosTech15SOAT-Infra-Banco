package br.com.fiap.numberone.serviceorder.api.mappers;

import br.com.fiap.numberone.serviceorder.api.dtos.requests.CreateServiceOrderRequest;
import br.com.fiap.numberone.serviceorder.api.dtos.requests.FinalDiagnosisRequest;
import br.com.fiap.numberone.serviceorder.api.dtos.responses.*;
import br.com.fiap.numberone.serviceorder.domain.entities.ServiceOrderBudget;
import br.com.fiap.numberone.serviceorder.domain.entities.ServiceOrderItem;
import br.com.fiap.numberone.serviceorder.domain.entities.ServiceOrderItemSupply;
import br.com.fiap.numberone.serviceorder.domain.valueobjects.Diagnosis;
import br.com.fiap.numberone.serviceorder.domain.entities.ServiceOrder;
import br.com.fiap.numberone.serviceorder.domain.references.Customer;
import br.com.fiap.numberone.serviceorder.domain.references.Vehicle;
import br.com.fiap.numberone.serviceorder.domain.valueobjects.ServiceOrderAverageExecutionTime;
import br.com.fiap.numberone.serviceorder.domain.valueobjects.ServiceOrderEstimatedTime;
import br.com.fiap.numberone.serviceorder.domain.valueobjects.ServiceOrderValue;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Comparator;
import java.util.List;

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

    ServiceOrderEstimatedTimeResponse toResponse(ServiceOrderEstimatedTime valueObject);

    ServiceOrderAverageExecutionTimeResponse toResponse(ServiceOrderAverageExecutionTime valueObject);

    ServiceOrderResponse toResponse(ServiceOrder entity);

    @Mapping(target = "budget", expression = "java(getLatestBudgetResponse(entity.getBudgets()))")
    ServiceOrderTrackingResponse toTrackingResponse(ServiceOrder entity);

    ServiceOrderResponse.CustomerResponse toResponse(Customer customer);

    ServiceOrderResponse.VehicleResponse toResponse(Vehicle vehicle);

    ServiceOrderTrackingResponse.VehicleResponse toTrackingResponse(Vehicle vehicle);

    @Mapping(target = "serviceOrderId", source = "serviceOrder.id")
    ServiceOrderItemResponse toResponse(ServiceOrderItem serviceOrderItem);

    @Mapping(target = "serviceName", source = "automotiveService.name")
    @Mapping(target = "serviceType", source = "automotiveService.serviceType")
    ServiceOrderTrackingResponse.ServiceItemResponse toTrackingResponse(ServiceOrderItem serviceOrderItem);

    @Mapping(target = "serviceOrderItemId", source = "serviceOrderItem.id")
    ServiceOrderItemSupplyResponse toResponse(ServiceOrderItemSupply serviceOrderItemSupply);

    @Mapping(target = "serviceOrderId", source = "serviceOrder.id")
    ServiceOrderBudgetResponse toResponse(ServiceOrderBudget serviceOrderBudget);

    ServiceOrderTrackingResponse.BudgetResponse toTrackingResponse(ServiceOrderBudget serviceOrderBudget);

    default ServiceOrderTrackingResponse.BudgetResponse getLatestBudgetResponse(List<ServiceOrderBudget> budgets) {
        if (budgets == null || budgets.isEmpty()) {
            return null;
        }

        ServiceOrderBudget latestBudget = budgets.stream()
                .filter(java.util.Objects::nonNull)
                .max(Comparator.comparing(ServiceOrderBudget::getCreatedAt,
                        Comparator.nullsLast(Comparator.naturalOrder())))
                .orElse(null);

        return latestBudget == null ? null : toTrackingResponse(latestBudget);
    }
}
