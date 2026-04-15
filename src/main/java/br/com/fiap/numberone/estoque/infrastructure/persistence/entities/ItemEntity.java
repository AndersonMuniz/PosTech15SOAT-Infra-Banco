package br.com.fiap.numberone.estoque.infrastructure.persistence.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "item")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemEntity {

    @Id
    @GeneratedValue
    private UUID id;

    private String nome;
    private String descricao;

    @Column(name = "valor_base")
    private BigDecimal valorBase;

    @Column(name = "tipo_item")
    private String tipoItem;

    @Column(name = "unidade_medida")
    private String unidadeMedida;

    @Column(name = "quantidade_estoque")
    private Integer quantidadeEstoque;
}