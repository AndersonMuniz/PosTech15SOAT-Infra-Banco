package br.com.fiap.numberone.ordemservico.infrastructure.persistence.gateways;

import br.com.fiap.numberone.ordemservico.application.gateways.VehicleGateway;
import br.com.fiap.numberone.ordemservico.domain.valueobjects.Vehicle;
import br.com.fiap.numberone.ordemservico.infrastructure.persistence.mappers.VehicleSummaryMapper;
import br.com.fiap.numberone.veiculo.infrastructure.repositories.VeiculoRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class VehicleGatewayImpl implements VehicleGateway {

    private final VeiculoRepository repository;
    private final VehicleSummaryMapper mapper;

    public VehicleGatewayImpl(VeiculoRepository repository, VehicleSummaryMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Optional<Vehicle> findById(UUID id) {
        return repository.findById(id)
                .map(mapper::toDomain);
    }
}
