package br.com.fiap.numberone.serviceorder.api.mappers;

import br.com.fiap.numberone.serviceorder.api.dtos.requests.CreateServiceOrderRequest;
import br.com.fiap.numberone.serviceorder.api.dtos.requests.FinalDiagnosisRequest;
import br.com.fiap.numberone.serviceorder.api.dtos.responses.*;
import br.com.fiap.numberone.serviceorder.domain.entities.ServiceOrderBudget;
import br.com.fiap.numberone.serviceorder.domain.entities.ServiceOrderItem;
import br.com.fiap.numberone.serviceorder.domain.entities.ServiceOrderItemSupply;
import br.com.fiap.numberone.serviceorder.domain.references.AutomotiveService;
import br.com.fiap.numberone.serviceorder.domain.valueobjects.Diagnosis;
import br.com.fiap.numberone.serviceorder.domain.entities.ServiceOrder;
import br.com.fiap.numberone.serviceorder.domain.references.Customer;
import br.com.fiap.numberone.serviceorder.domain.references.InventoryItem;
import br.com.fiap.numberone.serviceorder.domain.references.Vehicle;
import br.com.fiap.numberone.serviceorder.domain.valueobjects.ServiceOrderAverageExecutionTime;
import br.com.fiap.numberone.serviceorder.domain.valueobjects.ServiceOrderEstimatedTime;
import br.com.fiap.numberone.serviceorder.domain.valueobjects.ServiceOrderValue;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {
        ServiceOrderStatusApiMapper.class,
        ServiceOrderItemStatusApiMapper.class,
        ServiceOrderBudgetStatusApiMapper.class
})
public interface ServiceOrderApiMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "expectedDateTime", ignore = true)
    @Mapping(target = "deliveryDateTime", ignore = true)
    @Mapping(target = "finalDiagnosisDescription", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "customer", expression = "java(toCustomerReference(dto))")
    @Mapping(target = "vehicle", expression = "java(toVehicleReference(dto))")
    @Mapping(target = "serviceItems", source = "serviceItems")
    ServiceOrder toDomain(CreateServiceOrderRequest dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "serviceOrder", ignore = true)
    @Mapping(target = "automotiveService", expression = "java(toAutomotiveServiceReference(dto))")
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "startDateTime", ignore = true)
    @Mapping(target = "endDateTime", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "supplies", source = "supplies")
    ServiceOrderItem toDomain(CreateServiceOrderRequest.ServiceItemData dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "serviceOrderItem", ignore = true)
    @Mapping(target = "inventoryItem", expression = "java(toInventoryItemReference(dto))")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    ServiceOrderItemSupply toDomain(CreateServiceOrderRequest.SupplyData dto);

    default Customer toCustomerReference(CreateServiceOrderRequest dto) {
        CreateServiceOrderRequest.CustomerData customer = dto.customer();
        return Customer.builder()
                .id(dto.customerId())
                .name(customer != null ? customer.name() : null)
                .documentType(customer != null ? customer.documentType() : null)
                .document(customer != null ? customer.document() : null)
                .email(customer != null ? customer.email() : null)
                .phone(customer != null ? customer.phone() : null)
                .address(customer != null ? customer.address() : null)
                .active(customer == null || customer.active() == null ? Boolean.TRUE : customer.active())
                .build();
    }

    default Vehicle toVehicleReference(CreateServiceOrderRequest dto) {
        CreateServiceOrderRequest.VehicleData vehicle = dto.vehicle();
        return Vehicle.builder()
                .id(dto.vehicleId())
                .licensePlate(vehicle != null ? vehicle.licensePlate() : null)
                .brand(vehicle != null ? vehicle.brand() : null)
                .model(vehicle != null ? vehicle.model() : null)
                .year(vehicle != null ? vehicle.year() : null)
                .build();
    }

    default AutomotiveService toAutomotiveServiceReference(CreateServiceOrderRequest.ServiceItemData dto) {
        return AutomotiveService.builder()
                .id(dto.serviceId())
                .code(dto.code())
                .name(dto.name())
                .description(dto.description())
                .serviceType(dto.serviceType() != null ? dto.serviceType().name() : null)
                .baseValue(dto.baseValue())
                .estimatedTimeMinutes(dto.estimatedTimeMinutes())
                .active(Boolean.TRUE)
                .build();
    }

    default InventoryItem toInventoryItemReference(CreateServiceOrderRequest.SupplyData dto) {
        return InventoryItem.builder()
                .id(dto.inventoryItemId())
                .code(dto.code())
                .name(dto.name())
                .description(dto.description())
                .itemType(dto.itemType())
                .unitOfMeasure(dto.unitOfMeasure())
                .costPerUnit(dto.costPerUnit())
                .salePrice(dto.salePrice())
                .inventoryQuantity(dto.inventoryQuantity())
                .minimumInventoryQuantity(dto.minimumInventoryQuantity())
                .brand(dto.brand())
                .applicableVehicle(dto.applicableVehicle())
                .active(Boolean.TRUE)
                .build();
    }

    Diagnosis toDomain(FinalDiagnosisRequest dto);

    ServiceOrderValueResponse toResponse(ServiceOrderValue valueObject);

    ServiceOrderEstimatedTimeResponse toResponse(ServiceOrderEstimatedTime valueObject);

    ServiceOrderAverageExecutionTimeResponse toResponse(ServiceOrderAverageExecutionTime valueObject);

    ServiceOrderResponse toResponse(ServiceOrder entity);

    ServiceOrderResponse.CustomerResponse toResponse(Customer customer);

    @Mapping(target = "licensePlate", source = "licensePlate")
    @Mapping(target = "brand", source = "brand")
    @Mapping(target = "model", source = "model")
    @Mapping(target = "year", source = "year")
    @Mapping(target = "customerId", source = "customerId")
    ServiceOrderResponse.VehicleResponse toResponse(Vehicle vehicle);

    @Mapping(target = "serviceOrderId", source = "serviceOrder.id")
    ServiceOrderItemResponse toResponse(ServiceOrderItem serviceOrderItem);

    @Mapping(target = "serviceOrderItemId", source = "serviceOrderItem.id")
    ServiceOrderItemSupplyResponse toResponse(ServiceOrderItemSupply serviceOrderItemSupply);

    @Mapping(target = "serviceOrderId", source = "serviceOrder.id")
    ServiceOrderBudgetResponse toResponse(ServiceOrderBudget serviceOrderBudget);
}
