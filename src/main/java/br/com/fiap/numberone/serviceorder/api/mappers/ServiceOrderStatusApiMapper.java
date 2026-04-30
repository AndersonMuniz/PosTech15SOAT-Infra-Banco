package br.com.fiap.numberone.serviceorder.api.mappers;

import br.com.fiap.numberone.serviceorder.api.dtos.responses.ServiceOrderStatusResponse;
import br.com.fiap.numberone.serviceorder.domain.enums.ServiceOrderStatus;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ServiceOrderStatusApiMapper {

    default ServiceOrderStatusResponse toResponse(ServiceOrderStatus status) {
        if (status == null) {
            return null;
        }

        return switch (status) {
            case RECEIVED -> ServiceOrderStatusResponse.RECEBIDA;
            case IN_DIAGNOSIS -> ServiceOrderStatusResponse.EM_DIAGNOSTICO;
            case WAITING_APPROVAL -> ServiceOrderStatusResponse.AGUARDANDO_APROVACAO;
            case APPROVED -> ServiceOrderStatusResponse.APROVADA;
            case REJECTED -> ServiceOrderStatusResponse.REJEITADA;
            case IN_PROGRESS -> ServiceOrderStatusResponse.EM_EXECUCAO;
            case COMPLETED -> ServiceOrderStatusResponse.FINALIZADA;
            case CANCELLED -> ServiceOrderStatusResponse.CANCELADA;
            case DELIVERED -> ServiceOrderStatusResponse.ENTREGUE;
        };
    }
}
