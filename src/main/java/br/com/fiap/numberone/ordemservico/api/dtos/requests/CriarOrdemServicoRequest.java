package br.com.fiap.numberone.ordemservico.api.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CriarOrdemServicoRequest(
        @NotBlank(message = "Descicao inicial é obrigatoria") String descricaoInicial,
        @NotBlank(message = "Descicao diagnostico é obrigatorio") String descricaoDiagnostico,
        String observacao,
        @NotNull(message = "Cliente é obrigatorio") Long idCliente,
        @NotNull(message = "Veiculo é obrigatorio") Long idVeiculo,
        @NotNull(message = "Data hora entrada é obrigatorio") LocalDateTime dataHoraEntrada
) { }