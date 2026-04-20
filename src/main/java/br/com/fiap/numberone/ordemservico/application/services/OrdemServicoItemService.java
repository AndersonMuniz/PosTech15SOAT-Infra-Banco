package br.com.fiap.numberone.ordemservico.application.services;

import br.com.fiap.numberone.ordemservico.api.mappers.OrdemServicoApiMapper;
import br.com.fiap.numberone.ordemservico.infrastructure.persistence.repositories.OrdemServicoRepository;
import org.springframework.stereotype.Service;


@Service
public class OrdemServicoItemService {

    private final OrdemServicoRepository ordemRepository;
//    private final ServicoRepository servicoRepository;
    private final OrdemServicoApiMapper mapper;

    public OrdemServicoItemService(OrdemServicoRepository ordemRepository,
//                                   ServicoRepository servicoRepository,
                                   OrdemServicoApiMapper mapper) {
        this.ordemRepository = ordemRepository;
//        this.servicoRepository = servicoRepository;
        this.mapper = mapper;
    }

//    @Transactional
//    public OrdemServicoResponse vincularItens(Long ordemId, List<Long> servicosIds) {
//
//    }
}
