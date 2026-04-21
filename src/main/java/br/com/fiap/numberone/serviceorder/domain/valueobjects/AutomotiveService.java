package br.com.fiap.numberone.serviceorder.domain.valueobjects;

import br.com.fiap.numberone.inventory.domain.enums.ServiceType;

import java.math.BigDecimal;
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
}
