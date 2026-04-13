package br.com.fiap.numberone.ordemservico.api.dto.responses;

import br.com.fiap.numberone.ordemservico.domain.entities.OrdemServico;
import jakarta.validation.constraints.NotNull;

public record OrdemServicoResponse(
        @NotNull(message = "Descicao inicial é obrigatoria") Long id
) {

    public static OrdemServicoResponse from(OrdemServico entity) {
        return new OrdemServicoResponse(
                entity.getId()
        );
    }

}