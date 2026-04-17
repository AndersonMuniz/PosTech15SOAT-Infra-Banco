package br.com.fiap.numberone.estoque.infrastructure.persistence.repositories;

import br.com.fiap.numberone.estoque.infrastructure.persistence.entities.ServicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ServicoRepository extends JpaRepository<ServicoEntity, UUID> {
}