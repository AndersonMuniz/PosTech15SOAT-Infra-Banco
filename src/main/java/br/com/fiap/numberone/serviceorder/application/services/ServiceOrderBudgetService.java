package br.com.fiap.numberone.serviceorder.application.services;

import br.com.fiap.numberone.serviceorder.application.gateways.AutomotiveServiceGateway;
import br.com.fiap.numberone.serviceorder.application.gateways.ServiceOrderGateway;
import br.com.fiap.numberone.serviceorder.application.gateways.ServiceOrderItemGateway;
import br.com.fiap.numberone.serviceorder.domain.entities.ServiceOrder;
import br.com.fiap.numberone.serviceorder.domain.entities.ServiceOrderBudget;
import br.com.fiap.numberone.serviceorder.domain.entities.ServiceOrderItem;
import br.com.fiap.numberone.serviceorder.domain.enums.ServiceOrderStatus;
import br.com.fiap.numberone.serviceorder.domain.exceptions.InvalidServiceOrderStatusException;
import br.com.fiap.numberone.serviceorder.domain.references.AutomotiveService;
import br.com.fiap.numberone.shared.api.exception.ResourceNotFoundException;

import java.util.List;
import java.util.UUID;

public class ServiceOrderBudgetService {

    private final ServiceOrderGateway serviceOrderGateway;

    public ServiceOrderBudgetService(
            ServiceOrderGateway serviceOrderGateway
    ) {
        this.serviceOrderGateway = serviceOrderGateway;
    }

    public ServiceOrderBudget createOrderServiceBudget(ServiceOrderBudget serviceOrderBudget) {

        return serviceOrderBudget;
    }

}
