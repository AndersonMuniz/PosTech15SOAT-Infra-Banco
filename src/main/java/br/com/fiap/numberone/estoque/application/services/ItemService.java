package br.com.fiap.numberone.estoque.application.services;

import br.com.fiap.numberone.estoque.application.gateways.ItemGateway;
import br.com.fiap.numberone.estoque.domain.entities.Item;

import java.util.List;
import java.util.UUID;

public class ItemService {

    private final ItemGateway itemGateway;

    public ItemService(ItemGateway itemGateway) {
        this.itemGateway = itemGateway;
    }

    public Item criar(Item item) {
//        if (item.getValorBase().doubleValue() < 0) {
//            throw new IllegalArgumentException("Valor não pode ser negativo");
//        }

        return itemGateway.save(item);
    }

    public Item atualizar(UUID id, Item atualizado) {
        Item existente = itemGateway.findById(id)
                .orElseThrow(() -> new RuntimeException("Item não encontrado"));

//        existente.setNome(atualizado.getNome());
//        existente.setDescricao(atualizado.getDescricao());
//        existente.setValorBase(atualizado.getValorBase());

        return itemGateway.save(existente);
    }

    public List<Item> listar() {
        return itemGateway.findAll();
    }

    public Item detalhar(UUID id) {
        return itemGateway.findById(id)
                .orElseThrow(() -> new RuntimeException("Item não encontrado"));
    }

}
