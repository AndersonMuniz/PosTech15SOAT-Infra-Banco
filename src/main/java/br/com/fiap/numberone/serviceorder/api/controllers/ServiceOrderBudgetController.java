package br.com.fiap.numberone.serviceorder.api.controllers;

import br.com.fiap.numberone.serviceorder.api.dtos.requests.CreateServiceOrderBudgetRequest;
import br.com.fiap.numberone.serviceorder.api.dtos.responses.ServiceOrderBudgetResponse;
import br.com.fiap.numberone.serviceorder.api.mappers.ServiceOrderBudgetApiMapper;
import br.com.fiap.numberone.serviceorder.application.services.ServiceOrderBudgetService;
import br.com.fiap.numberone.serviceorder.domain.entities.ServiceOrderBudget;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/api/budgets")
public class ServiceOrderBudgetController {

    private final ServiceOrderBudgetApiMapper budgetApiMapper;
    private final ServiceOrderBudgetService budgetService;

    public ServiceOrderBudgetController(
            ServiceOrderBudgetApiMapper budgetApiMapper,
            ServiceOrderBudgetService budgetService
    ) {
        this.budgetApiMapper = budgetApiMapper;
        this.budgetService = budgetService;
    }

    @PostMapping
    public ResponseEntity<ServiceOrderBudgetResponse> createServiceOrderBudget(
            @Valid @RequestBody CreateServiceOrderBudgetRequest createServiceOrderBudgetRequest
    ) {
        ServiceOrderBudget serviceOrderBudget = budgetService.createDraftBudget(
                budgetApiMapper.toDomain(createServiceOrderBudgetRequest)
        );
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(serviceOrderBudget.getId())
                .toUri();
        return ResponseEntity.created(location).body(budgetApiMapper.toResponse(serviceOrderBudget));
    }

    @PostMapping("/{id}/request-approval")
    public ResponseEntity<ServiceOrderBudgetResponse> requestApprovalBudget(@PathVariable UUID id) {
        ServiceOrderBudget serviceOrderBudget = budgetService.requestApproval(id);
        return ResponseEntity.ok(budgetApiMapper.toResponse(serviceOrderBudget));
    }

    @GetMapping("/{id}/approval/approve")
    public ResponseEntity<String> approveBudgetByEmailLink(@PathVariable UUID id) {
        budgetService.approve(id);
        return ResponseEntity.ok("Budget approved successfully.");
    }

    @GetMapping("/{id}/approval/reject")
    public ResponseEntity<String> rejectBudgetByEmailLink(@PathVariable UUID id) {
        budgetService.reject(id);
        return ResponseEntity.ok("Budget rejected successfully.");
    }
}
