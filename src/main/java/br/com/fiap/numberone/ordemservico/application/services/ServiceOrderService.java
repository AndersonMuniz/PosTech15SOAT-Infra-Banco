package br.com.fiap.numberone.ordemservico.application.services;

import br.com.fiap.numberone.ordemservico.application.gateways.CustomerGateway;
import br.com.fiap.numberone.ordemservico.application.gateways.ServiceOrderGateway;
import br.com.fiap.numberone.ordemservico.application.gateways.VehicleGateway;
import br.com.fiap.numberone.ordemservico.domain.entities.Diagnosis;
import br.com.fiap.numberone.ordemservico.domain.entities.ServiceOrder;
import br.com.fiap.numberone.ordemservico.domain.valueobjects.Customer;
import br.com.fiap.numberone.ordemservico.domain.valueobjects.Vehicle;
import br.com.fiap.numberone.shared.api.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
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

        return serviceOrderGateway.save(serviceOrder);
    }
}
