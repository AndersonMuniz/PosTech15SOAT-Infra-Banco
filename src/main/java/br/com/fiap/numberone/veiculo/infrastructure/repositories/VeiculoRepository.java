package br.com.fiap.numberone.veiculo.infrastructure.repositories;

import br.com.fiap.numberone.veiculo.domain.entities.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

}
