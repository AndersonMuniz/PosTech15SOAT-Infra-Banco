package br.com.fiap.numberone.serviceorder.api.mappers;

import br.com.fiap.numberone.serviceorder.api.dtos.requests.CreateServiceOrderRequest;
import br.com.fiap.numberone.serviceorder.api.dtos.requests.FinalDiagnosisRequest;
import br.com.fiap.numberone.serviceorder.api.dtos.responses.ServiceOrderResponse;
import br.com.fiap.numberone.serviceorder.domain.entities.Diagnosis;
import br.com.fiap.numberone.serviceorder.domain.entities.ServiceOrder;
import br.com.fiap.numberone.serviceorder.domain.valueobjects.Customer;
import br.com.fiap.numberone.serviceorder.domain.valueobjects.Vehicle;
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

    ServiceOrderResponse toResponse(ServiceOrder entity);

    ServiceOrderResponse.CustomerResponse toResponse(Customer customer);

    ServiceOrderResponse.VehicleResponse toResponse(Vehicle vehicle);
}
