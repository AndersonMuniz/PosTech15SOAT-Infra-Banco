package br.com.fiap.numberone.automotiveservice.application.gateways;

import br.com.fiap.numberone.automotiveservice.domain.entities.AutomotiveService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AutoServiceGateway {
    AutomotiveService save(AutomotiveService servico);
    Optional<AutomotiveService> findById(UUID id);
    List<AutomotiveService> findAll();
}
