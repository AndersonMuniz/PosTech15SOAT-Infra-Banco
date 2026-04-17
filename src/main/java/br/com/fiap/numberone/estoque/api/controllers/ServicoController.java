package br.com.fiap.numberone.estoque.api.controllers;

import br.com.fiap.numberone.estoque.api.dto.requests.ServicoDTORequest;
import br.com.fiap.numberone.estoque.api.dto.responses.ServicoResponse;
import br.com.fiap.numberone.estoque.api.mappers.ServicoApiMapper;
import br.com.fiap.numberone.estoque.application.services.ServicoService;
import br.com.fiap.numberone.estoque.domain.entities.Servico;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/servicos")
public class ServicoController {

    private final ServicoService service;
    private final ServicoApiMapper mapper;

    public ServicoController(ServicoService service, ServicoApiMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<ServicoResponse> create(@RequestBody @Valid ServicoDTORequest request) {
        Servico servico = service.create(mapper.toDomain(request));

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(servico.getId())
                .toUri();

        return ResponseEntity.created(location).body(mapper.toResponse(servico));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServicoResponse> update(@PathVariable UUID id,
                                     @RequestBody ServicoDTORequest request) {

        Servico updatedServico = service.update(id, mapper.toDomain(request));
        return  ResponseEntity.ok(mapper.toResponse(updatedServico));
    }

    @GetMapping
    public ResponseEntity<List<ServicoResponse>> findAll() {
        return ResponseEntity.ok(service.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList());
    }

    @GetMapping("/{id}")
    public ServicoResponse findById(@PathVariable UUID id) {
        return mapper.toResponse(service.findById(id));
    }

    @PatchMapping("/{id}/inativar")
    public ResponseEntity<Void> inactivate(@PathVariable UUID id) {
        service.inactivate(id);
        return ResponseEntity.noContent().build();
    }
}

