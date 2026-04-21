package br.com.fiap.numberone.serviceorder.domain.entities;

import br.com.fiap.numberone.serviceorder.domain.enums.ServiceOrderStatus;
import br.com.fiap.numberone.serviceorder.domain.enums.OrderItemStatus;
import br.com.fiap.numberone.serviceorder.domain.exceptions.AutomotiveServiceNotActiveException;
import br.com.fiap.numberone.serviceorder.domain.exceptions.InvalidServiceOrderStatusException;
import br.com.fiap.numberone.serviceorder.domain.references.AutomotiveService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceOrderItem {

    private UUID id;
    private ServiceOrder serviceOrder;
    private AutomotiveService automotiveService;
    private BigDecimal value;
    private OrderItemStatus status;
    private Boolean optional;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public void attachServiceOrder(ServiceOrder serviceOrder) {
        if (List.of(ServiceOrderStatus.CANCELED, ServiceOrderStatus.COMPLETED, ServiceOrderStatus.DELIVERED).contains(serviceOrder.getStatus())) {
            throw new InvalidServiceOrderStatusException("Service order status does not allow attaching new services: " + serviceOrder.getStatus());
        }
        this.serviceOrder = serviceOrder;
    }

    public void attachAutomotiveService(AutomotiveService automotiveService) {
        if(!automotiveService.getActive()){
            throw new AutomotiveServiceNotActiveException("Automotive service is not active to be attached");
        }
        this.automotiveService = automotiveService;
    }

}
