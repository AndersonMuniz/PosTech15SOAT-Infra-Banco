package br.com.fiap.numberone.serviceorder.application.services;

import br.com.fiap.numberone.serviceorder.application.gateways.ServiceOrderBudgetApprovalNotificationGateway;
import br.com.fiap.numberone.serviceorder.application.gateways.ServiceOrderBudgetGateway;
import br.com.fiap.numberone.serviceorder.application.gateways.ServiceOrderGateway;
import br.com.fiap.numberone.serviceorder.domain.entities.ServiceOrder;
import br.com.fiap.numberone.serviceorder.domain.entities.ServiceOrderBudget;
import br.com.fiap.numberone.serviceorder.domain.entities.ServiceOrderItem;
import br.com.fiap.numberone.serviceorder.domain.enums.ServiceOrderStatus;
import br.com.fiap.numberone.serviceorder.domain.enums.OrderItemStatus;
import br.com.fiap.numberone.shared.api.exception.ResourceNotFoundException;
import org.jspecify.annotations.NonNull;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class ServiceOrderBudgetService {

    private final ServiceOrderGateway serviceOrderGateway;
    private final ServiceOrderBudgetGateway serviceOrderBudgetGateway;
    private final ServiceOrderBudgetApprovalNotificationGateway serviceOrderBudgetApprovalNotificationGateway;

    public ServiceOrderBudgetService(
            ServiceOrderGateway serviceOrderGateway,
            ServiceOrderBudgetGateway serviceOrderBudgetGateway,
            ServiceOrderBudgetApprovalNotificationGateway serviceOrderBudgetApprovalNotificationGateway
    ) {
        this.serviceOrderGateway = serviceOrderGateway;
        this.serviceOrderBudgetGateway = serviceOrderBudgetGateway;
        this.serviceOrderBudgetApprovalNotificationGateway = serviceOrderBudgetApprovalNotificationGateway;
    }

    public ServiceOrderBudget createDraftBudget(ServiceOrderBudget serviceOrderBudget) {
        ServiceOrder serviceOrder = serviceOrderGateway.findById(serviceOrderBudget.getServiceOrder().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Service order not found for id: " + serviceOrderBudget.getServiceOrder().getId()));

        serviceOrderBudget.attachServiceOrder(serviceOrder);
        serviceOrderBudget.defineQuotedAmount(resolveQuotedAmount(serviceOrderBudget.getQuotedAmount(), serviceOrder));

        return serviceOrderBudgetGateway.save(serviceOrderBudget);
    }

    public ServiceOrderBudget requestApproval(ServiceOrderBudget serviceOrderBudget) {
        ServiceOrder serviceOrder = serviceOrderGateway.findById(serviceOrderBudget.getServiceOrder().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Service order not found for id: " + serviceOrderBudget.getServiceOrder().getId()));

        serviceOrderBudget.attachServiceOrder(serviceOrder);
        serviceOrderBudget.defineQuotedAmount(resolveQuotedAmount(serviceOrderBudget.getQuotedAmount(), serviceOrder));
        serviceOrderBudget.markAsSent();

        serviceOrder.updateStatus(ServiceOrderStatus.WAITING_APPROVAL);
        ServiceOrder savedServiceOrder = serviceOrderGateway.save(serviceOrder);
        ServiceOrderBudget savedBudget = serviceOrderBudgetGateway.save(serviceOrderBudget);

        String recipientEmail = savedServiceOrder.getCustomer() != null ? savedServiceOrder.getCustomer().getEmail() : null;
        if (recipientEmail == null || recipientEmail.isBlank()) {
            throw new IllegalArgumentException("Customer email is required to request budget approval");
        }
        serviceOrderBudgetApprovalNotificationGateway.sendApprovalRequest(savedBudget, recipientEmail);

        return savedBudget;
    }

    public void approve(UUID id) {
        ServiceOrderBudget serviceOrderBudget = getServiceOrderBudget(id);

        serviceOrderBudget.approve();
        serviceOrderBudgetGateway.save(serviceOrderBudget);

        updateServiceOrderStatus(serviceOrderBudget, ServiceOrderStatus.APPROVED);
    }

    public void reject(UUID id) {
        ServiceOrderBudget serviceOrderBudget = getServiceOrderBudget(id);

        serviceOrderBudget.reject();
        serviceOrderBudgetGateway.save(serviceOrderBudget);

        updateServiceOrderStatus(serviceOrderBudget, ServiceOrderStatus.REJECTED);
    }

    private void updateServiceOrderStatus(ServiceOrderBudget serviceOrderBudget, ServiceOrderStatus approved) {
        ServiceOrder serviceOrder = serviceOrderGateway.findById(serviceOrderBudget.getServiceOrder().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Service order not found for id: " + serviceOrderBudget.getServiceOrder().getId()));
        serviceOrder.updateStatus(approved);
        serviceOrderGateway.save(serviceOrder);
    }

    private ServiceOrderBudget getServiceOrderBudget(UUID id) {
        return serviceOrderBudgetGateway.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Service order budget not found for id: " + id));
    }

    private BigDecimal resolveQuotedAmount(BigDecimal quotedAmount, ServiceOrder serviceOrder) {
        if (quotedAmount != null) {
            return quotedAmount;
        }
        return serviceOrder.getServiceItems()
                .stream()
                .filter(serviceOrderItem -> serviceOrderItem.getStatus() != OrderItemStatus.CANCELLED)
                .map(ServiceOrderItem::getValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
