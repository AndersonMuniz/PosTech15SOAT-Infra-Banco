package br.com.fiap.numberone.ordemservico.infrastructure.persistence.gateways;

import br.com.fiap.numberone.ordemservico.application.gateways.VeiculoGateway;
import br.com.fiap.numberone.ordemservico.domain.valueobjects.Veiculo;
import br.com.fiap.numberone.ordemservico.infrastructure.persistence.mappers.VeiculoResumoMapper;
import br.com.fiap.numberone.veiculo.infrastructure.repositories.VeiculoRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class VeiculoGatewayImpl implements VeiculoGateway {

    private final VeiculoRepository repository;
    private final VeiculoResumoMapper mapper;

    public VeiculoGatewayImpl(VeiculoRepository repository, VeiculoResumoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    @Override
    public Optional<Veiculo> findById(UUID id) {
        return repository.findById(id)
                .map(mapper::toDomain);
    }
}
