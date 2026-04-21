package br.com.fiap.numberone.client.infrastructure.repositories;

import br.com.fiap.numberone.client.infrastructure.persistence.entities.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, UUID> {
}
