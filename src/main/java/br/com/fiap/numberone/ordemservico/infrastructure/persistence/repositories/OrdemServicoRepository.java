package br.com.fiap.numberone.ordemservico.infrastructure.persistence.repositories;

import br.com.fiap.numberone.ordemservico.infrastructure.persistence.entities.OrdemServicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrdemServicoRepository extends JpaRepository<OrdemServicoEntity, UUID> {

}
