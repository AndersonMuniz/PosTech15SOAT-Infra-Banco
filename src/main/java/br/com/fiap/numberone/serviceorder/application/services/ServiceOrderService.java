package br.com.fiap.numberone.serviceorder.application.services;

import br.com.fiap.numberone.serviceorder.application.gateways.CustomerGateway;
import br.com.fiap.numberone.serviceorder.application.gateways.ServiceOrderGateway;
import br.com.fiap.numberone.serviceorder.application.gateways.ServiceOrderApprovalNotificationGateway;
import br.com.fiap.numberone.serviceorder.application.gateways.VehicleGateway;
import br.com.fiap.numberone.serviceorder.domain.enums.ServiceOrderStatus;
import br.com.fiap.numberone.serviceorder.domain.valueobjects.Diagnosis;
import br.com.fiap.numberone.serviceorder.domain.entities.ServiceOrder;
import br.com.fiap.numberone.serviceorder.domain.references.Customer;
import br.com.fiap.numberone.serviceorder.domain.references.Vehicle;
import br.com.fiap.numberone.shared.api.exception.ResourceNotFoundException;

import java.util.List;
import java.util.UUID;

public class ServiceOrderService {

    private final ServiceOrderGateway serviceOrderGateway;
    private final ServiceOrderApprovalNotificationGateway serviceOrderApprovalNotificationGateway;
    private final CustomerGateway customerGateway;
    private final VehicleGateway vehicleGateway;

    public ServiceOrderService(
            ServiceOrderGateway serviceOrderGateway,
            ServiceOrderApprovalNotificationGateway serviceOrderApprovalNotificationGateway,
            CustomerGateway customerGateway,
            VehicleGateway vehicleGateway
    ) {
        this.serviceOrderGateway = serviceOrderGateway;
        this.serviceOrderApprovalNotificationGateway = serviceOrderApprovalNotificationGateway;
        this.customerGateway = customerGateway;
        this.vehicleGateway = vehicleGateway;
    }

    public List<ServiceOrder> getServiceOrders() {
        return serviceOrderGateway.findAll();
    }

    public ServiceOrder getServiceOrder(UUID id) {
        return serviceOrderGateway.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Service order not found for id: " + id));
    }

    public ServiceOrder createServiceOrder(ServiceOrder serviceOrder) {
        Customer validatedCustomer = customerGateway.findById(serviceOrder.getCustomer().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
        Vehicle validatedVehicle = vehicleGateway.findById(serviceOrder.getVehicle().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found"));

        serviceOrder.attachCustomer(validatedCustomer);
        serviceOrder.attachVehicle(validatedVehicle);

        return serviceOrderGateway.save(serviceOrder);
    }

    public ServiceOrder addFinalDiagnosis(UUID id, Diagnosis diagnosis) {
        ServiceOrder serviceOrder = serviceOrderGateway.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Service order not found for id: " + id));

        serviceOrder.applyFinalDiagnosis(diagnosis.getFinalDiagnosisDescription(), diagnosis.getNotes());
        serviceOrder.updateStatus(ServiceOrderStatus.IN_DIAGNOSIS);

        return serviceOrderGateway.save(serviceOrder);
    }

    public ServiceOrder requestApproval(UUID id) {
        ServiceOrder serviceOrder = serviceOrderGateway.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Service order not found for id: " + id));

        serviceOrder.updateStatus(ServiceOrderStatus.WAITING_APPROVAL);

        ServiceOrder savedServiceOrder = serviceOrderGateway.save(serviceOrder);
        String recipientEmail = savedServiceOrder.getCustomer() != null ? savedServiceOrder.getCustomer().getEmail() : null;
        if (recipientEmail == null || recipientEmail.isBlank()) {
            throw new IllegalArgumentException("Customer email is required to request approval");
        }
        serviceOrderApprovalNotificationGateway.sendApprovalRequest(savedServiceOrder, recipientEmail);

        return savedServiceOrder;
    }

    public void approve(UUID id) {
        ServiceOrder serviceOrder = serviceOrderGateway.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Service order not found for id: " + id));

        serviceOrder.updateStatus(ServiceOrderStatus.APPROVED);

        serviceOrderGateway.save(serviceOrder);
    }

    public void reject(UUID id) {
        ServiceOrder serviceOrder = serviceOrderGateway.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Service order not found for id: " + id));

        serviceOrder.updateStatus(ServiceOrderStatus.REJECTED);

        serviceOrderGateway.save(serviceOrder);
    }
}
