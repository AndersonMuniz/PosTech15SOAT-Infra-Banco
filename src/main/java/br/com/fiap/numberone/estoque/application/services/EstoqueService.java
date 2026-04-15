package br.com.fiap.numberone.estoque.application.services;

import br.com.fiap.numberone.estoque.application.gateways.ItemGateway;
import br.com.fiap.numberone.estoque.application.gateways.MovimentacaoGateway;

public class EstoqueService {

    private final ItemGateway itemGateway;
    private final MovimentacaoGateway movimentacaoGateway;

    public EstoqueService(ItemGateway itemGateway,
                          MovimentacaoGateway movimentacaoGateway) {
        this.itemGateway = itemGateway;
        this.movimentacaoGateway = movimentacaoGateway;
    }
}
