package br.com.fiap.numberone.estoque.api.mappers;

import br.com.fiap.numberone.estoque.api.dto.requests.ItemRequest;
import br.com.fiap.numberone.estoque.api.dto.responses.ItemResponse;
import br.com.fiap.numberone.estoque.domain.entities.Item;

public class ItemApiMapper {

    public static Item toDomain(ItemRequest request) {
        Item i = new Item();
//        i.setNome(request.getNome());
//        i.setDescricao(request.getDescricao());
//        i.setValorBase(request.getValorBase());
        return i;
    }

    public static ItemResponse toResponse(Item i) {
        ItemResponse r = new ItemResponse();
//        r.setId(i.getId());
//        r.setNome(i.getNome());
//        r.setDescricao(i.getDescricao());
//        r.setValorBase(i.getValorBase());
//        r.setQuantidadeEstoque(i.getQuantidadeEstoque());
        return r;
    }
}
