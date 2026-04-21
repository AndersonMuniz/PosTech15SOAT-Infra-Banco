package br.com.fiap.numberone.serviceorder.infrastructure.persistence.entities;

import br.com.fiap.numberone.automotiveservice.infrastructure.persistence.entities.AutomotiveServiceEntity;
import br.com.fiap.numberone.serviceorder.domain.enums.OrderItemStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "ordem_servico_servico")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceOrderItemEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ordem_servico", nullable = false)
    private ServiceOrderEntity serviceOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_servico")
    private AutomotiveServiceEntity automotiveService;

    @Column(name = "valor")
    private BigDecimal value;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private OrderItemStatus status;

    @Column(name = "opcional")
    private Boolean optional;

    @Column(name = "data_hora_inicio")
    private LocalDateTime startDateTime;

    @Column(name = "data_hora_fim")
    private LocalDateTime endDateTime;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
        if (status == null) {
            status = OrderItemStatus.PENDING;
        }
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
