package br.com.fiap.numberone.serviceorder.api.controllers;

import br.com.fiap.numberone.serviceorder.api.dtos.responses.ServiceOrderTrackingResponse;
import br.com.fiap.numberone.serviceorder.api.mappers.ServiceOrderApiMapper;
import br.com.fiap.numberone.serviceorder.application.services.ServiceOrderTrackingService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/public/service-orders")
public class ServiceOrderTrackingController {

    private final ServiceOrderApiMapper serviceOrderApiMapper;
    private final ServiceOrderTrackingService serviceOrderTrackingService;

    public ServiceOrderTrackingController(
            ServiceOrderApiMapper serviceOrderApiMapper,
            ServiceOrderTrackingService serviceOrderTrackingService
    ) {
        this.serviceOrderApiMapper = serviceOrderApiMapper;
        this.serviceOrderTrackingService = serviceOrderTrackingService;
    }

    @GetMapping("/{id}/tracking")
    public ResponseEntity<ServiceOrderTrackingResponse> getServiceOrderTracking(@PathVariable UUID id) {
        return ResponseEntity.ok(
                serviceOrderApiMapper.toTrackingResponse(serviceOrderTrackingService.getTracking(id))
        );
    }

}
