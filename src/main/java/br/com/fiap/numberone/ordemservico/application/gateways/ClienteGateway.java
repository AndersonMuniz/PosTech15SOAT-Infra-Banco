package br.com.fiap.numberone.ordemservico.application.gateways;

import br.com.fiap.numberone.ordemservico.domain.valueobjects.Cliente;

import java.util.Optional;
import java.util.UUID;

public interface ClienteGateway {
    Optional<Cliente> findById(UUID id);
}
