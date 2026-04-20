package br.com.fiap.numberone.ordemservico.infrastructure.persistence.gateways;


import br.com.fiap.numberone.ordemservico.application.gateways.OrdemServicoGateway;
import br.com.fiap.numberone.ordemservico.domain.entities.OrdemServico;
import br.com.fiap.numberone.ordemservico.infrastructure.persistence.entities.OrdemServicoEntity;
import br.com.fiap.numberone.ordemservico.infrastructure.persistence.mappers.OrdemServicoMapper;
import br.com.fiap.numberone.ordemservico.infrastructure.persistence.repositories.OrdemServicoRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class OrdemServicoGatewayImpl implements OrdemServicoGateway {

    private final OrdemServicoRepository repository;
    private final OrdemServicoMapper mapper;

    public OrdemServicoGatewayImpl(OrdemServicoRepository repository, OrdemServicoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    @Override
    public OrdemServico save(OrdemServico ordemServico) {
        OrdemServicoEntity entity = mapper.toEntity(ordemServico);
        OrdemServicoEntity savedEntity = repository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<OrdemServico> findById(UUID id) {
        return repository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public List<OrdemServico> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }
}
