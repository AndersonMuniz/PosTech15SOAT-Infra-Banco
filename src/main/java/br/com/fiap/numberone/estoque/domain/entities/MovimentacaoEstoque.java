package br.com.fiap.numberone.estoque.domain.entities;

import br.com.fiap.numberone.estoque.domain.enums.TipoMovimentacaoEstoque;

import java.time.LocalDateTime;
import java.util.UUID;

public class MovimentacaoEstoque {
    private UUID id;
    private UUID itemId;
    private TipoMovimentacaoEstoque tipo;
    private int quantidade;
    private String motivo;
    private LocalDateTime createdAt;
}
