package br.com.fiap.numberone.serviceorder.application.services;

import br.com.fiap.numberone.serviceorder.application.gateways.AutomotiveServiceGateway;
import br.com.fiap.numberone.serviceorder.application.gateways.ServiceOrderGateway;
import br.com.fiap.numberone.serviceorder.domain.entities.ServiceOrder;
import br.com.fiap.numberone.serviceorder.domain.entities.ServiceOrderAutoservice;
import br.com.fiap.numberone.shared.api.exception.ResourceNotFoundException;

import java.util.UUID;

public class ServiceOrderAutoService {

    private final ServiceOrderGateway serviceOrderGateway;
    private final AutomotiveServiceGateway automotiveServiceGateway;

    public ServiceOrderAutoService(
            ServiceOrderGateway serviceOrderGateway, AutomotiveServiceGateway automotiveServiceGateway
    ) {
        this.serviceOrderGateway = serviceOrderGateway;
        this.automotiveServiceGateway = automotiveServiceGateway;
    }

    public ServiceOrderAutoservice createServiceOrderService(UUID serviceOrderId, ServiceOrderAutoservice serviceOrderAutoservice) {
        ServiceOrder serviceOrder = serviceOrderGateway.findById(serviceOrderId)
                .orElseThrow(() -> new ResourceNotFoundException("Service order not found for id: " + serviceOrderId));

        return null;
    }
}
