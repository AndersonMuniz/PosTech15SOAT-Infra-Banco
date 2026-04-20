package br.com.fiap.numberone.ordemservico.application.services;

import br.com.fiap.numberone.ordemservico.application.gateways.ClienteGateway;
import br.com.fiap.numberone.ordemservico.application.gateways.OrdemServicoGateway;
import br.com.fiap.numberone.ordemservico.application.gateways.VeiculoGateway;
import br.com.fiap.numberone.ordemservico.domain.entities.Diagnostico;
import br.com.fiap.numberone.ordemservico.domain.entities.OrdemServico;
import br.com.fiap.numberone.ordemservico.domain.valueobjects.Cliente;
import br.com.fiap.numberone.ordemservico.domain.valueobjects.Veiculo;
import br.com.fiap.numberone.shared.api.exception.ResourceNotFoundException;


import java.util.List;
import java.util.UUID;

public class OrdemServicoService {

    private final OrdemServicoGateway ordemServicoGateway;
    private final ClienteGateway clienteGateway;
    private final VeiculoGateway veiculoGateway;

    public OrdemServicoService(OrdemServicoGateway ordemServicoGateway, ClienteGateway clienteGateway, VeiculoGateway veiculoGateway) {
        this.ordemServicoGateway = ordemServicoGateway;
        this.clienteGateway = clienteGateway;
        this.veiculoGateway = veiculoGateway;
    }

    public List<OrdemServico> buscarOrdensServico() {
        return ordemServicoGateway.findAll();
    }

    public OrdemServico buscarOrdemServico(UUID id) {
        return ordemServicoGateway.findById(id).orElseThrow(() -> new ResourceNotFoundException("Ordem de Serviço não encontrada com id: " + id));
    }

    public OrdemServico criarOrdemServico(OrdemServico ordemServico) {
        Cliente clienteValidado = clienteGateway.findById(ordemServico.getCliente().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente nao encontrado"));
        Veiculo veiculoValidado = veiculoGateway.findById(ordemServico.getVeiculo().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Veiculo nao encontrado"));

        ordemServico.vincularCliente(clienteValidado);
        ordemServico.vincularVeiculo(veiculoValidado);

        return ordemServicoGateway.save(ordemServico);
    }

    public OrdemServico adicionarDiagnosticoFinal(UUID id, Diagnostico diagnostico) {
        OrdemServico ordemServico = ordemServicoGateway.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ordem de Serviço não encontrada com id: " + id));

        ordemServico.aplicarDiagnosticoFinal(diagnostico.getDescricaoDiagnosticoFinal(), diagnostico.getObservacao());

        return ordemServicoGateway.save(ordemServico);
    }

}
