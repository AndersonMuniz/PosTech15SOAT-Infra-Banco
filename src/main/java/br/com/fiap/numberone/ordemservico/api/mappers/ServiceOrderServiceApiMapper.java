package br.com.fiap.numberone.ordemservico.api.mappers;

import br.com.fiap.numberone.ordemservico.api.dtos.requests.CreateServiceOrderRequest;
import br.com.fiap.numberone.ordemservico.api.dtos.requests.FinalDiagnosisRequest;
import br.com.fiap.numberone.ordemservico.api.dtos.responses.ServiceOrderResponse;
import br.com.fiap.numberone.ordemservico.domain.entities.Diagnosis;
import br.com.fiap.numberone.ordemservico.domain.entities.ServiceOrder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ServiceOrderServiceApiMapper {

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
}
