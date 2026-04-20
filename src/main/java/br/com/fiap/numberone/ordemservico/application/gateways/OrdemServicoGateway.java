package br.com.fiap.numberone.ordemservico.application.gateways;

import br.com.fiap.numberone.ordemservico.domain.entities.OrdemServico;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrdemServicoGateway {

    OrdemServico save(OrdemServico servico);
    Optional<OrdemServico> findById(UUID id);
    List<OrdemServico> findAll();

}
