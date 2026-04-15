package br.com.fiap.numberone.estoque.application.gateways;

import br.com.fiap.numberone.estoque.domain.entities.Item;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ItemGateway {
    Item save(Item item);
    Optional<Item> findById(UUID id);
    List<Item> findAll();
}
