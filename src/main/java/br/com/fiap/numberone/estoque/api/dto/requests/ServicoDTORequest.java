package br.com.fiap.numberone.estoque.api.dto.requests;

import java.math.BigDecimal;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServicoDTORequest {

    @NotBlank(message = "Código é obrigatório")
    private String codigo;

    @NotBlank(message = "Descrição é obrigatória")
    private String descricao;

    @NotNull(message = "Valor base é obrigatório")
    @DecimalMin(value = "1", inclusive = false, message = "Valor base deve ser maior que zero")
    private BigDecimal valorBase;

    @Min(value = 1, message = "Tempo estimado não pode ser negativo")
    private Integer tempoEstimadoMinuto;
}
