package br.com.fiap.numberone.ordemservico.infrastructure.repositories;

import br.com.fiap.numberone.ordemservico.domain.entities.OrdemServico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long> {

}
