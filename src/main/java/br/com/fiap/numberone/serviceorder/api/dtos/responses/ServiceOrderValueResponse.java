package br.com.fiap.numberone.serviceorder.api.dtos.responses;

import java.math.BigDecimal;
import java.util.UUID;

public record ServiceOrderValueResponse(
        UUID serviceOrderId,
        BigDecimal totalValue
) {

}


