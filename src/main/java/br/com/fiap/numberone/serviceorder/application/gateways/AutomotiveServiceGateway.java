package br.com.fiap.numberone.serviceorder.application.gateways;

import br.com.fiap.numberone.serviceorder.domain.references.AutomotiveService;

import java.util.Optional;
import java.util.UUID;

public interface AutomotiveServiceGateway {
    AutomotiveService save(AutomotiveService automotiveService);
    Optional<AutomotiveService> findById(UUID id);
    Optional<AutomotiveService> findByCode(String code);
}
