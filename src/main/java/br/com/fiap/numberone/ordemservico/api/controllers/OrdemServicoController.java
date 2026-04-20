package br.com.fiap.numberone.ordemservico.api.controllers;

import br.com.fiap.numberone.estoque.domain.entities.Servico;
import br.com.fiap.numberone.ordemservico.api.dtos.requests.CriarOrdemServicoRequest;
import br.com.fiap.numberone.ordemservico.api.dtos.requests.DiagnosticoFinalRequest;
import br.com.fiap.numberone.ordemservico.api.dtos.responses.OrdemServicoResponse;
import br.com.fiap.numberone.ordemservico.api.mappers.OrdemServicoApiMapper;
import br.com.fiap.numberone.ordemservico.application.services.OrdemServicoItemService;
import br.com.fiap.numberone.ordemservico.application.services.OrdemServicoService;
import br.com.fiap.numberone.ordemservico.domain.entities.OrdemServico;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/ordens-servico")
public class OrdemServicoController {

    private final OrdemServicoApiMapper mapper;
    private final OrdemServicoService ordemServicoService;
    private final OrdemServicoItemService ordemServicoItemService;

    public OrdemServicoController(OrdemServicoApiMapper mapper, OrdemServicoService ordemServicoService, OrdemServicoItemService ordemServicoItemService) {
        this.mapper = mapper;
        this.ordemServicoService = ordemServicoService;
        this.ordemServicoItemService = ordemServicoItemService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdemServicoResponse> buscarOrdemServico(@PathVariable UUID id){
        return ResponseEntity.ok(mapper.toResponse(this.ordemServicoService.buscarOrdemServico(id)));
    }

    @GetMapping
    public ResponseEntity<List<OrdemServicoResponse>> buscarOrdensServico(){
        return ResponseEntity.ok(this.ordemServicoService.buscarOrdensServico()
                .stream()
                .map(mapper::toResponse)
                .toList());
    }

    @PostMapping
    public ResponseEntity<OrdemServicoResponse> criarOrdemServico(@Valid @RequestBody CriarOrdemServicoRequest criarOrdemServicoRequest){
        OrdemServico ordemServico = this.ordemServicoService.criarOrdemServico(mapper.toDomain(criarOrdemServicoRequest));
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(ordemServico.getId())
                .toUri();
        return ResponseEntity.created(location).body(mapper.toResponse(ordemServico));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<OrdemServicoResponse> adicionarDiagnosticoFinal(@PathVariable UUID id, @Valid @RequestBody DiagnosticoFinalRequest diagnosticoFinalRequest) {
        OrdemServico ordemServico = this.ordemServicoService.adicionarDiagnosticoFinal(id, mapper.toDomain(diagnosticoFinalRequest));
        return ResponseEntity.ok().body(mapper.toResponse(ordemServico));
    }

    @PostMapping("/{id}/servicos")
    public ResponseEntity<OrdemServicoResponse> adicionarListaServicos(@PathVariable Long id, @Valid @RequestBody DiagnosticoFinalRequest diagnosticoFinalRequest) {
        OrdemServicoResponse ordemServicoResponse = this.ordemServicoItemService.adicionarListaServicos(id, diagnosticoFinalRequest);
        return ResponseEntity.ok().body(ordemServicoResponse);
    }

}
