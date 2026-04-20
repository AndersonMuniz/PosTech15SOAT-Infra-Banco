package br.com.fiap.numberone.ordemservico.application.services;

import br.com.fiap.numberone.ordemservico.api.dtos.requests.LinkServicesRequest;
import br.com.fiap.numberone.ordemservico.api.dtos.responses.ServiceOrderResponse;
import br.com.fiap.numberone.ordemservico.api.mappers.ServiceOrderApiMapper;
import br.com.fiap.numberone.ordemservico.infrastructure.persistence.repositories.ServiceOrderRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ServiceOrderItemService {

    private final ServiceOrderRepository serviceOrderRepository;
    private final ServiceOrderApiMapper mapper;

    public ServiceOrderItemService(
            ServiceOrderRepository serviceOrderRepository,
            ServiceOrderApiMapper mapper
    ) {
        this.serviceOrderRepository = serviceOrderRepository;
        this.mapper = mapper;
    }

    public ServiceOrderResponse addServices(UUID serviceOrderId, LinkServicesRequest linkServicesRequest) {
        throw new UnsupportedOperationException("Service linking has not been implemented yet.");
    }
}
