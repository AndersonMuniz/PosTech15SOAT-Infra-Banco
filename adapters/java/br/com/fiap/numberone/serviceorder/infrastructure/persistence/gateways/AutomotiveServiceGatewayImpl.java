package br.com.fiap.numberone.serviceorder.infrastructure.persistence.gateways;

import br.com.fiap.numberone.automotiveservice.domain.enums.ServiceType;
import br.com.fiap.numberone.automotiveservice.infrastructure.persistence.repositories.AutoServiceRepository;
import br.com.fiap.numberone.serviceorder.application.gateways.AutomotiveServiceGateway;
import br.com.fiap.numberone.serviceorder.domain.references.AutomotiveService;
import br.com.fiap.numberone.serviceorder.infrastructure.persistence.mappers.AutomotiveServiceMapper;
import br.com.fiap.numberone.automotiveservice.infrastructure.persistence.entities.AutomotiveServiceEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Component
public class AutomotiveServiceGatewayImpl implements AutomotiveServiceGateway {

    private final AutoServiceRepository repository;
    private final AutomotiveServiceMapper mapper;

    public AutomotiveServiceGatewayImpl(AutoServiceRepository repository, AutomotiveServiceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public AutomotiveService save(AutomotiveService automotiveService) {
        AutomotiveServiceEntity entity = new AutomotiveServiceEntity(
                automotiveService.getId(),
                automotiveService.getCode(),
                automotiveService.getName(),
                automotiveService.getDescription(),
                ServiceType.valueOf(automotiveService.getServiceType()),
                automotiveService.getBaseValue(),
                automotiveService.getEstimatedTimeMinutes(),
                automotiveService.getActive(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        return mapper.toDomain(repository.save(entity));
    }

    @Override
    public Optional<AutomotiveService> findById(UUID id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Optional<AutomotiveService> findByCode(String code) {
        return repository.findByCode(code).map(mapper::toDomain);
    }
}
