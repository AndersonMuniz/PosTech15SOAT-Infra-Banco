package br.com.fiap.numberone.ordemservico.application.services;

import br.com.fiap.numberone.ordemservico.api.dto.requests.CreateOrdemServicoRequest;
import br.com.fiap.numberone.ordemservico.api.dto.responses.OrdemServicoResponse;
import org.springframework.stereotype.Service;

@Service
public class OrdemServicoService {

    public OrdemServicoResponse getOrdemServico(Long id) {
        return null;
    }

    public OrdemServicoResponse createNewOrdemServico(CreateOrdemServicoRequest createOrdemServicoRequest) {
        return new OrdemServicoResponse(1L);
    }

}
