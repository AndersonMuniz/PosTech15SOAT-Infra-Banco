package br.com.fiap.numberone.cliente.domain.entities;

import br.com.fiap.numberone.cliente.domain.enums.TipoDocumento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 90)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_documento", nullable = false, length = 30)
    private TipoDocumento tipoDocumento;

    @Column(nullable = false, length = 50)
    private String documento;

    @Column(nullable = false, length = 15)
    private String telefone;

    @Column(nullable = false, length = 90)
    private String endereco;

    @Column(nullable = false)
    private Boolean ativo;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.ativo = this.ativo == null ? Boolean.TRUE : this.ativo;
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public Cliente updateFrom(Cliente novoCliente) {
        return Cliente.builder()
                .id(this.id)
                .nome(novoCliente.nome)
                .tipoDocumento(novoCliente.tipoDocumento)
                .documento(novoCliente.documento)
                .telefone(novoCliente.telefone)
                .endereco(novoCliente.endereco)
                .ativo(novoCliente.ativo)
                .createdAt(this.createdAt)
                .updatedAt(LocalDateTime.now())
                .build();
    }
}
