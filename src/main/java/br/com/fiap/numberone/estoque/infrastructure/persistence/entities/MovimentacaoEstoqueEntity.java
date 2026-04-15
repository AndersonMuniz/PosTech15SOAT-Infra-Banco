package br.com.fiap.numberone.estoque.infrastructure.persistence.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "movimentacao_estoque")
public class MovimentacaoEstoqueEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "id_item")
    private UUID itemId;

    @Column(name = "tipo_movimentacao")
    private String tipoMovimentacao;

    private Integer quantidade;
    private String motivo;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    // getters e setters
}
