package br.com.fiap.numberone.ordemservico.infrastructure.persistence.gateways;

import br.com.fiap.numberone.cliente.infrastructure.repositories.ClienteRepository;
import br.com.fiap.numberone.ordemservico.application.gateways.ClienteGateway;
import br.com.fiap.numberone.ordemservico.domain.valueobjects.Cliente;
import br.com.fiap.numberone.ordemservico.infrastructure.persistence.mappers.ClienteResumoMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class ClienteGatewayImpl implements ClienteGateway {

    private final ClienteRepository repository;
    private final ClienteResumoMapper mapper;

    public ClienteGatewayImpl(ClienteRepository clienteRepository, ClienteResumoMapper mapper) {
        this.repository = clienteRepository;
        this.mapper = mapper;
    }


    @Override
    public Optional<Cliente> findById(UUID id) {
        return repository.findById(id)
                .map(mapper::toDomain);
    }
}
