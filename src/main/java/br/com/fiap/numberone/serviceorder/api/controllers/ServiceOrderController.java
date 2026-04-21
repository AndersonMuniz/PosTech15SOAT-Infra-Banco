package br.com.fiap.numberone.serviceorder.api.controllers;

import br.com.fiap.numberone.serviceorder.api.dtos.requests.CreateServiceOrderRequest;
import br.com.fiap.numberone.serviceorder.api.dtos.requests.FinalDiagnosisRequest;
import br.com.fiap.numberone.serviceorder.api.dtos.responses.ServiceOrderResponse;
import br.com.fiap.numberone.serviceorder.api.mappers.ServiceOrderAutoserviceApiMapper;
import br.com.fiap.numberone.serviceorder.api.mappers.ServiceOrderApiMapper;
import br.com.fiap.numberone.serviceorder.application.services.ServiceOrderAutoService;
import br.com.fiap.numberone.serviceorder.application.services.ServiceOrderService;
import br.com.fiap.numberone.serviceorder.domain.entities.ServiceOrder;
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
    private final ServiceOrderAutoserviceApiMapper serviceOrderAutoserviceApiMapper;

    private final ServiceOrderService serviceOrderService;
//    private final ServiceOrderAutoService serviceOrderAutoService;

    public ServiceOrderController(
            ServiceOrderApiMapper orderApiMapper,
            ServiceOrderAutoserviceApiMapper serviceOrderAutoserviceApiMapper,
            ServiceOrderService serviceOrderService
//            ServiceOrderAutoService serviceOrderAutoService
    ) {
        this.orderApiMapper = orderApiMapper;
        this.serviceOrderAutoserviceApiMapper = serviceOrderAutoserviceApiMapper;
        this.serviceOrderService = serviceOrderService;
//        this.serviceOrderAutoService = serviceOrderAutoService;
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

    @PatchMapping("/{id}")
    public ResponseEntity<ServiceOrderResponse> addFinalDiagnosis(
            @PathVariable UUID id,
            @Valid @RequestBody FinalDiagnosisRequest finalDiagnosisRequest
    ) {
        ServiceOrder serviceOrder = serviceOrderService.addFinalDiagnosis(id, orderApiMapper.toDomain(finalDiagnosisRequest));
        return ResponseEntity.ok(orderApiMapper.toResponse(serviceOrder));
    }

//    @PostMapping("/{id}/services")
//    public ResponseEntity<ServiceOrderResponse> addService(
//            @PathVariable UUID id,
//            @Valid @RequestBody CreateOrderAutoserviceRequest createOrderAutoserviceRequest
//    ) {
//        ServiceOrderAutoservice serviceOrder = serviceOrderAutoService.createServiceOrderService(id, orderAutoserviceApiMapper.toDomain(createOrderAutoserviceRequest));
//        return ResponseEntity.ok(orderApiMapper.toResponse(serviceOrder));
//    }
}
