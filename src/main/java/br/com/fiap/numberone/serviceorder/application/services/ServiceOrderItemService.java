package br.com.fiap.numberone.serviceorder.application.services;

import br.com.fiap.numberone.serviceorder.application.gateways.AutomotiveServiceGateway;
import br.com.fiap.numberone.serviceorder.application.gateways.ServiceOrderGateway;
import br.com.fiap.numberone.serviceorder.application.gateways.ServiceOrderItemGateway;
import br.com.fiap.numberone.serviceorder.domain.entities.ServiceOrder;
import br.com.fiap.numberone.serviceorder.domain.entities.ServiceOrderItem;
import br.com.fiap.numberone.serviceorder.domain.references.AutomotiveService;
import br.com.fiap.numberone.shared.api.exception.ResourceNotFoundException;

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

    public ServiceOrderItem createServiceOrderService(UUID serviceOrderId, ServiceOrderItem serviceOrderItem) {
        ServiceOrder serviceOrder = serviceOrderGateway.findById(serviceOrderId)
                .orElseThrow(() -> new ResourceNotFoundException("Service order not found for id: " + serviceOrderId));

        UUID automotiveServiceId = serviceOrderItem.getAutomotiveService().getId();
        AutomotiveService automotiveService = automotiveServiceGateway.findById(automotiveServiceId)
                .orElseThrow(() -> new ResourceNotFoundException("Automotive service not found for id: " + automotiveServiceId));

        serviceOrderItem.attachServiceOrder(serviceOrder);
        serviceOrderItem.attachAutomotiveService(automotiveService);

        return serviceOrderItemGateway.save(serviceOrderItem);
    }
}
