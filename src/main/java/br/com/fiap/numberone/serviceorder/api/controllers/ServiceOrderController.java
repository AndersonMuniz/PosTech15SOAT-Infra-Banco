package br.com.fiap.numberone.serviceorder.api.controllers;

import br.com.fiap.numberone.serviceorder.api.dtos.requests.CreateServiceOrderRequest;
import br.com.fiap.numberone.serviceorder.api.dtos.requests.FinalDiagnosisRequest;
import br.com.fiap.numberone.serviceorder.api.dtos.responses.ServiceOrderResponse;
import br.com.fiap.numberone.serviceorder.api.dtos.responses.ServiceOrderValueResponse;
import br.com.fiap.numberone.serviceorder.api.mappers.ServiceOrderApiMapper;
import br.com.fiap.numberone.serviceorder.application.services.ServiceOrderService;
import br.com.fiap.numberone.serviceorder.domain.entities.ServiceOrder;
import br.com.fiap.numberone.serviceorder.domain.valueobjects.ServiceOrderValue;
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

    private final ServiceOrderApiMapper orderApiMapper;
    private final ServiceOrderService serviceOrderService;

    public ServiceOrderController(
            ServiceOrderApiMapper orderApiMapper,
            ServiceOrderService serviceOrderService
    ) {
        this.orderApiMapper = orderApiMapper;
        this.serviceOrderService = serviceOrderService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceOrderResponse> getServiceOrder(@PathVariable UUID id) {
        return ResponseEntity.ok(orderApiMapper.toResponse(serviceOrderService.getServiceOrder(id)));
    }

    @GetMapping
    public ResponseEntity<List<ServiceOrderResponse>> getServiceOrders() {
        return ResponseEntity.ok(serviceOrderService.getServiceOrders()
                .stream()
                .map(orderApiMapper::toResponse)
                .toList());
    }

    @PostMapping
    public ResponseEntity<ServiceOrderResponse> createServiceOrder(
            @Valid @RequestBody CreateServiceOrderRequest createServiceOrderRequest
    ) {
        ServiceOrder serviceOrder = serviceOrderService.createServiceOrder(orderApiMapper.toDomain(createServiceOrderRequest));
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(serviceOrder.getId())
                .toUri();
        return ResponseEntity.created(location).body(orderApiMapper.toResponse(serviceOrder));
    }

    @PatchMapping("/{id}/start-diagnosis")
    public ResponseEntity<ServiceOrderResponse> addFinalDiagnosis(
            @PathVariable UUID id,
            @Valid @RequestBody FinalDiagnosisRequest finalDiagnosisRequest
    ) {
        ServiceOrder serviceOrder = serviceOrderService.addFinalDiagnosis(id, orderApiMapper.toDomain(finalDiagnosisRequest));
        return ResponseEntity.ok(orderApiMapper.toResponse(serviceOrder));
    }

    @GetMapping("/{id}/calculate-services")
    public ResponseEntity<ServiceOrderValueResponse> calculateServices(@PathVariable UUID id) {
        ServiceOrderValue serviceOrderValue = serviceOrderService.calculateServices(id);
        return ResponseEntity.ok(orderApiMapper.toResponse(serviceOrderValue));
    }

    @PostMapping("/{id}/request-approval")
    public ResponseEntity<ServiceOrderResponse> requestApprovalServiceOrder(@PathVariable UUID id) {
        ServiceOrder serviceOrder = serviceOrderService.requestApproval(id);
        return ResponseEntity.ok(orderApiMapper.toResponse(serviceOrder));
    }

    @GetMapping("/{id}/approval/approve")
    public ResponseEntity<String> approveServiceOrderByEmailLink(@PathVariable UUID id) {
        serviceOrderService.approve(id);
        return ResponseEntity.ok("Service order approved successfully.");
    }

    @GetMapping("/{id}/approval/reject")
    public ResponseEntity<String> rejectServiceOrderByEmailLink(@PathVariable UUID id) {
        serviceOrderService.reject(id);
        return ResponseEntity.ok("Service order rejected successfully.");
    }

    @PatchMapping("/{id}/cancel")
    public ResponseEntity<ServiceOrderResponse> cancelServieOrder(
            @PathVariable UUID id
    ) {
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/start")
    public ResponseEntity<ServiceOrderResponse> startServieOrder(
            @PathVariable UUID id
    ) {
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/complete")
    public ResponseEntity<ServiceOrderResponse> completeServieOrder(
            @PathVariable UUID id
    ) {
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/deliver")
    public ResponseEntity<ServiceOrderResponse> deliverServieOrder(
            @PathVariable UUID id
    ) {
        return ResponseEntity.noContent().build();
    }
}
