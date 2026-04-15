package br.com.fiap.numberone.estoque.infrastructure.persistence.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "servico")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServicoEntity {

    @Id
    @GeneratedValue
    private UUID id;

    private String codigo;
    private String descricao;

    @Column(name = "valor_base")
    private BigDecimal valorBase;

    @Column(name = "tempo_estimado_minuto")
    private Integer tempoEstimadoMinuto;

    private Boolean ativo;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.ativo = true;
    }
}
