package br.com.fiap.numberone.estoque.application.gateways;

import br.com.fiap.numberone.estoque.domain.entities.MovimentacaoEstoque;

import java.util.List;
import java.util.UUID;

public interface MovimentacaoGateway {
    void save(MovimentacaoEstoque mov);
    List<MovimentacaoEstoque> findByItemId(UUID itemId);
}

