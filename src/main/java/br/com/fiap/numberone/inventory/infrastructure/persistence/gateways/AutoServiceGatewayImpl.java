package br.com.fiap.numberone.inventory.infrastructure.persistence.gateways;

import br.com.fiap.numberone.inventory.application.gateways.AutoServiceGateway;
import br.com.fiap.numberone.inventory.domain.entities.AutomotiveService;
import br.com.fiap.numberone.inventory.infrastructure.persistence.entities.AutomotiveServiceEntity;
import br.com.fiap.numberone.inventory.infrastructure.persistence.mappers.AutomotiveServicePersistenceMapper;
import br.com.fiap.numberone.inventory.infrastructure.persistence.repositories.AutoServiceRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class AutoServiceGatewayImpl implements AutoServiceGateway {

    private final AutoServiceRepository repository;
    private final AutomotiveServicePersistenceMapper mapper;

    public AutoServiceGatewayImpl(AutoServiceRepository repository, AutomotiveServicePersistenceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public AutomotiveService save(AutomotiveService servico) {
        AutomotiveServiceEntity entity = mapper.toEntity(servico);
        AutomotiveServiceEntity savedEntity = repository.save(entity);
        return mapper.toDomain(savedEntity);
    }


    @Override
    public Optional<AutomotiveService> findById(UUID id) {
        return repository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public List<AutomotiveService> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }
}