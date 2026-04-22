package br.com.fiap.numberone.serviceorder.domain.entities;

import br.com.fiap.numberone.serviceorder.domain.exceptions.CustomerNotActiveException;
import br.com.fiap.numberone.serviceorder.domain.exceptions.InvalidServiceOrderStatusException;
import br.com.fiap.numberone.serviceorder.domain.enums.ServiceOrderStatus;
import br.com.fiap.numberone.serviceorder.domain.references.Customer;
import br.com.fiap.numberone.serviceorder.domain.references.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
    private List<ServiceOrderItem> serviceItems = new ArrayList<>();
    private List<ServiceOrderBudget> budgets = new ArrayList<>();
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

    public void updateStatus(ServiceOrderStatus serviceOrderStatus) {
        if (status == null) {
            this.status = serviceOrderStatus;
            return;
        }
        if (status == serviceOrderStatus) {
            return;
        }
        if (!isTransitionAllowed(serviceOrderStatus)) {
            throw new InvalidServiceOrderStatusException(
                    "Transition from " + status + " to " + serviceOrderStatus + " is not allowed"
            );
        }
        this.status = serviceOrderStatus;
    }

    private boolean isTransitionAllowed(ServiceOrderStatus nextStatus) {
        return switch (status) {
            case RECEIVED -> List.of(ServiceOrderStatus.IN_DIAGNOSIS, ServiceOrderStatus.CANCELED).contains(nextStatus);
            case IN_DIAGNOSIS -> List.of(ServiceOrderStatus.WAITING_APPROVAL, ServiceOrderStatus.CANCELED).contains(nextStatus);
            case WAITING_APPROVAL -> List.of(
                    ServiceOrderStatus.APPROVED,
                    ServiceOrderStatus.REJECTED,
                    ServiceOrderStatus.CANCELED
            ).contains(nextStatus);
            case APPROVED -> List.of(ServiceOrderStatus.IN_PROGRESS, ServiceOrderStatus.CANCELED).contains(nextStatus);
            case IN_PROGRESS -> List.of(ServiceOrderStatus.COMPLETED, ServiceOrderStatus.CANCELED).contains(nextStatus);
            case COMPLETED -> Objects.equals(ServiceOrderStatus.DELIVERED, nextStatus);
            case REJECTED, CANCELED, DELIVERED -> false;
        };
    }
}
