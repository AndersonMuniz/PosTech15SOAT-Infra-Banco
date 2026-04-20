package br.com.fiap.numberone.inventory.infrastructure.persistence.repositories;

import br.com.fiap.numberone.inventory.infrastructure.persistence.entities.AutomotiveServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AutoServiceRepository extends JpaRepository<AutomotiveServiceEntity, UUID> {
}