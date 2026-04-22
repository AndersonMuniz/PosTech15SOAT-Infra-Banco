package br.com.fiap.numberone.serviceorder.api.controllers;

import br.com.fiap.numberone.serviceorder.api.dtos.requests.CreateServiceOrderBudgetRequest;
import br.com.fiap.numberone.serviceorder.api.dtos.responses.ServiceOrderBudgetResponse;
import br.com.fiap.numberone.serviceorder.api.dtos.responses.ServiceOrderResponse;
import br.com.fiap.numberone.serviceorder.api.mappers.ServiceOrderBudgetApiMapper;
import br.com.fiap.numberone.serviceorder.application.services.ServiceOrderBudgetService;
import br.com.fiap.numberone.serviceorder.domain.entities.ServiceOrder;
import br.com.fiap.numberone.serviceorder.domain.entities.ServiceOrderBudget;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping("/api/budgets/")
public class ServiceOrderBudgetController {

    private final ServiceOrderBudgetApiMapper budgetApiMapper;
    private final ServiceOrderBudgetService budgetService;

    public ServiceOrderBudgetController(ServiceOrderBudgetApiMapper budgetApiMapper, ServiceOrderBudgetService budgetService) {
        this.budgetApiMapper = budgetApiMapper;
        this.budgetService = budgetService;
    }

    @PostMapping
    public ResponseEntity<ServiceOrderBudgetResponse> createServiceOrderBudget(
            @Valid @RequestBody CreateServiceOrderBudgetRequest createServiceOrderBudgetRequest
    ) {
        ServiceOrderBudget serviceOrderBudget = budgetService.createOrderServiceBudget(budgetApiMapper.toDomain(createServiceOrderBudgetRequest));
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(serviceOrderBudget.getId())
                .toUri();
        return ResponseEntity.created(location).body(budgetApiMapper.toResponse(serviceOrderBudget));
    }

}
