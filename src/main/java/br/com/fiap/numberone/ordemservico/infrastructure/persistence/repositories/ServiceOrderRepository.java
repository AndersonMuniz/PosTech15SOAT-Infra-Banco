package br.com.fiap.numberone.ordemservico.infrastructure.persistence.repositories;

import br.com.fiap.numberone.ordemservico.infrastructure.persistence.entities.ServiceOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ServiceOrderRepository extends JpaRepository<ServiceOrderEntity, UUID> {
}
