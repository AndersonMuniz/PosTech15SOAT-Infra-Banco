package br.com.fiap.numberone.cliente.infrastructure.repositories;

import br.com.fiap.numberone.cliente.infrastructure.persistence.entities.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {
}
