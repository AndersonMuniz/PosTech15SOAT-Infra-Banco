package br.com.fiap.numberone.serviceorder.infrastructure.persistence.gateways;

import br.com.fiap.numberone.serviceorder.application.gateways.ServiceOrderItemGateway;
import br.com.fiap.numberone.serviceorder.domain.entities.ServiceOrderItem;
import br.com.fiap.numberone.serviceorder.infrastructure.persistence.entities.ServiceOrderItemEntity;
import br.com.fiap.numberone.serviceorder.infrastructure.persistence.mappers.ServiceOrderItemMapper;
import br.com.fiap.numberone.serviceorder.infrastructure.persistence.repositories.ServiceOrderItemRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class ServiceOrderItemGatewayImpl implements ServiceOrderItemGateway {

    private final ServiceOrderItemRepository repository;
    private final ServiceOrderItemMapper mapper;

    public ServiceOrderItemGatewayImpl(ServiceOrderItemRepository repository, ServiceOrderItemMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public ServiceOrderItem save(ServiceOrderItem serviceOrderItem) {
        ServiceOrderItemEntity entity = mapper.toEntity(serviceOrderItem);
        ServiceOrderItemEntity savedEntity = repository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ServiceOrderItem> findById(UUID id) {
        return repository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ServiceOrderItem> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}
