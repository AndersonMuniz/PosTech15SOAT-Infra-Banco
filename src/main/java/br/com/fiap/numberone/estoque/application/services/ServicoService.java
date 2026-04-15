package br.com.fiap.numberone.estoque.application.services;

import br.com.fiap.numberone.estoque.application.gateways.ServicoGateway;
import br.com.fiap.numberone.estoque.domain.entities.Servico;

import java.util.List;
import java.util.UUID;

public class ServicoService {

    private final ServicoGateway servicoGateway;

    public ServicoService(ServicoGateway servicoGateway) {
        this.servicoGateway = servicoGateway;
    }


    public Servico create(Servico servico) {
        return servicoGateway.create(servico);
    }

    public List<Servico> listar() {
        return servicoGateway.findAll();
    }

    public Servico buscar(UUID id) {
        return servicoGateway.findById(id)
                .orElseThrow(() -> new RuntimeException("Não encontrado"));
    }

    public void inativar(UUID id) {
        Servico s = buscar(id);
        //s.inativar();
        //servicoGateway.save(s);
    }
}
