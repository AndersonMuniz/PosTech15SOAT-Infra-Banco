package br.com.fiap.numberone.serviceorder.api.mappers;

import br.com.fiap.numberone.serviceorder.api.dtos.responses.ServiceOrderItemStatusResponse;
import br.com.fiap.numberone.serviceorder.domain.enums.OrderItemStatus;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ServiceOrderItemStatusApiMapper {

    default ServiceOrderItemStatusResponse toResponse(OrderItemStatus status) {
        if (status == null) {
            return null;
        }

        return switch (status) {
            case PENDING -> ServiceOrderItemStatusResponse.PENDENTE;
            case WAITING_FOR_PARTS_AND_SUPPLIES -> ServiceOrderItemStatusResponse.AGUARDANDO_PECAS_E_INSUMOS;
            case IN_PROGRESS -> ServiceOrderItemStatusResponse.EM_EXECUCAO;
            case CANCELLED -> ServiceOrderItemStatusResponse.CANCELADO;
            case COMPLETED -> ServiceOrderItemStatusResponse.FINALIZADO;
        };
    }
}
