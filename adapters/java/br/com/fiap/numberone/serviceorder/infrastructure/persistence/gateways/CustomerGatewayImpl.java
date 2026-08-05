package br.com.fiap.numberone.serviceorder.infrastructure.persistence.gateways;

import br.com.fiap.numberone.customer.application.services.CustomerService;
import br.com.fiap.numberone.customer.domain.exceptions.CustomerNotFoundException;
import br.com.fiap.numberone.serviceorder.application.gateways.CustomerGateway;
import br.com.fiap.numberone.serviceorder.domain.references.Customer;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class CustomerGatewayImpl implements CustomerGateway {

    private final CustomerService customerService;

    public CustomerGatewayImpl(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public Optional<Customer> findById(UUID id) {
        try {
            return Optional.of(toReference(customerService.findById(id)));
        } catch (CustomerNotFoundException ex) {
            return Optional.empty();
        }
    }

    @Override
    public Customer findOrCreateByDocument(Customer customer) {
        return toReference(customerService.findOrCreateByDocument(toCustomerDomain(customer)));
    }

    private br.com.fiap.numberone.customer.domain.entities.Customer toCustomerDomain(Customer customer) {
        return br.com.fiap.numberone.customer.domain.entities.Customer.builder()
                .id(customer.getId())
                .name(customer.getName())
                .documentType(customer.getDocumentType())
                .document(customer.getDocument())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .address(customer.getAddress())
                .active(customer.getActive())
                .build();
    }

    private Customer toReference(br.com.fiap.numberone.customer.domain.entities.Customer customer) {
        return Customer.builder()
                .id(customer.getId())
                .name(customer.getName())
                .documentType(customer.getDocumentType())
                .document(customer.getDocument())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .address(customer.getAddress())
                .active(customer.getActive())
                .build();
    }
}


