package br.com.fiap.numberone.ordemservico.api.controllers;

import br.com.fiap.numberone.ordemservico.api.dtos.requests.CreateServiceOrderRequest;
import br.com.fiap.numberone.ordemservico.api.dtos.requests.FinalDiagnosisRequest;
import br.com.fiap.numberone.ordemservico.api.dtos.requests.LinkServicesRequest;
import br.com.fiap.numberone.ordemservico.api.dtos.responses.ServiceOrderResponse;
import br.com.fiap.numberone.ordemservico.api.mappers.ServiceOrderApiMapper;
import br.com.fiap.numberone.ordemservico.application.services.ServiceOrderItemService;
import br.com.fiap.numberone.ordemservico.application.services.ServiceOrderService;
import br.com.fiap.numberone.ordemservico.domain.entities.ServiceOrder;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/service-orders")
public class ServiceOrderController {

    private final ServiceOrderApiMapper mapper;
    private final ServiceOrderService serviceOrderService;
    private final ServiceOrderItemService serviceOrderItemService;

    public ServiceOrderController(
            ServiceOrderApiMapper mapper,
            ServiceOrderService serviceOrderService,
            ServiceOrderItemService serviceOrderItemService
    ) {
        this.mapper = mapper;
        this.serviceOrderService = serviceOrderService;
        this.serviceOrderItemService = serviceOrderItemService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceOrderResponse> getServiceOrder(@PathVariable UUID id) {
        return ResponseEntity.ok(mapper.toResponse(serviceOrderService.getServiceOrder(id)));
    }

    @GetMapping
    public ResponseEntity<List<ServiceOrderResponse>> getServiceOrders() {
        return ResponseEntity.ok(serviceOrderService.getServiceOrders()
                .stream()
                .map(mapper::toResponse)
                .toList());
    }

    @PostMapping
    public ResponseEntity<ServiceOrderResponse> createServiceOrder(
            @Valid @RequestBody CreateServiceOrderRequest createServiceOrderRequest
    ) {
        ServiceOrder serviceOrder = serviceOrderService.createServiceOrder(mapper.toDomain(createServiceOrderRequest));
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(serviceOrder.getId())
                .toUri();
        return ResponseEntity.created(location).body(mapper.toResponse(serviceOrder));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ServiceOrderResponse> addFinalDiagnosis(
            @PathVariable UUID id,
            @Valid @RequestBody FinalDiagnosisRequest finalDiagnosisRequest
    ) {
        ServiceOrder serviceOrder = serviceOrderService.addFinalDiagnosis(id, mapper.toDomain(finalDiagnosisRequest));
        return ResponseEntity.ok(mapper.toResponse(serviceOrder));
    }

    @PostMapping("/{id}/services")
    public ResponseEntity<ServiceOrderResponse> addServices(
            @PathVariable UUID id,
            @Valid @RequestBody LinkServicesRequest linkServicesRequest
    ) {
        ServiceOrderResponse serviceOrderResponse = serviceOrderItemService.addServices(id, linkServicesRequest);
        return ResponseEntity.ok(serviceOrderResponse);
    }
}
