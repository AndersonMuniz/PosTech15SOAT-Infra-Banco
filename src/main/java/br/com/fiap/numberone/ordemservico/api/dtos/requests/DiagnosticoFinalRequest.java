package br.com.fiap.numberone.ordemservico.api.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DiagnosticoFinalRequest(
        @NotBlank(message = "Descicao diagnostico final é obrigatorio") String descricaoDiagnosticoFinal,
        String observacao
) { }