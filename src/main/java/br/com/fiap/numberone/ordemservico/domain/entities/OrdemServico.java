package br.com.fiap.numberone.ordemservico.domain.entities;

import br.com.fiap.numberone.cliente.domain.entities.Cliente;
import br.com.fiap.numberone.ordemservico.api.exceptions.StatusOrdemServicoInvalidoException;
import br.com.fiap.numberone.ordemservico.domain.enums.StatusOrdemServico;
import br.com.fiap.numberone.veiculo.domain.entities.Veiculo;
import jakarta.persistence.*;
import lombok.*;
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

    private String descricaoDiagnosticoFinal;

    private String observacao;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_veiculo")
    private Veiculo veiculo;

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

    public void aplicarDiagnosticoFinal(String descricao, String observacao) {
        if (this.getStatus() != StatusOrdemServico.RECEBIDA) {
            throw new StatusOrdemServicoInvalidoException(
                    "Nao e possivel adicionar diagnostico final em uma ordem de servico com status: " + this.status
            );
        }

        this.descricaoDiagnosticoFinal = descricao;
        this.status = StatusOrdemServico.EM_DIAGNOSTICO;

        if (observacao != null && !observacao.isBlank()) {
            this.observacao = observacao;
        }
    }
}
