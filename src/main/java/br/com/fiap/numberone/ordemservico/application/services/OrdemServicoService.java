package br.com.fiap.numberone.ordemservico.application.services;

import br.com.fiap.numberone.ordemservico.api.dto.requests.CreateOrdemServicoRequest;
import br.com.fiap.numberone.ordemservico.api.dto.responses.OrdemServicoResponse;
import br.com.fiap.numberone.ordemservico.domain.entities.OrdemServico;
import br.com.fiap.numberone.ordemservico.infrastructure.repositories.OrdemServicoRepository;
import br.com.fiap.numberone.shared.api.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class OrdemServicoService {

    private final OrdemServicoRepository ordemServicoRepository;

    public OrdemServicoService(OrdemServicoRepository ordemServicoRepository) {
        this.ordemServicoRepository = ordemServicoRepository;
    }


    public OrdemServicoResponse getOrdemServico(Long id) {
        return ordemServicoRepository
                .findById(id)
                .map(OrdemServicoResponse::from)
                .orElseThrow(() -> new ResourceNotFoundException("Ordem de Serviço não encontrada com id: " + id));
    }

    public OrdemServicoResponse createNewOrdemServico(CreateOrdemServicoRequest createOrdemServicoRequest) {
        OrdemServico entity = createOrdemServicoRequest.toEntity();
        OrdemServico saved = ordemServicoRepository.save(entity);
        return OrdemServicoResponse.from(saved);
    }

}
