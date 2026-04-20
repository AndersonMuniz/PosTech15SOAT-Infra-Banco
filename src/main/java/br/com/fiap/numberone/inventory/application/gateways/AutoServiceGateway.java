package br.com.fiap.numberone.inventory.application.gateways;

import br.com.fiap.numberone.inventory.domain.entities.AutomotiveService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AutoServiceGateway {
    AutomotiveService save(AutomotiveService servico);
    Optional<AutomotiveService> findById(UUID id);
    List<AutomotiveService> findAll();
}
