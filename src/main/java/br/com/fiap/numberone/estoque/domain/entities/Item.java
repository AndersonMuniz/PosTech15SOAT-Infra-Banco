package br.com.fiap.numberone.estoque.domain.entities;

import java.math.BigDecimal;
import java.util.UUID;

public class Item {
    private UUID id;
    private String nome;
    private BigDecimal valorBase;
    private int quantidadeEstoque;
}
