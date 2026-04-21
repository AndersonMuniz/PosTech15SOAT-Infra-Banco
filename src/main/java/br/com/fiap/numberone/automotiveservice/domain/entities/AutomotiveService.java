package br.com.fiap.numberone.automotiveservice.domain.entities;

import br.com.fiap.numberone.automotiveservice.domain.enums.ServiceType;
import br.com.fiap.numberone.automotiveservice.domain.exceptions.AutomotiveServiceBusinessException;

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

    public AutomotiveService() {
    }

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
        this.active = active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public BigDecimal getBaseValue() {
        return baseValue;
    }

    public void setBaseValue(BigDecimal baseValue) {
        this.baseValue = baseValue;
    }

    public Integer getEstimatedTimeMinutes() {
        return estimatedTimeMinutes;
    }

    public void setEstimatedTimeMinutes(Integer estimatedTimeMinutes) {
        this.estimatedTimeMinutes = estimatedTimeMinutes;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
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

    public void updateFrom(AutomotiveService newData) {
        if (newData.name != null) {
            this.name = newData.name;
        }
        if (newData.description != null) {
            this.description = newData.description;
        }
        if (newData.serviceType != null) {
            this.serviceType = newData.serviceType;
        }
        if (newData.baseValue != null) {
            this.baseValue = newData.baseValue;
        }
        if (newData.estimatedTimeMinutes != null) {
            this.estimatedTimeMinutes = newData.estimatedTimeMinutes;
        }
        if (newData.active != null) {
            this.active = newData.active;
        }

        this.updatedAt = LocalDateTime.now();
    }

    public void deactivate() {
        if (Boolean.FALSE.equals(this.active)) {
            throw new AutomotiveServiceBusinessException("O serviço automotivo já está inativo");
        }

        this.active = false;
        this.updatedAt = LocalDateTime.now();
    }
}