package br.com.fiap.numberone.ordemservico.application.services;

import br.com.fiap.numberone.ordemservico.application.mappers.OrdemServicoMapper;
import br.com.fiap.numberone.ordemservico.infrastructure.repositories.OrdemServicoRepository;
import org.springframework.stereotype.Service;


@Service
public class OrdemServicoItemService {

    private final OrdemServicoRepository ordemRepository;
//    private final ServicoRepository servicoRepository;
    private final OrdemServicoMapper mapper;

    public OrdemServicoItemService(OrdemServicoRepository ordemRepository,
//                                   ServicoRepository servicoRepository,
                                   OrdemServicoMapper mapper) {
        this.ordemRepository = ordemRepository;
//        this.servicoRepository = servicoRepository;
        this.mapper = mapper;
    }

//    @Transactional
//    public OrdemServicoResponse vincularItens(Long ordemId, List<Long> servicosIds) {
//
//    }
}
