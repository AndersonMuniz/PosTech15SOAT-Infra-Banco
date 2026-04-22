package br.com.fiap.numberone.serviceorder.domain.entities;

import br.com.fiap.numberone.serviceorder.domain.enums.OrderItemStatus;
import br.com.fiap.numberone.serviceorder.domain.enums.ServiceOrderBudgetStatus;
import br.com.fiap.numberone.serviceorder.domain.enums.ServiceOrderStatus;
import br.com.fiap.numberone.serviceorder.domain.exceptions.AutomotiveServiceNotActiveException;
import br.com.fiap.numberone.serviceorder.domain.exceptions.InvalidServiceOrderStatusException;
import br.com.fiap.numberone.serviceorder.domain.references.AutomotiveService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceOrderBudget {

    private UUID id;
    private ServiceOrder serviceOrder;
    private BigDecimal quotedAmount;
    private BigDecimal approvedAmount;
    private ServiceOrderBudgetStatus status;
    private LocalDateTime sentAt;
    private LocalDateTime approvedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public void attachServiceOrder(ServiceOrder serviceOrder) {
        if (ServiceOrderStatus.IN_DIAGNOSIS != serviceOrder.getStatus()) {
            throw new InvalidServiceOrderStatusException("Service order status does not allow creating new budget: " + serviceOrder.getStatus());
        }
        this.serviceOrder = serviceOrder;
    }

    public void defineQuotedAmount(BigDecimal quotedAmount) {
        this.quotedAmount = quotedAmount;
    }

    public void markAsSent() {
        this.status = ServiceOrderBudgetStatus.SENT;
        this.sentAt = LocalDateTime.now();
    }

    public void approve() {
        this.status = ServiceOrderBudgetStatus.APPROVED;
        this.approvedAmount = this.quotedAmount;
        this.approvedAt = LocalDateTime.now();
    }

    public void reject() {
        this.status = ServiceOrderBudgetStatus.REJECTED;
    }
}
