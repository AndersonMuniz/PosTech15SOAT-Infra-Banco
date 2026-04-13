package br.com.fiap.numberone.ordemservico.api.controllers;

import br.com.fiap.numberone.ordemservico.api.dto.requests.CreateOrdemServicoRequest;
import br.com.fiap.numberone.ordemservico.api.dto.responses.OrdemServicoResponse;
import br.com.fiap.numberone.ordemservico.application.services.OrdemServicoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/ordens-servico")
public class OrdemServicoController {

    private final OrdemServicoService ordemServicoService;

    public OrdemServicoController(OrdemServicoService ordemServicoService) {
        this.ordemServicoService = ordemServicoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdemServicoResponse> getOrdemServico(@PathVariable Long id){
        return ResponseEntity.ok(this.ordemServicoService.getOrdemServico(id));
    }

    @PostMapping
    public ResponseEntity<OrdemServicoResponse> createNewOrdemServico(@Valid @RequestBody CreateOrdemServicoRequest createOrdemServicoRequest){
        OrdemServicoResponse ordemServicoResponse = this.ordemServicoService.createNewOrdemServico(createOrdemServicoRequest);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(ordemServicoResponse.id())
                .toUri();
        return ResponseEntity.created(location).body(ordemServicoResponse);
    }

}
