package br.com.fiap.numberone.ordemservico.domain.entities;

import br.com.fiap.numberone.cliente.domain.entities.Cliente;
import br.com.fiap.numberone.ordemservico.domain.enums.StatusOrdemServico;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
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
