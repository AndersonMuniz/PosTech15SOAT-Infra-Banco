package br.com.fiap.numberone.estoque.application.gateways;

import br.com.fiap.numberone.estoque.domain.entities.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ServicoGateway {
    Servico save(Servico servico);
    Optional<Servico> findById(UUID id);
    List<Servico> findAll();
}

