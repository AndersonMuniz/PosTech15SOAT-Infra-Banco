package br.com.fiap.numberone.estoque.infrastructure.persistence.gateways;

import br.com.fiap.numberone.estoque.application.gateways.ServicoGateway;
import br.com.fiap.numberone.estoque.domain.entities.Servico;
import br.com.fiap.numberone.estoque.infrastructure.persistence.entities.ServicoEntity;
import br.com.fiap.numberone.estoque.infrastructure.persistence.mappers.ServicoMapper;
import br.com.fiap.numberone.estoque.infrastructure.persistence.repositories.ServicoRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class ServicoGatewayImpl implements ServicoGateway {

    private final ServicoRepository repository;
    private final ServicoMapper mapper;

    public ServicoGatewayImpl(ServicoRepository repository, ServicoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Servico save(Servico servico) {
        ServicoEntity entity = mapper.toEntity(servico);
        ServicoEntity savedEntity = repository.save(entity);
        return mapper.toDomain(savedEntity);
    }


    @Override
    public Optional<Servico> findById(UUID id) {
        return repository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public List<Servico> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }
}