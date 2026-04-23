package br.com.fiap.numberone.serviceorder.application.services;

import br.com.fiap.numberone.serviceorder.application.gateways.AutomotiveServiceGateway;
import br.com.fiap.numberone.serviceorder.application.gateways.ServiceOrderGateway;
import br.com.fiap.numberone.serviceorder.application.gateways.ServiceOrderItemGateway;
import br.com.fiap.numberone.serviceorder.domain.entities.ServiceOrder;
import br.com.fiap.numberone.serviceorder.domain.entities.ServiceOrderItem;
import br.com.fiap.numberone.serviceorder.domain.enums.ServiceOrderStatus;
import br.com.fiap.numberone.serviceorder.domain.exceptions.InvalidServiceOrderStatusException;
import br.com.fiap.numberone.serviceorder.domain.references.AutomotiveService;
import br.com.fiap.numberone.shared.api.exception.ResourceNotFoundException;

import java.util.List;
import java.util.UUID;

public class ServiceOrderItemService {

    private final ServiceOrderGateway serviceOrderGateway;
    private final ServiceOrderItemGateway serviceOrderItemGateway;
    private final AutomotiveServiceGateway automotiveServiceGateway;

    public ServiceOrderItemService(
            ServiceOrderGateway serviceOrderGateway,
            ServiceOrderItemGateway serviceOrderItemGateway,
            AutomotiveServiceGateway automotiveServiceGateway
    ) {
        this.serviceOrderGateway = serviceOrderGateway;
        this.serviceOrderItemGateway = serviceOrderItemGateway;
        this.automotiveServiceGateway = automotiveServiceGateway;
    }

    public ServiceOrderItem createServiceOrderItem(ServiceOrderItem serviceOrderItem) {
        UUID serviceOrderId = serviceOrderItem.getServiceOrder().getId();
        ServiceOrder serviceOrder = serviceOrderGateway.findById(serviceOrderId)
                .orElseThrow(() -> new ResourceNotFoundException("Service order not found for id: " + serviceOrderId));

        UUID automotiveServiceId = serviceOrderItem.getAutomotiveService().getId();
        AutomotiveService automotiveService = automotiveServiceGateway.findById(automotiveServiceId)
                .orElseThrow(() -> new ResourceNotFoundException("Automotive service not found for id: " + automotiveServiceId));

        serviceOrderItem.attachServiceOrder(serviceOrder);
        serviceOrderItem.attachAutomotiveService(automotiveService);

        return serviceOrderItemGateway.save(serviceOrderItem);
    }

    public void deleteServiceOrderItem(UUID id) {
        ServiceOrderItem serviceOrderItem = serviceOrderItemGateway.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Service order not found for id: " + id));

        UUID serviceOrderId = serviceOrderItem.getId();
        ServiceOrder serviceOrder = serviceOrderGateway.findById(serviceOrderId)
                .orElseThrow(() -> new ResourceNotFoundException("Service order not found for id: " + serviceOrderId));

        if (!List.of(ServiceOrderStatus.RECEIVED, ServiceOrderStatus.IN_DIAGNOSIS).contains(serviceOrder.getStatus())) {
            throw new InvalidServiceOrderStatusException("Service order status does not allow deleting service item: " + serviceOrder.getStatus());
        }

        serviceOrderItemGateway.deleteById(id);
    }
}
