package br.com.fiap.numberone.estoque.infrastructure.persistence.mappers;

import br.com.fiap.numberone.estoque.domain.entities.Item;
import br.com.fiap.numberone.estoque.infrastructure.persistence.entities.ItemEntity;

public class ItemMapper {

    public static ItemEntity toEntity(Item domain) {
        ItemEntity e = new ItemEntity();
//        e.setId(domain.getId());
//        e.setNome(domain.getNome());
//        e.setDescricao(domain.getDescricao());
//        e.setValorBase(domain.getValorBase());
//        e.setQuantidadeEstoque(domain.getQuantidadeEstoque());
        return e;
    }

    public static Item toDomain(ItemEntity entity) {
        Item i = new Item();
//        i.setId(entity.getId());
//        i.setNome(entity.getNome());
//        i.setDescricao(entity.getDescricao());
//        i.setValorBase(entity.getValorBase());
//        i.setQuantidadeEstoque(entity.getQuantidadeEstoque());
        return i;
    }
}
