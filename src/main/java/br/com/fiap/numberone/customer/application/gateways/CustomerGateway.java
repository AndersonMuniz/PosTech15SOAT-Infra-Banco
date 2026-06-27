package br.com.fiap.numberone.customer.application.gateways;

import br.com.fiap.numberone.customer.domain.entities.Customer;
import br.com.fiap.numberone.customer.domain.enums.TipoDocumento;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerGateway {

    Customer save(Customer customer);

    Optional<Customer> findById(UUID id);

    Optional<Customer> findByDocumentAndDocumentType(String document, TipoDocumento documentType);

    List<Customer> findAll();

    void delete(Customer customer);

    boolean existsById(UUID id);
}


