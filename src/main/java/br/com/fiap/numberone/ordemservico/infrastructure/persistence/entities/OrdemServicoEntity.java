package br.com.fiap.numberone.ordemservico.infrastructure.persistence.entities;

import br.com.fiap.numberone.cliente.domain.entities.Cliente;
import br.com.fiap.numberone.ordemservico.domain.enums.StatusOrdemServico;
import br.com.fiap.numberone.veiculo.domain.entities.VeiculoEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrdemServicoEntity {

    @Id
    @GeneratedValue
    private UUID id;

    private String descricaoInicial;

    private String descricaoDiagnostico;

    private String descricaoDiagnosticoFinal;

    private String observacao;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_veiculo")
    private VeiculoEntity veiculoEntity;

    @Enumerated(EnumType.STRING)
    private StatusOrdemServico status = StatusOrdemServico.RECEBIDA;

    private LocalDateTime dataHoraEntrada;

    private LocalDateTime dataHoraPrevista;

    private LocalDateTime dataHoraEntrega;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

}
