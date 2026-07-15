package br.com.fiap.numberone.serviceorder.api.controllers;

import br.com.fiap.numberone.serviceorder.api.dtos.responses.ServiceOrderTrackingResponse;
import br.com.fiap.numberone.serviceorder.api.dtos.responses.ServiceOrderStatusConsultResponse;
import br.com.fiap.numberone.serviceorder.api.mappers.ServiceOrderStatusApiMapper;
import br.com.fiap.numberone.serviceorder.api.mappers.ServiceOrderTrackingApiMapper;
import br.com.fiap.numberone.serviceorder.application.services.ServiceOrderTrackingService;
import br.com.fiap.numberone.serviceorder.domain.entities.ServiceOrder;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/public/ordens-servico")
public class ServiceOrderTrackingController {

    private final ServiceOrderTrackingApiMapper serviceOrderTrackingApiMapper;
    private final ServiceOrderStatusApiMapper serviceOrderStatusApiMapper;
    private final ServiceOrderTrackingService serviceOrderTrackingService;

    public ServiceOrderTrackingController(
            ServiceOrderTrackingApiMapper serviceOrderTrackingApiMapper,
            ServiceOrderStatusApiMapper serviceOrderStatusApiMapper,
            ServiceOrderTrackingService serviceOrderTrackingService
    ) {
        this.serviceOrderTrackingApiMapper = serviceOrderTrackingApiMapper;
        this.serviceOrderStatusApiMapper = serviceOrderStatusApiMapper;
        this.serviceOrderTrackingService = serviceOrderTrackingService;
    }

    @GetMapping("/{id}/acompanhamento")
    public ResponseEntity<ServiceOrderTrackingResponse> getServiceOrderTracking(@PathVariable UUID id) {
        return ResponseEntity.ok(
                serviceOrderTrackingApiMapper.toResponse(serviceOrderTrackingService.getTracking(id))
        );
    }

    @GetMapping("/{id}/status")
    public ResponseEntity<ServiceOrderStatusConsultResponse> getServiceOrderStatus(@PathVariable UUID id) {
        ServiceOrder serviceOrder = serviceOrderTrackingService.getTracking(id);
        return ResponseEntity.ok(
                new ServiceOrderStatusConsultResponse(
                        serviceOrder.getId(),
                        serviceOrderStatusApiMapper.toResponse(serviceOrder.getStatus())
                )
        );
    }

}
