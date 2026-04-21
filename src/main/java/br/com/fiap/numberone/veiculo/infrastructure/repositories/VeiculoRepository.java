package br.com.fiap.numberone.veiculo.infrastructure.repositories;

import br.com.fiap.numberone.veiculo.domain.entities.VeiculoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface VeiculoRepository extends JpaRepository<VeiculoEntity, UUID> {

}
