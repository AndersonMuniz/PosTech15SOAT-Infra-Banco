package br.com.fiap.numberone.estoque.application.services;

import br.com.fiap.numberone.estoque.application.gateways.LoggerGateway;
import br.com.fiap.numberone.estoque.application.gateways.ServicoGateway;
import br.com.fiap.numberone.estoque.domain.entities.Servico;
import br.com.fiap.numberone.estoque.domain.exceptions.ServicoNotFountException;

import java.util.List;
import java.util.UUID;

public class ServicoService {

    private final ServicoGateway servicoGateway;
    private final LoggerGateway logger;

    public ServicoService(ServicoGateway servicoGateway, LoggerGateway loggerGateway) {
        this.servicoGateway = servicoGateway;
        this.logger = loggerGateway;
    }


    public Servico create(Servico servico) {
        return servicoGateway.save(servico);
    }

    public Servico update(UUID id, Servico servico) {
        Servico currentServico = servicoGateway.findById(id)
                .orElseThrow(() -> new ServicoNotFountException("Serviço não encontrado"));

        currentServico.updateFrom(servico);

        return servicoGateway.save(currentServico);
    }

    public List<Servico> findAll() {
        logger.info("Buscando todos os serviços");
        return servicoGateway.findAll();
    }

    public Servico findById(UUID id) {
        logger.info("Buscando servico {}", id);
        return servicoGateway.findById(id)
                .orElseThrow(() -> new ServicoNotFountException("Serviço não encontrado"));
    }

    public void inactivate(UUID id) {
        Servico servico = servicoGateway.findById(id)
                .orElseThrow(() -> new ServicoNotFountException("Serviço não encontrado"));

        servico.inactivate();

        servicoGateway.save(servico);
    }
}
