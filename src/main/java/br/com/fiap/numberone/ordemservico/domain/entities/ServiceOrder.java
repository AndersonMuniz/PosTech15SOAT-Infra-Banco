package br.com.fiap.numberone.ordemservico.domain.entities;

import br.com.fiap.numberone.ordemservico.domain.enums.ServiceOrderStatus;
import br.com.fiap.numberone.ordemservico.domain.exceptions.CustomerNotActiveException;
import br.com.fiap.numberone.ordemservico.domain.valueobjects.Customer;
import br.com.fiap.numberone.ordemservico.domain.valueobjects.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceOrder {

    private UUID id;
    private String initialDescription;
    private String diagnosisDescription;
    private String finalDiagnosisDescription;
    private String notes;
    private Customer customer;
    private Vehicle vehicle;
    private ServiceOrderStatus status;
    private LocalDateTime entryDateTime;
    private LocalDateTime expectedDateTime;
    private LocalDateTime deliveryDateTime;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public void attachCustomer(Customer customer) {
        if(!customer.getActive()){
            throw new CustomerNotActiveException("Customer is not active to be attached");
        }
        this.customer = customer;
    }

    public void attachVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void applyFinalDiagnosis(String finalDiagnosisDescription, String notes) {
        this.finalDiagnosisDescription = finalDiagnosisDescription;
        this.notes = notes;
    }
}
