package br.com.fiap.numberone.estoque.api.dto.responses;

import java.math.BigDecimal;
import java.util.UUID;

public class ItemResponse {

    private UUID id;
    private String nome;
    private String descricao;
    private BigDecimal valorBase;
    private Integer quantidadeEstoque;

    // getters e setters
}