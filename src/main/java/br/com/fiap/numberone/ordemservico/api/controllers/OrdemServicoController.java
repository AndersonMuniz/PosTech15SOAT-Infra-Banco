package br.com.fiap.numberone.ordemservico.api.controllers;

import br.com.fiap.numberone.ordemservico.api.dtos.requests.CriarOrdemServicoRequest;
import br.com.fiap.numberone.ordemservico.api.dtos.requests.DiagnosticoFinalRequest;
import br.com.fiap.numberone.ordemservico.api.dtos.responses.OrdemServicoResponse;
import br.com.fiap.numberone.ordemservico.application.services.OrdemServicoItemService;
import br.com.fiap.numberone.ordemservico.application.services.OrdemServicoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/ordens-servico")
public class OrdemServicoController {

    private final OrdemServicoService ordemServicoService;
    private final OrdemServicoItemService ordemServicoItemService;

    public OrdemServicoController(OrdemServicoService ordemServicoService, OrdemServicoItemService ordemServicoItemService) {
        this.ordemServicoService = ordemServicoService;
        this.ordemServicoItemService = ordemServicoItemService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdemServicoResponse> buscarOrdemServico(@PathVariable Long id){
        return ResponseEntity.ok(this.ordemServicoService.buscarOrdemServico(id));
    }

    @GetMapping
    public ResponseEntity<List<OrdemServicoResponse>> buscarOrdensServico(){
        return ResponseEntity.ok(this.ordemServicoService.buscarOrdensServico());
    }

    @PostMapping
    public ResponseEntity<OrdemServicoResponse> criarOrdemServico(@Valid @RequestBody CriarOrdemServicoRequest criarOrdemServicoRequest){
        OrdemServicoResponse ordemServicoResponse = this.ordemServicoService.criarOrdemServico(criarOrdemServicoRequest);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(ordemServicoResponse.id())
                .toUri();
        return ResponseEntity.created(location).body(ordemServicoResponse);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<OrdemServicoResponse> adicionarDiagnosticoFinal(@PathVariable Long id, @Valid @RequestBody DiagnosticoFinalRequest diagnosticoFinalRequest) {
        OrdemServicoResponse ordemServicoResponse = this.ordemServicoService.adicionarDiagnosticoFinal(id, diagnosticoFinalRequest);
        return ResponseEntity.ok().body(ordemServicoResponse);
    }

//    @PostMapping("/{id}/servicos")
//    public ResponseEntity<OrdemServicoResponse> adicionarListaServicos(@PathVariable Long id, @Valid @RequestBody DiagnosticoFinalRequest diagnosticoFinalRequest) {
//        OrdemServicoResponse ordemServicoResponse = this.ordemServicoItemService.adicionarListaServicos(id, diagnosticoFinalRequest);
//        return ResponseEntity.ok().body(ordemServicoResponse);
//    }

}
