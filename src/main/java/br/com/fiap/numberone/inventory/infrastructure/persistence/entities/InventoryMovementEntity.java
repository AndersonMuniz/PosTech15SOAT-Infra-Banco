package br.com.fiap.numberone.inventory.infrastructure.persistence.entities;

import br.com.fiap.numberone.inventory.domain.enums.InventoryMovementOrigin;
import br.com.fiap.numberone.inventory.domain.enums.InventoryMovementType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "movimentacao_estoque")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryMovementEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "id_item_estoque", nullable = false)
    private UUID inventoryItemId;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_movimentacao", nullable = false, length = 50)
    private InventoryMovementType movementType;

    @Enumerated(EnumType.STRING)
    @Column(name = "origem_movimentacao", length = 50)
    private InventoryMovementOrigin movementOrigin;

    @Column(name = "referencia_origem_id")
    private UUID originReferenceId;

    @Column(name = "quantidade_antes")
    private Integer quantityBefore;

    @Column(name = "quantidade_depois")
    private Integer quantityAfter;

    @Column(name = "observacao", length = 500)
    private String observation;

    @Column(name = "usuario_responsavel_id")
    private UUID responsibleUserId;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}