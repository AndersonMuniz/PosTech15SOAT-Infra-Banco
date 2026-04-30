package br.com.fiap.numberone.custumer.application.services;

import br.com.fiap.numberone.custumer.application.gateways.CustomerGateway;
import br.com.fiap.numberone.custumer.domain.entities.Customer;
import br.com.fiap.numberone.custumer.domain.exceptions.CustomerNotFoundException;
import br.com.fiap.numberone.custumer.domain.validators.DocumentoValidator;

import java.util.List;
import java.util.UUID;

public class CustomerService {

    private final CustomerGateway customerGateway;

    public CustomerService(CustomerGateway customerGateway) {
        this.customerGateway = customerGateway;
    }

    public Customer create(Customer customer) {
        validateDocument(customer);
        return customerGateway.save(customer);
    }

    public Customer update(UUID id, Customer newData) {
        validateDocument(newData);

        Customer currentCustomer = findById(id);
        Customer updatedCustomer = currentCustomer.updateFrom(newData);

        return customerGateway.save(updatedCustomer);
    }

    public Customer findById(UUID id) {
        return customerGateway.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Cliente nao encontrado para o id: " + id));
    }

    public List<Customer> findAll() {
        return customerGateway.findAll();
    }

    public void delete(UUID id) {
        Customer customer = findById(id);
        customerGateway.delete(customer);
    }

    private void validateDocument(Customer customer) {
        DocumentoValidator.validar(customer.getDocumentType(), customer.getDocument());
    }
}


