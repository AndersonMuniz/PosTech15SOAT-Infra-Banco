package br.com.fiap.numberone.estoque.infrastructure.persistence.gateways;

import br.com.fiap.numberone.estoque.application.gateways.ItemGateway;
import br.com.fiap.numberone.estoque.domain.entities.Item;
import br.com.fiap.numberone.estoque.infrastructure.persistence.repositories.ItemRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class ItemGatewayImpl implements ItemGateway {

    private final ItemRepository repository;

    public ItemGatewayImpl(ItemRepository repository) {
        this.repository = repository;
    }

    @Override
    public Item save(Item item) {
        return null;
    }

    @Override
    public Optional<Item> findById(UUID id) {
        return Optional.empty();
    }

    @Override
    public List<Item> findAll() {
        return List.of();
    }
}
