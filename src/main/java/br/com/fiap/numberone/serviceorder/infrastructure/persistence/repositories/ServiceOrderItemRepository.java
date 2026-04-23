package br.com.fiap.numberone.serviceorder.infrastructure.persistence.repositories;

import br.com.fiap.numberone.serviceorder.infrastructure.persistence.entities.ServiceOrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ServiceOrderItemRepository extends JpaRepository<ServiceOrderItemEntity, UUID> {
}
