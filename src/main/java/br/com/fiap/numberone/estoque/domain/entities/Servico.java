package br.com.fiap.numberone.estoque.domain.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Servico {
    private UUID id;
    private String codigo;
    private String descricao;
    private BigDecimal valorBase;
    private Integer tempoEstimadoMinuto;
    private Boolean ativo;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Servico() {
    }

    public Servico(UUID id, String codigo, String descricao, BigDecimal valorBase, Integer tempoEstimadoMinuto, Boolean ativo, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.codigo = codigo;
        this.descricao = descricao;
        this.valorBase = valorBase;
        this.tempoEstimadoMinuto = tempoEstimadoMinuto;
        this.ativo = ativo;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValorBase() {
        return valorBase;
    }

    public void setValorBase(BigDecimal valorBase) {
        this.valorBase = valorBase;
    }

    public Integer getTempoEstimadoMinuto() {
        return tempoEstimadoMinuto;
    }

    public void setTempoEstimadoMinuto(Integer tempoEstimadoMinuto) {
        this.tempoEstimadoMinuto = tempoEstimadoMinuto;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
