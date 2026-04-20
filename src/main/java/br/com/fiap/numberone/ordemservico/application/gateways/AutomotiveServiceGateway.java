package br.com.fiap.numberone.ordemservico.application.gateways;

import br.com.fiap.numberone.ordemservico.domain.valueobjects.AutomotiveService;
import br.com.fiap.numberone.ordemservico.domain.valueobjects.Customer;

import java.util.Optional;
import java.util.UUID;

public interface AutomotiveServiceGateway {
    Optional<AutomotiveService> findById(UUID id);
}
