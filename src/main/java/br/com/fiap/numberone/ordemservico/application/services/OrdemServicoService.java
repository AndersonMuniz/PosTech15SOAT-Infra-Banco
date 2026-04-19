package br.com.fiap.numberone.ordemservico.application.services;

import br.com.fiap.numberone.cliente.domain.entities.Cliente;
import br.com.fiap.numberone.cliente.infrastructure.persistence.entities.ClienteEntity;
import br.com.fiap.numberone.cliente.infrastructure.repositories.ClienteRepository;
import br.com.fiap.numberone.ordemservico.api.dtos.requests.CriarOrdemServicoRequest;
import br.com.fiap.numberone.ordemservico.api.dtos.requests.DiagnosticoFinalRequest;
import br.com.fiap.numberone.ordemservico.api.dtos.responses.OrdemServicoResponse;
import br.com.fiap.numberone.ordemservico.application.mappers.OrdemServicoMapper;
import br.com.fiap.numberone.ordemservico.domain.entities.OrdemServico;
import br.com.fiap.numberone.ordemservico.infrastructure.repositories.OrdemServicoRepository;
import br.com.fiap.numberone.shared.api.exception.ResourceNotFoundException;
import br.com.fiap.numberone.veiculo.domain.entities.Veiculo;
import br.com.fiap.numberone.veiculo.infrastructure.repositories.VeiculoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Transactional(readOnly = true)
    public List<OrdemServicoResponse> buscarOrdensServico() {
        return ordemServicoRepository.findAll()
                .stream()
                .map((ordemServicoMapper::toResponse))
                .toList();
    }

    @Transactional(readOnly = true)
    public OrdemServicoResponse buscarOrdemServico(Long id) {
        return ordemServicoRepository.findById(id)
                .map((ordemServicoMapper::toResponse))
                .orElseThrow(() -> new ResourceNotFoundException("Ordem de Serviço não encontrada com id: " + id));
    }

<<<<<<< HEAD
    @Transactional
    public OrdemServicoResponse criarOrdemServico(CriarOrdemServicoRequest criarOrdemServicoRequest) {
        Cliente cliente = clienteRepository.findById(criarOrdemServicoRequest.idCliente())
=======
    public OrdemServicoResponse createOrdemServico(CreateOrdemServicoRequest createOrdemServicoRequest) {
        ClienteEntity cliente = clienteRepository.findById(createOrdemServicoRequest.idCliente())
>>>>>>> e00dbb4a69bf7950652d44304205f31ab0f1bea5
                .orElseThrow(() -> new ResourceNotFoundException("Cliente nao encontrado"));
        Veiculo veiculo = veiculoRepository.findById(criarOrdemServicoRequest.idVeiculo())
                .orElseThrow(() -> new ResourceNotFoundException("Veiculo nao encontrado"));

        OrdemServico entity = ordemServicoMapper.toEntity(criarOrdemServicoRequest, cliente, veiculo);

        OrdemServico saved = ordemServicoRepository.save(entity);

        //TODO: Verificar como notificar cliente

        return ordemServicoMapper.toResponse(saved);
    }

    @Transactional
    public OrdemServicoResponse adicionarDiagnosticoFinal(Long id, DiagnosticoFinalRequest diagnosticoFinalRequest) {
        OrdemServico entity = ordemServicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ordem de serviço nao encontrada"));

        entity.aplicarDiagnosticoFinal(diagnosticoFinalRequest.descricaoDiagnosticoFinal(), diagnosticoFinalRequest.observacao());

        OrdemServico saved = ordemServicoRepository.save(entity);

        return ordemServicoMapper.toResponse(saved);
    }

}
