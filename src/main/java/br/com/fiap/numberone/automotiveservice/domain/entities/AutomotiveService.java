package br.com.fiap.numberone.automotiveservice.domain.entities;

import br.com.fiap.numberone.automotiveservice.domain.enums.ServiceType;
import br.com.fiap.numberone.automotiveservice.domain.exceptions.AutomotiveServiceAlreadyActiveException;
import br.com.fiap.numberone.automotiveservice.domain.exceptions.AutomotiveServiceAlreadyInactiveException;
import br.com.fiap.numberone.automotiveservice.domain.exceptions.InvalidAutomotiveServiceDataException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class AutomotiveService {

    private UUID id;
    private String code;
    private String name;
    private String description;
    private ServiceType serviceType;
    private BigDecimal baseValue;
    private Integer estimatedTimeMinutes;
    private Boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public AutomotiveService(
            UUID id,
            String code,
            String name,
            String description,
            ServiceType serviceType,
            BigDecimal baseValue,
            Integer estimatedTimeMinutes,
            Boolean active,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.description = description;
        this.serviceType = serviceType;
        this.baseValue = baseValue;
        this.estimatedTimeMinutes = estimatedTimeMinutes;
        this.active = active != null ? active : true;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;

        validate();
    }

    public void update(
            String code,
            String name,
            String description,
            ServiceType serviceType,
            BigDecimal baseValue,
            Integer estimatedTimeMinutes
    ) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.serviceType = serviceType;
        this.baseValue = baseValue;
        this.estimatedTimeMinutes = estimatedTimeMinutes;
        this.updatedAt = LocalDateTime.now();

        validate();
    }

    public void deactivate() {
        if (Boolean.FALSE.equals(this.active)) {
            throw new AutomotiveServiceAlreadyInactiveException("O serviço automotivo já está inativo");
        }

        this.active = false;
        this.updatedAt = LocalDateTime.now();
    }

    public void activate() {
        if (Boolean.TRUE.equals(this.active)) {
            throw new AutomotiveServiceAlreadyActiveException("O serviço automotivo já está ativo");
        }

        this.active = true;
        this.updatedAt = LocalDateTime.now();
    }

    private void validate() {
        if (code == null || code.isBlank()) {
            throw new InvalidAutomotiveServiceDataException("O código do serviço é obrigatório");
        }

        if (name == null || name.isBlank()) {
            throw new InvalidAutomotiveServiceDataException("O nome do serviço é obrigatório");
        }

        if (description == null || description.isBlank()) {
            throw new InvalidAutomotiveServiceDataException("A descrição do serviço é obrigatória");
        }

        if (serviceType == null) {
            throw new InvalidAutomotiveServiceDataException("O tipo do serviço é obrigatório");
        }

        if (baseValue == null) {
            throw new InvalidAutomotiveServiceDataException("O valor base do serviço é obrigatório");
        }

        if (baseValue.compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidAutomotiveServiceDataException("O valor base do serviço não pode ser negativo");
        }

        if (estimatedTimeMinutes == null) {
            throw new InvalidAutomotiveServiceDataException("O tempo estimado em minutos é obrigatório");
        }

        if (estimatedTimeMinutes <= 0) {
            throw new InvalidAutomotiveServiceDataException("O tempo estimado em minutos deve ser maior que zero");
        }
    }

    public UUID getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public BigDecimal getBaseValue() {
        return baseValue;
    }

    public Integer getEstimatedTimeMinutes() {
        return estimatedTimeMinutes;
    }

    public Boolean getActive() {
        return active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}