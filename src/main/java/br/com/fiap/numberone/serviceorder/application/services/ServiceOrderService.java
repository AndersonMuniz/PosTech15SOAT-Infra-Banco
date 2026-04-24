package br.com.fiap.numberone.serviceorder.application.services;

import br.com.fiap.numberone.serviceorder.application.gateways.CustomerGateway;
import br.com.fiap.numberone.serviceorder.application.gateways.ServiceOrderGateway;
import br.com.fiap.numberone.serviceorder.application.gateways.VehicleGateway;
import br.com.fiap.numberone.serviceorder.domain.enums.ServiceOrderStatus;
import br.com.fiap.numberone.serviceorder.domain.valueobjects.Diagnosis;
import br.com.fiap.numberone.serviceorder.domain.entities.ServiceOrder;
import br.com.fiap.numberone.serviceorder.domain.references.Customer;
import br.com.fiap.numberone.serviceorder.domain.references.Vehicle;
import br.com.fiap.numberone.serviceorder.domain.valueobjects.ServiceOrderValue;
import br.com.fiap.numberone.shared.api.exception.ResourceNotFoundException;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class ServiceOrderService {

    private final ServiceOrderGateway serviceOrderGateway;
    private final CustomerGateway customerGateway;
    private final VehicleGateway vehicleGateway;

    public ServiceOrderService(
            ServiceOrderGateway serviceOrderGateway,
            CustomerGateway customerGateway,
            VehicleGateway vehicleGateway
    ) {
        this.serviceOrderGateway = serviceOrderGateway;
        this.customerGateway = customerGateway;
        this.vehicleGateway = vehicleGateway;
    }

    public List<ServiceOrder> getServiceOrders() {
        return serviceOrderGateway.findAll();
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
        ServiceOrder serviceOrder = getServiceOrder(id);

        serviceOrder.applyFinalDiagnosis(diagnosis.getFinalDiagnosisDescription(), diagnosis.getNotes());
        return changeOrderStatus(serviceOrder, ServiceOrderStatus.IN_DIAGNOSIS);
    }

    public ServiceOrder startOrderService(UUID id) {
        ServiceOrder serviceOrder = getServiceOrder(id);

        return changeOrderStatus(serviceOrder, ServiceOrderStatus.IN_PROGRESS);
    }

    public ServiceOrder cancelOrderService(UUID id) {
        ServiceOrder serviceOrder = getServiceOrder(id);

        return changeOrderStatus(serviceOrder, ServiceOrderStatus.CANCELLED);
    }

    public ServiceOrder completeOrderService(UUID id) {
        ServiceOrder serviceOrder = getServiceOrder(id);

        serviceOrder.validateServiceItemsAreFinished();
        return changeOrderStatus(serviceOrder, ServiceOrderStatus.COMPLETED);
    }


    public ServiceOrder deliverOrderService(UUID id) {
        ServiceOrder serviceOrder = getServiceOrder(id);

        if (serviceOrder.getStatus() == ServiceOrderStatus.COMPLETED) {
            serviceOrder.validateServiceItemsAreFinished();
        }

        return changeOrderStatus(serviceOrder, ServiceOrderStatus.DELIVERED);
    }

    public ServiceOrderValue calculateServices(UUID id) {
        ServiceOrder serviceOrder = getServiceOrder(id);

        BigDecimal totalValue = serviceOrder.getServiceItemsTotalValue();

        return ServiceOrderValue.builder()
                .serviceOrderId(id)
                .totalValue(totalValue)
                .build();
    }

    public ServiceOrder getServiceOrder(UUID id) {
        return serviceOrderGateway.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Service order not found for id: " + id));
    }

    private ServiceOrder changeOrderStatus(ServiceOrder serviceOrder, ServiceOrderStatus targetStatus) {
        serviceOrder.updateStatus(targetStatus);
        return serviceOrderGateway.save(serviceOrder);
    }

}
