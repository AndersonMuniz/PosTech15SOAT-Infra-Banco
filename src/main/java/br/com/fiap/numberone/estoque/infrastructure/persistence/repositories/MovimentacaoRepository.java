package br.com.fiap.numberone.estoque.infrastructure.persistence.repositories;

import br.com.fiap.numberone.estoque.infrastructure.persistence.entities.MovimentacaoEstoqueEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MovimentacaoRepository extends JpaRepository<MovimentacaoEstoqueEntity, UUID> {

    List<MovimentacaoEstoqueEntity> findByItemId(UUID itemId);
}
