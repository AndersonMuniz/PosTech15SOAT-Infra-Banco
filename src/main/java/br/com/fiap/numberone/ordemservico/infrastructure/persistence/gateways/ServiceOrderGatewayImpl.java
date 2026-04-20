package br.com.fiap.numberone.ordemservico.infrastructure.persistence.gateways;

import br.com.fiap.numberone.ordemservico.application.gateways.ServiceOrderGateway;
import br.com.fiap.numberone.ordemservico.domain.entities.ServiceOrder;
import br.com.fiap.numberone.ordemservico.infrastructure.persistence.entities.ServiceOrderEntity;
import br.com.fiap.numberone.ordemservico.infrastructure.persistence.mappers.ServiceOrderMapper;
import br.com.fiap.numberone.ordemservico.infrastructure.persistence.repositories.ServiceOrderRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class ServiceOrderGatewayImpl implements ServiceOrderGateway {

    private final ServiceOrderRepository repository;
    private final ServiceOrderMapper mapper;

    public ServiceOrderGatewayImpl(ServiceOrderRepository repository, ServiceOrderMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ServiceOrder save(ServiceOrder serviceOrder) {
        ServiceOrderEntity entity = mapper.toEntity(serviceOrder);
        ServiceOrderEntity savedEntity = repository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<ServiceOrder> findById(UUID id) {
        return repository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public List<ServiceOrder> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }
}
