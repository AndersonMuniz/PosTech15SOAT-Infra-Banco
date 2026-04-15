package br.com.fiap.numberone.estoque.api.dto.responses;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServicoResponse {
    private UUID id;
    private String codigo;
    private String descricao;
    private BigDecimal valorBase;
    private Integer tempoEstimadoMinuto;
    private Boolean ativo;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
