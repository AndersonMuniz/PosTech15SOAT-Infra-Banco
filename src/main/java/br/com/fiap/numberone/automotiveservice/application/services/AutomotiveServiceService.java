package br.com.fiap.numberone.automotiveservice.application.services;

import br.com.fiap.numberone.automotiveservice.application.gateways.AutoServiceGateway;
import br.com.fiap.numberone.automotiveservice.domain.entities.AutomotiveService;
import br.com.fiap.numberone.automotiveservice.domain.exceptions.AutoServiceNotFoundException;
import br.com.fiap.numberone.shared.application.gateways.LoggerGateway;
import java.util.List;
import java.util.UUID;


public class AutomotiveServiceService {

    private final AutoServiceGateway autoServiceGateway;
    private final LoggerGateway logger;

    public AutomotiveServiceService(AutoServiceGateway autoServiceGateway, LoggerGateway loggerGateway) {
        this.autoServiceGateway = autoServiceGateway;
        this.logger = loggerGateway;
    }


    public AutomotiveService create(AutomotiveService autoService) {
        return autoServiceGateway.save(autoService);
    }

    public AutomotiveService update(UUID id, AutomotiveService autoService) {
        AutomotiveService currentService = autoServiceGateway.findById(id)
                .orElseThrow(() -> new AutoServiceNotFoundException("Serviço automotivo não encontrado"));

        currentService.updateFrom(autoService);

        return autoServiceGateway.save(currentService);
    }

    public List<AutomotiveService> findAll() {
        logger.info("Buscando todos os serviços");
        return autoServiceGateway.findAll();
    }

    public AutomotiveService findById(UUID id) {
        logger.info("Buscando autoService {}", id);
        return autoServiceGateway.findById(id)
                .orElseThrow(() -> new AutoServiceNotFoundException("Serviço automotivo não encontrado"));
    }

    public void inactivate(UUID id) {
        AutomotiveService autoService = autoServiceGateway.findById(id)
                .orElseThrow(() -> new AutoServiceNotFoundException("Serviço automotivo não encontrado"));

        autoService.deactivate();

        autoServiceGateway.save(autoService);
    }
}
