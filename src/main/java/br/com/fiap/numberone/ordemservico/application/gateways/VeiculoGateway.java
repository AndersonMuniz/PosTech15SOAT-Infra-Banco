package br.com.fiap.numberone.ordemservico.application.gateways;

import br.com.fiap.numberone.ordemservico.domain.valueobjects.Veiculo;

import java.util.Optional;
import java.util.UUID;

public interface VeiculoGateway {
    Optional<Veiculo> findById(UUID id);
}
