package br.com.fiap.numberone.ordemservico.infrastructure.persistence.gateways;

import br.com.fiap.numberone.cliente.infrastructure.repositories.ClienteRepository;
import br.com.fiap.numberone.ordemservico.application.gateways.CustomerGateway;
import br.com.fiap.numberone.ordemservico.domain.valueobjects.Customer;
import br.com.fiap.numberone.ordemservico.infrastructure.persistence.mappers.CustomerSummaryMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class CustomerGatewayImpl implements CustomerGateway {

    private final ClienteRepository repository;
    private final CustomerSummaryMapper mapper;

    public CustomerGatewayImpl(ClienteRepository customerRepository, CustomerSummaryMapper mapper) {
        this.repository = customerRepository;
        this.mapper = mapper;
    }

    @Override
    public Optional<Customer> findById(UUID id) {
        return repository.findById(id)
                .map(mapper::toDomain);
    }
}
