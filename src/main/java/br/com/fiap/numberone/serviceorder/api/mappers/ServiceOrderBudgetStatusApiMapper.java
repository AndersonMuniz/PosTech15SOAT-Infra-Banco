package br.com.fiap.numberone.serviceorder.api.mappers;

import br.com.fiap.numberone.serviceorder.api.dtos.responses.ServiceOrderBudgetStatusResponse;
import br.com.fiap.numberone.serviceorder.domain.enums.ServiceOrderBudgetStatus;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ServiceOrderBudgetStatusApiMapper {

    default ServiceOrderBudgetStatusResponse toResponse(ServiceOrderBudgetStatus status) {
        if (status == null) {
            return null;
        }

        return switch (status) {
            case DRAFT -> ServiceOrderBudgetStatusResponse.RASCUNHO;
            case SENT -> ServiceOrderBudgetStatusResponse.ENVIADO;
            case APPROVED -> ServiceOrderBudgetStatusResponse.APROVADO;
            case REJECTED -> ServiceOrderBudgetStatusResponse.REJEITADO;
            case CANCELLED -> ServiceOrderBudgetStatusResponse.CANCELADO;
        };
    }
}
