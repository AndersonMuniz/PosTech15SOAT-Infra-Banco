package br.com.fiap.numberone.ordemservico.application.gateways;

import br.com.fiap.numberone.ordemservico.domain.entities.ServiceOrder;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ServiceOrderGateway {

    ServiceOrder save(ServiceOrder serviceOrder);

    Optional<ServiceOrder> findById(UUID id);

    List<ServiceOrder> findAll();
}
