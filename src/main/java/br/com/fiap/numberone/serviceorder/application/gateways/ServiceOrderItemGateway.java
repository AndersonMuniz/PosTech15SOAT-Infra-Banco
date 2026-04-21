package br.com.fiap.numberone.serviceorder.application.gateways;

import br.com.fiap.numberone.serviceorder.domain.entities.ServiceOrderItem;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ServiceOrderItemGateway {

    ServiceOrderItem save(ServiceOrderItem serviceOrderItem);

    Optional<ServiceOrderItem> findById(UUID id);

    List<ServiceOrderItem> findAll();
}
