package br.com.fiap.numberone.estoque.api.controllers;

import br.com.fiap.numberone.estoque.api.dto.requests.ServicoDTORequest;
import br.com.fiap.numberone.estoque.api.dto.responses.ServicoResponse;
import br.com.fiap.numberone.estoque.api.mappers.ServicoApiMapper;
import br.com.fiap.numberone.estoque.application.services.ServicoService;
import br.com.fiap.numberone.estoque.domain.entities.Servico;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

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
    public ServicoResponse create(@RequestBody @Valid ServicoDTORequest request) {
        Servico servico = service.create(mapper.toDomain(request));
        return mapper.toResponse(servico);
    }

    @PutMapping("/{id}")
    public ServicoResponse atualizar(@PathVariable UUID id,
                                     @RequestBody ServicoDTORequest request) {

        //var atualizado = service.(id, mapper.toDomain(request));
        return null;//mapper.toResponse(atualizado);
    }

    @GetMapping
    public List<ServicoResponse> listar() {
        return service.listar()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public ServicoResponse detalhar(@PathVariable UUID id) {
        return mapper.toResponse(service.buscar(id));
    }

    @PatchMapping("/{id}/inativar")
    public void inativar(@PathVariable UUID id) {
        service.inativar(id);
    }
}

