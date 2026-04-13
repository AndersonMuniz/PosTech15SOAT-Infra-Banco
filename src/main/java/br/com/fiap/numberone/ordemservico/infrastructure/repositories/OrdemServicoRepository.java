package br.com.fiap.numberone.ordemservico.infrastructure.repositories;

import br.com.fiap.numberone.ordemservico.domain.entities.OrdemServico;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long> {

    Optional<OrdemServico> findById(@NonNull Long id);

}
