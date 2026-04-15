package br.com.fiap.numberone.estoque.infrastructure.persistence.mappers;

import br.com.fiap.numberone.estoque.domain.entities.MovimentacaoEstoque;
import br.com.fiap.numberone.estoque.infrastructure.persistence.entities.MovimentacaoEstoqueEntity;

public class MovimentacaoMapper {

    public static MovimentacaoEstoqueEntity toEntity(MovimentacaoEstoque domain) {
        MovimentacaoEstoqueEntity e = new MovimentacaoEstoqueEntity();
//        e.setId(domain.getId());
//        e.setItemId(domain.getItemId());
//        e.setQuantidade(domain.getQuantidade());
//        e.setMotivo(domain.getMotivo());
//        e.setTipoMovimentacao(domain.getTipo().name());
//        e.setCreatedAt(domain.getCreatedAt());
        return e;
    }
}
