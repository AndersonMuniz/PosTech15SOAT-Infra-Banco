package br.com.fiap.numberone.estoque.infrastructure.persistence.repositories;

import br.com.fiap.numberone.estoque.infrastructure.persistence.entities.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ItemRepository extends JpaRepository<ItemEntity, UUID> {
}
