package br.com.fiap.numberone.customer.infrastructure.persistence.repositories;

import br.com.fiap.numberone.customer.domain.enums.TipoDocumento;
import br.com.fiap.numberone.customer.infrastructure.persistence.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, UUID> {

    Optional<CustomerEntity> findByDocumentAndDocumentType(String document, TipoDocumento documentType);
}


