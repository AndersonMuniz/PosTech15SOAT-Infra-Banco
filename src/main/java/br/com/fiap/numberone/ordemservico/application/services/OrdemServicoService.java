package br.com.fiap.numberone.ordemservico.application.services;

import br.com.fiap.numberone.cliente.domain.entities.Cliente;
import br.com.fiap.numberone.cliente.infrastructure.repositories.ClienteRepository;
import br.com.fiap.numberone.ordemservico.api.dtos.requests.CreateOrdemServicoRequest;
import br.com.fiap.numberone.ordemservico.api.dtos.responses.OrdemServicoResponse;
import br.com.fiap.numberone.ordemservico.application.mappers.OrdemServicoMapper;
import br.com.fiap.numberone.ordemservico.domain.entities.OrdemServico;
import br.com.fiap.numberone.ordemservico.infrastructure.repositories.OrdemServicoRepository;
import br.com.fiap.numberone.shared.api.exception.ResourceNotFoundException;
import br.com.fiap.numberone.veiculo.domain.entities.Veiculo;
import br.com.fiap.numberone.veiculo.infrastructure.repositories.VeiculoRepository;
import org.springframework.stereotype.Service;

@Service
public class OrdemServicoService {

    private final OrdemServicoMapper ordemServicoMapper;

    private final OrdemServicoRepository ordemServicoRepository;
    private final ClienteRepository clienteRepository;
    private final VeiculoRepository veiculoRepository;

    public OrdemServicoService(OrdemServicoMapper ordemServicoMapper, OrdemServicoRepository ordemServicoRepository, ClienteRepository clienteRepository, VeiculoRepository veiculoRepository) {
        this.ordemServicoMapper = ordemServicoMapper;
        this.ordemServicoRepository = ordemServicoRepository;
        this.clienteRepository = clienteRepository;
        this.veiculoRepository = veiculoRepository;
    }

    public OrdemServicoResponse getOrdemServico(Long id) {
        return ordemServicoRepository.findById(id)
                .map(OrdemServicoResponse::from)
                .orElseThrow(() -> new ResourceNotFoundException("Ordem de Serviço não encontrada com id: " + id));
    }

    public OrdemServicoResponse createOrdemServico(CreateOrdemServicoRequest createOrdemServicoRequest) {
        Cliente cliente = clienteRepository.findById(createOrdemServicoRequest.idCliente())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente nao encontrado"));
        Veiculo veiculo = veiculoRepository.findById(createOrdemServicoRequest.idVeiculo())
                .orElseThrow(() -> new ResourceNotFoundException("Veiculo nao encontrado"));

        OrdemServico entity = ordemServicoMapper.toEntity(createOrdemServicoRequest, cliente, veiculo);

        OrdemServico saved = ordemServicoRepository.save(entity);
        return OrdemServicoResponse.from(saved);
    }

}
