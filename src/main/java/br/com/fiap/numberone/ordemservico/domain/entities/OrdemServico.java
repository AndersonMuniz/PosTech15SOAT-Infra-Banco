package br.com.fiap.numberone.ordemservico.domain.entities;

import br.com.fiap.numberone.cliente.domain.entities.Cliente;
import br.com.fiap.numberone.ordemservico.domain.enums.StatusOrdemServico;
import br.com.fiap.numberone.veiculo.domain.entities.entities.Veiculo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrdemServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricaoInicial;

    private String descricaoDiagnostico;

    private String observacao;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCliente")
    private Cliente cliente;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idVeiculo")
    private Veiculo veiculo;

    @Enumerated(EnumType.STRING)
    private StatusOrdemServico status;

    private LocalDateTime dataHoraEntrada;

    private LocalDateTime dataHoraPrevista;

    private LocalDateTime dataHoraEntrega;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
